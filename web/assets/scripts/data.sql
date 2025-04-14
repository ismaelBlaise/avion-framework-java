
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




