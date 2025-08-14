
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




-- Exemple d'insertion de vols

INSERT INTO vols (numero, depart, arrivee, fin_reservation, fin_annulation, id_statut, id_ville_depart, id_ville_arrivee, id_avion) VALUES
('AF123', '2025-09-01 08:00:00', '2025-09-01 12:00:00', '2025-08-31 23:59:59', NULL, 2, -- statut 'Disponible' pour vol
    (SELECT id_ville FROM villes WHERE nom_ville = 'Paris'),
    (SELECT id_ville FROM villes WHERE nom_ville = 'New York'),
    (SELECT id_avion FROM avions WHERE modele = 'Boeing 737')),

('BA456', '2025-09-02 14:30:00', '2025-09-02 18:45:00', '2025-09-02 13:30:00', NULL, 2,
    (SELECT id_ville FROM villes WHERE nom_ville = 'Londres'),
    (SELECT id_ville FROM villes WHERE nom_ville = 'Tokyo'),
    (SELECT id_avion FROM avions WHERE modele = 'Airbus A320')),

('EK789', '2025-09-03 22:00:00', '2025-09-04 06:00:00', '2025-09-03 21:00:00', NULL, 2,
    (SELECT id_ville FROM villes WHERE nom_ville = 'Dubaï'),
    (SELECT id_ville FROM villes WHERE nom_ville = 'Paris'),
    (SELECT id_avion FROM avions WHERE modele = 'Boeing 777'));


INSERT INTO conf_vol (id_vol, id_classe, id_categorie_age, montant, capacite)
VALUES (
    (SELECT id_vol FROM vols WHERE numero = 'AF123'),
    (SELECT id_classe FROM classes WHERE classe = 'Economique'),
    (SELECT id_categorie_age FROM categories_age WHERE categorie = 'Adulte'),
    500000.000,   -- montant en MGA
    120           -- nombre de places disponibles
);
