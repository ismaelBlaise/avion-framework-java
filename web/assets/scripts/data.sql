
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




INSERT INTO vols (numero, depart, arrivee, fin_reservation, fin_annulation, id_statut, id_ville_depart, id_ville_arrivee, id_avion) VALUES
('AF100', '2025-08-10 09:00:00', '2025-08-10 13:00:00', '2025-08-09 23:59:59', NULL, 2,   
    (SELECT id_ville FROM villes WHERE nom_ville = 'Paris'),
    (SELECT id_ville FROM villes WHERE nom_ville = 'Londres'),
    (SELECT id_avion FROM avions WHERE modele = 'Airbus A320')),

('BA200', '2025-08-15 15:30:00', '2025-08-15 20:00:00', '2025-08-14 23:59:59', NULL, 2,  -- Annulée
    (SELECT id_ville FROM villes WHERE nom_ville = 'New York'),
    (SELECT id_ville FROM villes WHERE nom_ville = 'Tokyo'),
    (SELECT id_avion FROM avions WHERE modele = 'Boeing 777'));

-- 2 vols après aujourd'hui (futurs)
INSERT INTO vols (numero, depart, arrivee, fin_reservation, fin_annulation, id_statut, id_ville_depart, id_ville_arrivee, id_avion) VALUES
('EK300', '2025-08-20 07:00:00', '2025-08-20 11:00:00', '2025-08-19 23:59:59', NULL, 2,  -- Disponible
    (SELECT id_ville FROM villes WHERE nom_ville = 'Dubaï'),
    (SELECT id_ville FROM villes WHERE nom_ville = 'Paris'),
    (SELECT id_avion FROM avions WHERE modele = 'Boeing 737')),

('LH400', '2025-09-10 21:00:00', '2025-09-11 05:00:00', '2025-09-09 23:59:59', NULL, 2,  -- Disponible
    (SELECT id_ville FROM villes WHERE nom_ville = 'Londres'),
    (SELECT id_ville FROM villes WHERE nom_ville = 'New York'),
    (SELECT id_avion FROM avions WHERE modele = 'Airbus A320'));


