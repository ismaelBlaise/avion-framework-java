-- Rôles
INSERT INTO roles (role) VALUES ('admin'), ('passager');

-- Villes
INSERT INTO villes (ville) VALUES ('Paris'), ('Dakar'), ('Abidjan');

-- Avions
INSERT INTO avions (capacite, modele) VALUES (180, 'Boeing 737'), (150, 'Airbus A320');

-- Statuts
INSERT INTO statuts (statut) VALUES ('Réservé'), ('Annulé'), ('Confirmé');

-- Classes
INSERT INTO classes (classe) VALUES ('Economique'), ('Business'), ('Première');

-- Utilisateurs
INSERT INTO utilisateurs (nom, prenom, email, contact, mdp, id_role) VALUES
('Admin', 'Super', 'admin@gmail.com', '770001122', 'admin123', 1),
('Diop', 'Mamadou', 'mamadou@gmail.com', '771234567', 'pass123', 2),
('Fall', 'Aissatou', 'aissatou@gmail.com', '776543210', 'pass456', 2);

-- Vols
INSERT INTO vols (numero, date_vol, heure_depart, heure_arrive, heure_reservation, heure_annulation, id_statut, id_ville_depart, id_ville_arrive, id_avion) VALUES
('VOL001', '2024-10-10', '10:00', '13:00', '08:00', '09:00', 3, 1, 2, 1),
('VOL002', '2024-10-15', '15:00', '18:00', '10:00', '11:00', 1, 2, 3, 2);

-- Villes Escale
INSERT INTO villes_escale (id_vol, id_ville) VALUES (1, 3);

-- Configurations de Vol
INSERT INTO conf_vol (id_vol, id_classe, montant, capacite) VALUES
(1, 1, 50000, 100),
(1, 2, 80000, 50),
(2, 1, 70000, 90),
(2, 3, 120000, 30);

-- Promotions
INSERT INTO promotions (id_vol, id_classe, pourcentage, nb_siege) VALUES
(1, 1, 10.00, 10),
(2, 3, 20.00, 5);

-- Réservations
INSERT INTO reservations (date_reservation, prix, id_statut, id_classe, id_vol, id_utilisateur) VALUES
(NOW(), 45000.0000, 1, 1, 1, 2),
(NOW(), 68000.0000, 3, 2, 1, 3),
(NOW(), 56000.0000, 3, 1, 2, 2);
