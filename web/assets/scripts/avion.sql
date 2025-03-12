\c postgres

DROP DATABASE avion;

CREATE DATABASE avion;

\c avion

CREATE TABLE roles (
   id_role SERIAL PRIMARY KEY,
   role VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE avions (
   id_avion SERIAL PRIMARY KEY,
   capacite INTEGER CHECK (capacite > 0),
   modele VARCHAR(50) NOT NULL
);

CREATE TABLE villes (
   id_ville SERIAL PRIMARY KEY,
   ville VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE classes (
   id_classe SERIAL PRIMARY KEY,
   classe VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE statuts (
   id_statut SERIAL PRIMARY KEY,
   statut VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE utilisateurs (
   id_utilisateur SERIAL PRIMARY KEY,
   nom VARCHAR(250) NOT NULL,
   prenom VARCHAR(250) NOT NULL,
   email VARCHAR(250) UNIQUE NOT NULL,
   date_naissance DATE NOT NULL,
   contact VARCHAR(50) NOT NULL,
   mdp VARCHAR(250) NOT NULL,
   id_role INTEGER NOT NULL,
   FOREIGN KEY (id_role) REFERENCES roles(id_role) ON DELETE CASCADE
);

CREATE TABLE vols (
   id_vol SERIAL PRIMARY KEY,
   numero VARCHAR(50) UNIQUE NOT NULL,
   date_vol DATE NOT NULL,
   heure_depart TIME NOT NULL,
   heure_arrive TIME NOT NULL,
   heure_reservation TIME NOT NULL,
   heure_annulation TIME NOT NULL,
   id_statut INTEGER NOT NULL,
   id_ville_depart INTEGER NOT NULL,
   id_ville_arrive INTEGER NOT NULL,
   id_avion INTEGER NOT NULL,
   FOREIGN KEY (id_statut) REFERENCES statuts(id_statut) ON DELETE CASCADE,
   FOREIGN KEY (id_ville_depart) REFERENCES villes(id_ville) ON DELETE CASCADE,
   FOREIGN KEY (id_ville_arrive) REFERENCES villes(id_ville) ON DELETE CASCADE,
   FOREIGN KEY (id_avion) REFERENCES avions(id_avion) ON DELETE CASCADE
);

CREATE TABLE villes_escale (
   id_vol INTEGER,
   id_ville INTEGER,
   PRIMARY KEY (id_vol, id_ville),
   FOREIGN KEY (id_vol) REFERENCES vols(id_vol) ON DELETE CASCADE,
   FOREIGN KEY (id_ville) REFERENCES villes(id_ville) ON DELETE CASCADE
);

CREATE TABLE reservations(
   Id_reservation SERIAL,
   date_reservation TIMESTAMP,
   prix NUMERIC(15,4)  ,
   Id_statut INTEGER NOT NULL,
   Id_classe INTEGER NOT NULL,
   Id_vol INTEGER NOT NULL,
   PRIMARY KEY(Id_reservation),
   FOREIGN KEY(Id_statut) REFERENCES statuts(Id_statut),
   FOREIGN KEY(Id_classe) REFERENCES classes(Id_classe),
   FOREIGN KEY(Id_vol) REFERENCES vols(Id_vol)
);

CREATE TABLE reservation_passeports(
   Id_reservation_passeports SERIAL,
   passeport VARCHAR(50) ,
   Id_reservation INTEGER NOT NULL,
   PRIMARY KEY(Id_reservation_passeports),
   FOREIGN KEY(Id_reservation) REFERENCES reservations(Id_reservation)
);

CREATE TABLE conf_vol (
   id_vol INTEGER,
   id_classe INTEGER,
   montant NUMERIC(15, 3) CHECK (montant > 0),
   capacite INTEGER CHECK (capacite > 0),
   PRIMARY KEY (id_vol, id_classe),
   FOREIGN KEY (id_vol) REFERENCES vols(id_vol) ON DELETE CASCADE,
   FOREIGN KEY (id_classe) REFERENCES classes(id_classe) ON DELETE CASCADE
);

CREATE TABLE promotions (
   id_vol INTEGER,
   id_classe INTEGER,
   pourcentage NUMERIC(5, 2) CHECK (pourcentage >= 0 AND pourcentage <= 100),
   nb_siege INTEGER CHECK (nb_siege > 0),
   PRIMARY KEY (id_vol, id_classe),
   FOREIGN KEY (id_vol) REFERENCES vols(id_vol) ON DELETE CASCADE,
   FOREIGN KEY (id_classe) REFERENCES classes(id_classe) ON DELETE CASCADE
);
