package services.alea;

import java.io.ObjectInputFilter.Status;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Statut;
import models.alea.ReservationList;
import models.alea.ReservationPrix;
import services.ReservationService;
import services.StatutService;
import utils.DbConnect;

public class AleaService {


    public List<ReservationPrix> findByDate(LocalDate dateLimite) throws Exception {
        List<ReservationPrix> reservations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DbConnect.getConnection();

            String sql = "SELECT id_reservation,capacite, id_vol, id_classe, id_statut, date_reservation, " +
                         "id_reservation_prix, prix_configure, date_debut, date_fin, prix_facture, nb_sieges " +
                         "FROM vue_reservations_prix_utilises " +
                         "WHERE date_fin <= ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, java.sql.Date.valueOf(dateLimite));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ReservationPrix rp = new ReservationPrix();
                rp.setIdReservation(resultSet.getInt("id_reservation"));
                rp.setIdVol(resultSet.getInt("id_vol"));
                rp.setCapacite(resultSet.getInt("capacite"));
                rp.setIdClasse(resultSet.getInt("id_classe"));
                rp.setIdStatut(resultSet.getInt("id_statut"));

                Timestamp ts = resultSet.getTimestamp("date_reservation");
                if (ts != null) {
                    rp.setDateReservation(ts.toLocalDateTime());
                }

                rp.setIdReservationPrix(resultSet.getInt("id_reservation_prix"));
                rp.setPrixConfigure(resultSet.getBigDecimal("prix_configure"));

                Date db = resultSet.getDate("date_debut");
                if (db != null) {
                    rp.setDateDebut(db.toLocalDate());
                }

                Date df = resultSet.getDate("date_fin");
                if (df != null) {
                    rp.setDateFin(df.toLocalDate());
                }

                rp.setPrixFacture(resultSet.getBigDecimal("prix_facture"));
                rp.setNbSieges(resultSet.getInt("nb_sieges"));

                reservations.add(rp);
            }

            return reservations;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<ReservationPrix> annulerLesReservation(List<ReservationPrix> reservationPrixs) throws Exception{
        ReservationService reservationService = new ReservationService();
        StatutService statutService = new StatutService();
        Statut statut = statutService.getByStatut("Confirme");
        Statut statutAnnule=statutService.getByStatut("Annulee");
        for (ReservationPrix reservationPrix : reservationPrixs) {
            
            if(reservationPrix.getIdStatut()==statut.getIdStatut()){
                reservationService.annulerReservation(reservationPrix.getIdReservation());
                reservationPrix.setIdStatut(statutAnnule.getIdStatut().intValue());
                
            }

        }

        return reservationPrixs;



    }


    @SuppressWarnings("unused")
    public List<ReservationPrix> mettreAJourSieze(List<ReservationPrix> reservationPrixs,LocalDate date) throws Exception {
        StatutService statutService = new StatutService();
        Statut statutAnnulee = statutService.getByStatut("Annulee");

        Connection connection = null;
        PreparedStatement selectStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        try {
            connection = DbConnect.getConnection();
            connection.setAutoCommit(false); // pour gérer la transaction

            String selectSql = "SELECT id_reservation_prix " +
                            "FROM reservation_prix " +
                            "WHERE id_vol = ? AND id_classe = ? AND date_fin > ? " +
                            "ORDER BY date_fin ASC " +
                            "LIMIT 1";

            String updateSql = "UPDATE reservation_prix SET capacite = capacite + ? WHERE id_reservation_prix = ?";
            String subtractSql = "UPDATE reservation_prix SET capacite =  ? WHERE id_reservation_prix = ?";

            selectStmt = connection.prepareStatement(selectSql);
            updateStmt = connection.prepareStatement(updateSql);
            PreparedStatement subtractStmt = connection.prepareStatement(subtractSql);

            for (ReservationPrix reservationPrix : reservationPrixs) {
                // System.out.println(reservationPrix.getIdStatut());
                if (reservationPrix.getIdStatut() == statutAnnulee.getIdStatut()) {
                    int nbSiegesLibres = reservationPrix.getNbSieges();

                    // Chercher la tranche héritière
                    selectStmt.setInt(1, reservationPrix.getIdVol());
                    selectStmt.setInt(2, reservationPrix.getIdClasse());
                    selectStmt.setDate(3, java.sql.Date.valueOf(date));

                    rs = selectStmt.executeQuery();
                    // System.out.println(date.toString());
                    if (rs.next()) {
                        // System.out.println("ALLOO");
                        int idReservationPrixHeredite = rs.getInt("id_reservation_prix");

                        // Ajouter les sièges à la tranche héritière
                        updateStmt.setInt(1, nbSiegesLibres);
                        updateStmt.setInt(2, idReservationPrixHeredite);
                        updateStmt.executeUpdate();


                        // Soustraire les sièges de la tranche initiale
                        subtractStmt.setInt(1, nbSiegesLibres);
                        subtractStmt.setInt(2, reservationPrix.getIdReservationPrix());
                        subtractStmt.executeUpdate();
                    }

                    if (rs != null) rs.close();
                }
            }

            connection.commit();

            if (subtractStmt != null) subtractStmt.close();

        } catch (SQLException e) {
            if (connection != null) connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (selectStmt != null) selectStmt.close();
                if (updateStmt != null) updateStmt.close();
                if (connection != null) connection.setAutoCommit(true);
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reservationPrixs;
    }



    public List<ReservationList> aleaFonction(LocalDate date)throws Exception{
        List<ReservationPrix> reservationPrixs=findByDate(date);
        reservationPrixs=annulerLesReservation(reservationPrixs);
        reservationPrixs=mettreAJourSieze(reservationPrixs,date);
        List<ReservationList> reservationLists=new ArrayList<>();
        int id=-1;
        for (ReservationPrix reservationPrix : reservationPrixs) {
            if(reservationPrix.getIdReservationPrix()!=id){
                id=reservationPrix.getIdReservationPrix();
                ReservationList reservationList=new ReservationList();
                reservationList.setDateFin(reservationPrix.getDateFin());
                reservationList.setCapaciteInitial(reservationPrix.getCapacite());
                reservationList.setIdReservationPrix(id);
                reservationLists.add(reservationList);
            }
        }

        for (ReservationList reservationList : reservationLists) {
            List<ReservationPrix> reservationPrixs2=new ArrayList<>();
            int siezeVendu=0;
            for (ReservationPrix reservationPrix2 : reservationPrixs) {
                if(reservationList.getIdReservationPrix()==reservationPrix2.getIdReservationPrix()){
                    reservationPrixs2.add(reservationPrix2);
                    siezeVendu+=reservationPrix2.getNbSieges();
                    
                }
            }
            reservationList.setSiezeVendu(siezeVendu);
            reservationList.setSiezeNonVendu(reservationList.getCapaciteInitial()-siezeVendu);
        }
        return reservationLists;
    }

}

