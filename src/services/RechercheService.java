package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import dto.RechercheDto;
import models.Vol;
import utils.DbConnect;

public class RechercheService {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

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

            List<Object> params = new ArrayList<>();

            // Filtrage numéro vol
            if (rechercheDto.getNumero() != null && !rechercheDto.getNumero().isEmpty()) {
                query.append(" AND v.numero LIKE ?");
                params.add("%" + rechercheDto.getNumero() + "%");
            }

            // Gestion des dates de départ
            try {
                if (rechercheDto.getDateDebut() != null && !rechercheDto.getDateDebut().isEmpty()) {
                    LocalDate dateDebut = LocalDate.parse(rechercheDto.getDateDebut(), DATE_FORMATTER);
                    LocalTime heureDebut = rechercheDto.getHeureDebut() != null && !rechercheDto.getHeureDebut().isEmpty()
                            ? LocalTime.parse(rechercheDto.getHeureDebut(), TIME_FORMATTER)
                            : LocalTime.MIN;
                    LocalDateTime dateTimeDebut = LocalDateTime.of(dateDebut, heureDebut);
                    query.append(" AND v.depart >= ?");
                    params.add(Timestamp.valueOf(dateTimeDebut));
                }

                if (rechercheDto.getDateFin() != null && !rechercheDto.getDateFin().isEmpty()) {
                    LocalDate dateFin = LocalDate.parse(rechercheDto.getDateFin(), DATE_FORMATTER);
                    LocalTime heureFin = rechercheDto.getHeureFin() != null && !rechercheDto.getHeureFin().isEmpty()
                            ? LocalTime.parse(rechercheDto.getHeureFin(), TIME_FORMATTER)
                            : LocalTime.MAX;
                    LocalDateTime dateTimeFin = LocalDateTime.of(dateFin, heureFin);
                    query.append(" AND v.depart <= ?");
                    params.add(Timestamp.valueOf(dateTimeFin));
                }
            } catch (DateTimeParseException e) {
                throw new Exception("Format de date ou heure invalide. Utilisez le format yyyy-MM-dd pour les dates et HH:mm:ss pour les heures.");
            }

            // Filtres sur les villes (convertir en entier)
            try {
                if (rechercheDto.getVilleDepart() != null && !rechercheDto.getVilleDepart().isEmpty()) {
                    query.append(" AND v.id_ville_depart = ?");
                    params.add(Integer.parseInt(rechercheDto.getVilleDepart()));
                }
                if (rechercheDto.getVilleArrive() != null && !rechercheDto.getVilleArrive().isEmpty()) {
                    query.append(" AND v.id_ville_arrivee = ?");
                    params.add(Integer.parseInt(rechercheDto.getVilleArrive()));
                }
            } catch (NumberFormatException e) {
                throw new Exception("L'ID de la ville doit être un nombre entier");
            }

            // Autres filtres
            try {
                if (rechercheDto.getIdStatut() != null && !rechercheDto.getIdStatut().isEmpty()) {
                    query.append(" AND v.id_statut = ?");
                    params.add(Integer.parseInt(rechercheDto.getIdStatut()));
                }
                if (rechercheDto.getIdAvion() != null && !rechercheDto.getIdAvion().isEmpty()) {
                    query.append(" AND v.id_avion = ?");
                    params.add(Integer.parseInt(rechercheDto.getIdAvion()));
                }
            } catch (NumberFormatException e) {
                throw new Exception("L'ID du statut ou de l'avion doit être un nombre entier");
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
                vol.setFinReservation(resultSet.getTimestamp("fin_reservation") != null 
                    ? resultSet.getTimestamp("fin_reservation").toString() : null);
                vol.setFinAnnulation(resultSet.getTimestamp("fin_annulation") != null 
                    ? resultSet.getTimestamp("fin_annulation").toString() : null);
                vol.setIdStatut(resultSet.getLong("id_statut"));
                vol.setIdVilleDepart(resultSet.getLong("id_ville_depart"));
                vol.setIdVilleArrive(resultSet.getLong("id_ville_arrivee"));
                vol.setIdAvion(resultSet.getLong("id_avion"));
                vol.setModeleAvion(resultSet.getString("modele"));
                VilleService villeService = new VilleService();
                vol.setVilleDepart(villeService.getById(vol.getIdVilleDepart()).getVille());
                vol.setVilleArrive(villeService.getById(vol.getIdVilleArrive()).getVille());
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