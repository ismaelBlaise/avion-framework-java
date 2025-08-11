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

            // Préparer flags pour la présence des dates et heures
            boolean hasDateDebut = rechercheDto.getDateDebut() != null && !rechercheDto.getDateDebut().isEmpty();
            boolean hasHeureDebut = rechercheDto.getHeureDebut() != null && !rechercheDto.getHeureDebut().isEmpty();
            boolean hasDateFin = rechercheDto.getDateFin() != null && !rechercheDto.getDateFin().isEmpty();
            boolean hasHeureFin = rechercheDto.getHeureFin() != null && !rechercheDto.getHeureFin().isEmpty();

            // Filtrage numéro vol avec LIKE
            if (rechercheDto.getNumero() != null && !rechercheDto.getNumero().isEmpty()) {
                query.append(" AND v.numero LIKE ?");
            }

            // Filtrage intervalle complet date+heure
            if (hasDateDebut && hasHeureDebut && hasDateFin && hasHeureFin) {
                query.append(" AND v.depart BETWEEN ? AND ?");
            } else {
                if (hasDateDebut && hasHeureDebut) {
                    query.append(" AND v.depart >= ?");
                } else if (hasDateDebut) {
                    query.append(" AND v.depart >= ?");
                }
                if (hasDateFin && hasHeureFin) {
                    query.append(" AND v.depart <= ?");
                } else if (hasDateFin) {
                    query.append(" AND v.depart <= ?");
                }
            }

            // Autres filtres
            if (rechercheDto.getVilleDepart() != null && !rechercheDto.getVilleDepart().isEmpty()) {
                query.append(" AND v.id_ville_depart = ?");
            }
            if (rechercheDto.getVilleArrive() != null && !rechercheDto.getVilleArrive().isEmpty()) {
                query.append(" AND v.id_ville_arrivee = ?");
            }
            if (rechercheDto.getIdStatut() != null && !rechercheDto.getIdStatut().isEmpty()) {
                query.append(" AND v.id_statut = ?");
            }
            if (rechercheDto.getIdAvion() != null && !rechercheDto.getIdAvion().isEmpty()) {
                query.append(" AND v.id_avion = ?");
            }

            preparedStatement = connection.prepareStatement(query.toString());

            int index = 1;

            if (rechercheDto.getNumero() != null && !rechercheDto.getNumero().isEmpty()) {
                preparedStatement.setString(index++, "%" + rechercheDto.getNumero() + "%"); // LIKE avec wildcard %
            }

            // Set paramètres date+heure combinés
            if (hasDateDebut && hasHeureDebut && hasDateFin && hasHeureFin) {
                preparedStatement.setTimestamp(index++, java.sql.Timestamp.valueOf(rechercheDto.getDateDebut() + " " + rechercheDto.getHeureDebut()));
                preparedStatement.setTimestamp(index++, java.sql.Timestamp.valueOf(rechercheDto.getDateFin() + " " + rechercheDto.getHeureFin()));
            } else {
                if (hasDateDebut && hasHeureDebut) {
                    preparedStatement.setTimestamp(index++, java.sql.Timestamp.valueOf(rechercheDto.getDateDebut() + " " + rechercheDto.getHeureDebut()));
                } else if (hasDateDebut) {
                    preparedStatement.setTimestamp(index++, java.sql.Timestamp.valueOf(rechercheDto.getDateDebut() + " 00:00:00"));
                }
                if (hasDateFin && hasHeureFin) {
                    preparedStatement.setTimestamp(index++, java.sql.Timestamp.valueOf(rechercheDto.getDateFin() + " " + rechercheDto.getHeureFin()));
                } else if (hasDateFin) {
                    preparedStatement.setTimestamp(index++, java.sql.Timestamp.valueOf(rechercheDto.getDateFin() + " 23:59:59"));
                }
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
