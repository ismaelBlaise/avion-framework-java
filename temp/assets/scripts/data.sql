
INSERT INTO roles (role) VALUES ('admin'), ('passager');


INSERT INTO statuts (statut,source) VALUES ('Non disponible','vols'),('Disponible','vols'), ('Annulee','reservation'), ('Confirme','reservation'),('Payee','reservation');


INSERT INTO categories_age (categorie,age_min,age_max) VALUES ('Enfant',1,20), ('Adulte',20,100) ;


INSERT INTO utilisateurs (nom, prenom, email, contact, mdp, id_role) 
VALUES 
('Dupont', 'Jean', 'admin@example.com', '0601020304', 'adminpass', 1),  
('Martin', 'Sophie', 'passager@example.com', '0605060708', 'passagerpass', 2);   





-- INSERTION DES VILLES
INSERT INTO villes (nom_ville) VALUES 
('Antananarivo'), 
('Paris CDG'), 
('Mauritius'), 
('Addis Abeba');

-- INSERTION DES AVIONS
INSERT INTO avions (capacite, modele) VALUES 
(200, 'AV001'),   -- Air Madagascar
(200, 'AV002');   -- Air France

-- INSERTION DES CLASSES
INSERT INTO classes (classe) VALUES 
('Economique'),
('Affaire');

-- INSERTION DES VOLS
-- On utilise TIMESTAMP pour les dates
INSERT INTO vols (numero, depart, arrivee, id_ville_depart, id_ville_arrivee, id_avion, id_statut) VALUES
('VOL1', '2025-09-05 08:00:00', '2025-09-05 12:00:00', 
    (SELECT id_ville FROM villes WHERE nom_ville='Antananarivo'),
    (SELECT id_ville FROM villes WHERE nom_ville='Addis Abeba'),
    (SELECT id_avion FROM avions WHERE modele='AV001'),
    (SELECT id_statut FROM statuts WHERE statut='Disponible' AND source='vols')
),
('VOL2', '2025-09-15 21:00:00', '2025-09-16 05:00:00', 
    (SELECT id_ville FROM villes WHERE nom_ville='Paris CDG'),
    (SELECT id_ville FROM villes WHERE nom_ville='Mauritius'),
    (SELECT id_avion FROM avions WHERE modele='AV002'),
    (SELECT id_statut FROM statuts WHERE statut='Disponible' AND source='vols')
);

-- INSERTION DES PRIX PAR VOL
INSERT INTO reservation_prix (id_vol, id_classe, montant, capacite, date_fin) VALUES
((SELECT id_vol FROM vols WHERE numero='VOL1'), (SELECT id_classe FROM classes WHERE classe='Economique'), 200, 4, '2025-08-27'),
((SELECT id_vol FROM vols WHERE numero='VOL1'), (SELECT id_classe FROM classes WHERE classe='Economique'), 300, 2, '2025-09-03'),
((SELECT id_vol FROM vols WHERE numero='VOL2'), (SELECT id_classe FROM classes WHERE classe='Economique'), 350, 3, '2025-09-05'),
((SELECT id_vol FROM vols WHERE numero='VOL2'), (SELECT id_classe FROM classes WHERE classe='Economique'), 400, 1, '2025-09-13');

-- INSERTION DES RÉSERVATIONS
INSERT INTO reservations (id_vol, id_utilisateur, id_statut, date_reservation) VALUES
((SELECT id_vol FROM vols WHERE numero='VOL1'), 2, (SELECT id_statut FROM statuts WHERE statut='Payee' AND source='reservation'), '2025-08-20'),
((SELECT id_vol FROM vols WHERE numero='VOL1'), 2, (SELECT id_statut FROM statuts WHERE statut='Confirme' AND source='reservation'), '2025-08-21'),
((SELECT id_vol FROM vols WHERE numero='VOL1'), 2, (SELECT id_statut FROM statuts WHERE statut='Payee' AND source='reservation'), '2025-08-21'),
((SELECT id_vol FROM vols WHERE numero='VOL1'), 2, (SELECT id_statut FROM statuts WHERE statut='Payee' AND source='reservation'), '2025-08-28'),
((SELECT id_vol FROM vols WHERE numero='VOL1'), 2, (SELECT id_statut FROM statuts WHERE statut='Payee' AND source='reservation'), '2025-08-29'),
((SELECT id_vol FROM vols WHERE numero='VOL1'), 2, (SELECT id_statut FROM statuts WHERE statut='Confirme' AND source='reservation'), '2025-09-01'),
((SELECT id_vol FROM vols WHERE numero='VOL1'), 2, (SELECT id_statut FROM statuts WHERE statut='Payee' AND source='reservation'), '2025-09-02'),

((SELECT id_vol FROM vols WHERE numero='VOL2'), 2, (SELECT id_statut FROM statuts WHERE statut='Payee' AND source='reservation'), '2025-09-01'),
((SELECT id_vol FROM vols WHERE numero='VOL2'), 2, (SELECT id_statut FROM statuts WHERE statut='Confirme' AND source='reservation'), '2025-09-02'),
((SELECT id_vol FROM vols WHERE numero='VOL2'), 2, (SELECT id_statut FROM statuts WHERE statut='Payee' AND source='reservation'), '2025-09-08'),
((SELECT id_vol FROM vols WHERE numero='VOL2'), 2, (SELECT id_statut FROM statuts WHERE statut='Payee' AND source='reservation'), '2025-09-10');



