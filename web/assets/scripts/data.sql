
INSERT INTO roles (role) VALUES ('admin'), ('passager');


INSERT INTO statuts (statut,source) VALUES ('Non disponible','vols'),('Disponible','vols'), ('Annulee','reservation'), ('Confirme','reservation'),('Payee','reservation');
-- INSERT INTO statuts (statut,source) VALUES ('En ','vols');

INSERT INTO classes (classe) VALUES ('Economique'), ('Business'), ('Première');

INSERT INTO categories_age (categorie,age_min,age_max) VALUES ('Enfant',1,20), ('Adulte',20,100) ;

INSERT INTO villes (nom_ville) VALUES ('Paris'), ('New York'), ('Londres'), ('Tokyo'), ('Dubaï');

INSERT INTO avions (capacite, modele) VALUES (180, 'Boeing 737'), (250, 'Airbus A320'), (350, 'Boeing 777');

INSERT INTO utilisateurs (nom, prenom, email, contact, mdp, id_role) 
VALUES 
('Dupont', 'Jean', 'admin@example.com', '0601020304', 'adminpass', 1),  
('Martin', 'Sophie', 'passager@example.com', '0605060708', 'passagerpass', 2);   




I

-- 2 vols après aujourd'hui (futurs)
INSERT INTO vols (numero, depart, arrivee, fin_reservation, fin_annulation, id_statut, id_ville_depart, id_ville_arrivee, id_avion) VALUES
('EK300', '2025-09-20 07:00:00', '2025-09-20 11:00:00', NULL, NULL, 2,  -- Disponible
    (SELECT id_ville FROM villes WHERE nom_ville = 'Dubaï'),
    (SELECT id_ville FROM villes WHERE nom_ville = 'Paris'),
    (SELECT id_avion FROM avions WHERE modele = 'Boeing 737'));



-- Reservation prix : prix et stock pour chaque vol et classe
INSERT INTO reservation_prix (id_vol, id_classe, montant, capacite, date_fin)
VALUES
((SELECT id_vol FROM vols WHERE numero='EK300'), (SELECT id_classe FROM classes WHERE classe='Economique'), 500.000, 120, '2025-08-28'),

((SELECT id_vol FROM vols WHERE numero='EK300'), (SELECT id_classe FROM classes WHERE classe='Economique'), 450.000, 120, '2025-08-20');






