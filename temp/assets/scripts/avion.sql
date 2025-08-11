\c postgres

DROP DATABASE avion;

CREATE DATABASE avion;

\c avion

CREATE TABLE roles (
   id_role SERIAL PRIMARY KEY,
   role VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE avions (
   id_avion SERIAL PRIMARY KEY,
   capacite INTEGER NOT NULL,
   modele VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE villes (
   id_ville SERIAL PRIMARY KEY,
   nom_ville VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE classes (
   id_classe SERIAL PRIMARY KEY,
   classe VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE statuts (
   id_statut SERIAL PRIMARY KEY,
   statut VARCHAR(50) NOT NULL UNIQUE,
   source VARCHAR(50) NOT NULL
);

CREATE TABLE categories_age (
   id_categorie_age SERIAL PRIMARY KEY,
   categorie VARCHAR(50) NOT NULL UNIQUE,
   age_min INTEGER,
   age_max INTEGER
);

CREATE TABLE utilisateurs (
   id_utilisateur SERIAL PRIMARY KEY,
   nom VARCHAR(250) NOT NULL,
   prenom VARCHAR(250) NOT NULL,
   email VARCHAR(250) NOT NULL UNIQUE,
   contact VARCHAR(50) NOT NULL UNIQUE,
   mdp VARCHAR(250) NOT NULL,
   id_role INTEGER NOT NULL,
   FOREIGN KEY (id_role) REFERENCES roles(id_role) ON DELETE CASCADE
);

CREATE TABLE vols (
    id_vol SERIAL PRIMARY KEY,
    numero VARCHAR(50) NOT NULL UNIQUE,
    depart TIMESTAMP NOT NULL,             
    arrivee TIMESTAMP NOT NULL,           
    fin_reservation TIMESTAMP,            
    fin_annulation TIMESTAMP,              
    id_statut INTEGER,                     
    id_ville_depart INTEGER NOT NULL,
    id_ville_arrivee INTEGER NOT NULL,
    id_avion INTEGER NOT NULL,
    FOREIGN KEY (id_statut) REFERENCES statuts(id_statut) ON DELETE SET NULL,
    FOREIGN KEY (id_ville_depart) REFERENCES villes(id_ville) ON DELETE CASCADE,
    FOREIGN KEY (id_ville_arrivee) REFERENCES villes(id_ville) ON DELETE CASCADE,
    FOREIGN KEY (id_avion) REFERENCES avions(id_avion) ON DELETE CASCADE
);


CREATE TABLE reservations (
   id_reservation SERIAL PRIMARY KEY,
   date_reservation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   id_statut INTEGER NOT NULL,
   id_utilisateur INTEGER NOT NULL,
   id_vol INTEGER NOT NULL,
   FOREIGN KEY (id_statut) REFERENCES statuts(id_statut) ON DELETE SET NULL,
   FOREIGN KEY (id_utilisateur) REFERENCES utilisateurs(id_utilisateur) ON DELETE SET NULL,
   FOREIGN KEY (id_vol) REFERENCES vols(id_vol) ON DELETE CASCADE
);


CREATE TABLE conf_vol (
   id_vol INTEGER NOT NULL,
   id_classe INTEGER NOT NULL,
   id_categorie_age INTEGER NOT NULL,
   montant NUMERIC(15,3) NOT NULL CHECK (montant >= 0),
   capacite INTEGER NOT NULL CHECK (capacite >= 0),
   PRIMARY KEY (id_vol, id_classe, id_categorie_age),
   FOREIGN KEY (id_vol) REFERENCES vols(id_vol) ON DELETE CASCADE,
   FOREIGN KEY (id_classe) REFERENCES classes(id_classe) ON DELETE CASCADE,
   FOREIGN KEY (id_categorie_age) REFERENCES categories_age(id_categorie_age) ON DELETE CASCADE
);

CREATE TABLE promotions (
   id_vol INTEGER NOT NULL,
   id_classe INTEGER NOT NULL,
   pourcentage NUMERIC(5,2) NOT NULL CHECK (pourcentage BETWEEN 0 AND 100),
   nb_siege INTEGER NOT NULL CHECK (nb_siege >= 0),
   PRIMARY KEY (id_vol, id_classe),
   FOREIGN KEY (id_vol) REFERENCES vols(id_vol) ON DELETE CASCADE,
   FOREIGN KEY (id_classe) REFERENCES classes(id_classe) ON DELETE CASCADE
);

CREATE TABLE reservation_details (
   id_reservation_detail SERIAL PRIMARY KEY,
   id_reservation INTEGER NOT NULL,
   id_categorie_age INTEGER NOT NULL,
   id_classe INTEGER NOT NULL,
   prix NUMERIC(15,4) NOT NULL CHECK (prix >= 0),
   passeport BYTEA,
   nom_fichier VARCHAR(255) UNIQUE,
   date_depot TIMESTAMP,
   FOREIGN KEY (id_classe) REFERENCES classes(id_classe) ON DELETE CASCADE,
   FOREIGN KEY (id_reservation) REFERENCES reservations(id_reservation) ON DELETE CASCADE,
   FOREIGN KEY (id_categorie_age) REFERENCES categories_age(id_categorie_age) ON DELETE CASCADE
);
