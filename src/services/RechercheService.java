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
            
            StringBuilder query = new StringBuilder("SELECT * FROM vols WHERE 1=1");

            if (rechercheDto.getNumero() != null && !rechercheDto.getNumero().isEmpty()) {
                query.append(" AND numero = ?");
            }
            if (rechercheDto.getDateDebut() != null && !rechercheDto.getDateDebut().isEmpty()) {
                query.append(" AND date_vol >= ?");
            }
            if (rechercheDto.getDateFin() != null && !rechercheDto.getDateFin().isEmpty()) {
                query.append(" AND date_vol <= ?");
            }
            if (rechercheDto.getHeureDebut() != null && !rechercheDto.getHeureDebut().isEmpty()) {
                query.append(" AND heure_depart >= ?");
            }
            if (rechercheDto.getHeureFin() != null && !rechercheDto.getHeureFin().isEmpty()) {
                query.append(" AND heure_depart <= ?");
            }
            if (rechercheDto.getVilleDepart() != null && !rechercheDto.getVilleDepart().isEmpty()) {
                query.append(" AND id_ville_depart = ?");
            }
            if (rechercheDto.getVilleArrive() != null && !rechercheDto.getVilleArrive().isEmpty()) {
                query.append(" AND id_ville_arrivee = ?");
            }
            if (rechercheDto.getIdStatut() != null && !rechercheDto.getIdStatut().isEmpty()) {
                query.append(" AND id_statut = ?");
            }
            if (rechercheDto.getIdAvion() != null && !rechercheDto.getIdAvion().isEmpty()) {
                query.append(" AND id_avion = ?");
            }

            preparedStatement = connection.prepareStatement(query.toString());
            
            int index = 1;
            if (rechercheDto.getNumero() != null && !rechercheDto.getNumero().isEmpty()) {
                preparedStatement.setString(index++, rechercheDto.getNumero());
            }
            if (rechercheDto.getDateDebut() != null && !rechercheDto.getDateDebut().isEmpty()) {
                preparedStatement.setDate(index++, java.sql.Date.valueOf(rechercheDto.getDateDebut()));
            }
            if (rechercheDto.getDateFin() != null && !rechercheDto.getDateFin().isEmpty()) {
                preparedStatement.setDate(index++, java.sql.Date.valueOf(rechercheDto.getDateFin()));
            }
            if (rechercheDto.getHeureDebut() != null && !rechercheDto.getHeureDebut().isEmpty()) {
                preparedStatement.setTime(index++, java.sql.Time.valueOf(rechercheDto.getHeureDebut()));
            }
            if (rechercheDto.getHeureFin() != null && !rechercheDto.getHeureFin().isEmpty()) {
                preparedStatement.setTime(index++, java.sql.Time.valueOf(rechercheDto.getHeureFin()));
            }
            if (rechercheDto.getVilleDepart() != null && !rechercheDto.getVilleDepart().isEmpty()) {
                preparedStatement.setInt(index++, Integer.parseInt(rechercheDto.getVilleDepart()));
            }
            if (rechercheDto.getVilleArrive() != null && !rechercheDto.getVilleArrive().isEmpty()) {
                preparedStatement.setInt(index++, Integer.parseInt(rechercheDto.getVilleArrive()));
            }
            if (rechercheDto.getIdStatut() != null && !rechercheDto.getIdStatut().isEmpty()) {
                preparedStatement.setInt(index++, Integer.parseInt(rechercheDto.getIdStatut()));
            }
            if (rechercheDto.getIdAvion() != null && !rechercheDto.getIdAvion().isEmpty()) {
                preparedStatement.setInt(index++, Integer.parseInt(rechercheDto.getIdAvion()));
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vol vol = new Vol();
                vol.setIdVol(resultSet.getLong("id_vol"));
                vol.setNumero(resultSet.getString("numero"));
                // vol.setDateVol(resultSet.getString("date_vol"));
                vol.setDepart(resultSet.getString("depart"));
                vol.setArrivee(resultSet.getString("arrivee"));
                vol.setFinReservation(resultSet.getString("fin_reservation"));
                vol.setFinAnnulation(resultSet.getString("fin_annulation"));
                vol.setIdStatut(resultSet.getLong("id_statut"));
                vol.setIdVilleDepart(resultSet.getLong("id_ville_depart"));
                vol.setIdVilleArrive(resultSet.getLong("id_ville_arrivee"));
                vol.setIdAvion(resultSet.getLong("id_avion"));

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
