package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.RechercheDto;
import models.Vol;
import utils.DbConnect;

public class RechercheService {
    public List<Vol> rechercher(RechercheDto rechercheDto) throws Exception {
        List<Vol> vols = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DbConnect.getConnection();

            StringBuilder query = new StringBuilder(
                "SELECT v.*, a.modele FROM vols v " +
                "JOIN avions a ON v.id_avion = a.id_avion " +
                "WHERE 1=1"
            );

            // Liste pour stocker les paramètres dans l'ordre
            List<Object> params = new ArrayList<>();

            // Filtrage numéro vol avec LIKE
            if (rechercheDto.getNumero() != null && !rechercheDto.getNumero().isEmpty()) {
                query.append(" AND v.numero LIKE ?");
                params.add("%" + rechercheDto.getNumero() + "%");
            }

            // Gestion des dates et heures de départ
            if (rechercheDto.getDateDebut() != null && !rechercheDto.getDateDebut().isEmpty()) {
                String dateTimeDebut = rechercheDto.getDateDebut();
                if (rechercheDto.getHeureDebut() != null && !rechercheDto.getHeureDebut().isEmpty()) {
                    dateTimeDebut += " " + rechercheDto.getHeureDebut();
                } else {
                    dateTimeDebut += " 00:00:00";
                }
                query.append(" AND v.depart >= ?");
                params.add(java.sql.Timestamp.valueOf(dateTimeDebut));
            }

            if (rechercheDto.getDateFin() != null && !rechercheDto.getDateFin().isEmpty()) {
                String dateTimeFin = rechercheDto.getDateFin();
                if (rechercheDto.getHeureFin() != null && !rechercheDto.getHeureFin().isEmpty()) {
                    dateTimeFin += " " + rechercheDto.getHeureFin();
                } else {
                    dateTimeFin += " 23:59:59";
                }
                query.append(" AND v.depart <= ?");
                params.add(java.sql.Timestamp.valueOf(dateTimeFin));
            }

            // Autres filtres
            if (rechercheDto.getVilleDepart() != null && !rechercheDto.getVilleDepart().isEmpty()) {
                query.append(" AND v.id_ville_depart = ?");
                params.add(Integer.parseInt(rechercheDto.getVilleDepart()));
            }
            if (rechercheDto.getVilleArrive() != null && !rechercheDto.getVilleArrive().isEmpty()) {
                query.append(" AND v.id_ville_arrivee = ?");
                params.add(Integer.parseInt(rechercheDto.getVilleArrive()));
            }
            if (rechercheDto.getIdStatut() != null && !rechercheDto.getIdStatut().isEmpty()) {
                query.append(" AND v.id_statut = ?");
                params.add(Integer.parseInt(rechercheDto.getIdStatut()));
            }
            if (rechercheDto.getIdAvion() != null && !rechercheDto.getIdAvion().isEmpty()) {
                query.append(" AND v.id_avion = ?");
                params.add(Integer.parseInt(rechercheDto.getIdAvion()));
            }

            preparedStatement = connection.prepareStatement(query.toString());

            // Set des paramètres
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vol vol = new Vol();
                vol.setIdVol(resultSet.getLong("id_vol"));
                vol.setNumero(resultSet.getString("numero"));
                vol.setDepart(resultSet.getTimestamp("depart").toString());
                vol.setArrivee(resultSet.getTimestamp("arrivee").toString());
                vol.setFinReservation(resultSet.getTimestamp("fin_reservation") != null ? resultSet.getTimestamp("fin_reservation").toString() : null);
                vol.setFinAnnulation(resultSet.getTimestamp("fin_annulation") != null ? resultSet.getTimestamp("fin_annulation").toString() : null);
                vol.setIdStatut(resultSet.getLong("id_statut"));
                vol.setIdVilleDepart(resultSet.getLong("id_ville_depart"));
                vol.setIdVilleArrive(resultSet.getLong("id_ville_arrivee"));
                vol.setIdAvion(resultSet.getLong("id_avion"));
                vol.setModeleAvion(resultSet.getString("modele"));

                vols.add(vol);
            }

        } catch (Exception e) {
            throw new Exception("Erreur lors de la recherche des vols : " + e.getMessage(), e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return vols;
    }
}