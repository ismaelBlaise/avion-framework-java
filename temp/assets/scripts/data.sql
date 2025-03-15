
INSERT INTO roles (role) VALUES ('admin'), ('passager');


INSERT INTO statuts (statut) VALUES ('Réservé'), ('Annulé'), ('Confirmé');

INSERT INTO classes (classe) VALUES ('Economique'), ('Business'), ('Première');

INSERT INTO categories_age (categorie) VALUES ('Enfant'), ('Adulte'), ('Senior');

INSERT INTO villes (nom_ville) VALUES ('Paris'), ('New York'), ('Londres'), ('Tokyo'), ('Dubaï');

INSERT INTO avions (capacite, modele) VALUES (180, 'Boeing 737'), (250, 'Airbus A320'), (350, 'Boeing 777');

INSERT INTO utilisateurs (nom, prenom, email, contact, mdp, id_role) 
VALUES 
('Dupont', 'Jean', 'admin@example.com', '0601020304', 'adminpass', 1),  
('Martin', 'Sophie', 'passager@example.com', '0605060708', 'passagerpass', 2);   

INSERT INTO vols (numero, date_vol, heure_depart, heure_arrivee, id_statut, id_ville_depart, id_ville_arrivee, id_avion) 
VALUES 
('AF123', '2025-05-10', '10:00:00', '14:00:00', 3, 1, 2, 3),  
('BA456', '2025-06-15', '15:30:00', '18:45:00', 1, 3, 4, 3);  



