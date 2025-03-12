CREATE TABLE roles(
   Id_roles SERIAL,
   role VARCHAR(50) ,
   PRIMARY KEY(Id_roles)
);

CREATE TABLE avions(
   Id_avions SERIAL,
   capacite INTEGER,
   model VARCHAR(50) ,
   PRIMARY KEY(Id_avions)
);

CREATE TABLE villes(
   Id_villes SERIAL,
   ville VARCHAR(50) ,
   PRIMARY KEY(Id_villes)
);

CREATE TABLE classes(
   Id_classes SERIAL,
   classe VARCHAR(50) ,
   PRIMARY KEY(Id_classes)
);

CREATE TABLE statuts(
   Id_statuts SERIAL,
   statut VARCHAR(50) ,
   PRIMARY KEY(Id_statuts)
);

CREATE TABLE categories_age(
   Id_categorie_age SERIAL,
   categorie VARCHAR(50)  NOT NULL,
   PRIMARY KEY(Id_categorie_age)
);

CREATE TABLE utilisateurs(
   Id_utilisateurs SERIAL,
   nom VARCHAR(250) ,
   prenom VARCHAR(250) ,
   email VARCHAR(250) ,
   contact VARCHAR(250) ,
   mdp VARCHAR(250) ,
   Id_roles INTEGER NOT NULL,
   PRIMARY KEY(Id_utilisateurs),
   FOREIGN KEY(Id_roles) REFERENCES roles(Id_roles)
);

CREATE TABLE vols(
   Id_vols SERIAL,
   numero VARCHAR(50) ,
   date_vol DATE,
   heure_depart TIME,
   heure_arrive TIME,
   heure_reservation TIME,
   heure_annulation TIME,
   Id_statuts INTEGER NOT NULL,
   Id_villes INTEGER NOT NULL,
   Id_villes_1 INTEGER NOT NULL,
   Id_avions INTEGER NOT NULL,
   PRIMARY KEY(Id_vols),
   FOREIGN KEY(Id_statuts) REFERENCES statuts(Id_statuts),
   FOREIGN KEY(Id_villes) REFERENCES villes(Id_villes),
   FOREIGN KEY(Id_villes_1) REFERENCES villes(Id_villes),
   FOREIGN KEY(Id_avions) REFERENCES avions(Id_avions)
);

CREATE TABLE reservations(
   Id_reservations SERIAL,
   date_reservation TIMESTAMP,
   prix NUMERIC(15,4)  ,
   Id_statuts INTEGER NOT NULL,
   Id_classes INTEGER NOT NULL,
   Id_vols INTEGER NOT NULL,
   PRIMARY KEY(Id_reservations),
   FOREIGN KEY(Id_statuts) REFERENCES statuts(Id_statuts),
   FOREIGN KEY(Id_classes) REFERENCES classes(Id_classes),
   FOREIGN KEY(Id_vols) REFERENCES vols(Id_vols)
);

CREATE TABLE reservation_passeports(
   Id_reservation_passeports SERIAL,
   passeport VARCHAR(50) ,
   Id_reservations INTEGER NOT NULL,
   PRIMARY KEY(Id_reservation_passeports),
   FOREIGN KEY(Id_reservations) REFERENCES reservations(Id_reservations)
);

CREATE TABLE villes_escale(
   Id_vols INTEGER,
   Id_villes INTEGER,
   PRIMARY KEY(Id_vols, Id_villes),
   FOREIGN KEY(Id_vols) REFERENCES vols(Id_vols),
   FOREIGN KEY(Id_villes) REFERENCES villes(Id_villes)
);

CREATE TABLE conf_vol(
   Id_vols INTEGER,
   Id_classes INTEGER,
   Id_categorie_age INTEGER,
   montant NUMERIC(15,3)  ,
   capacite INTEGER,
   PRIMARY KEY(Id_vols, Id_classes, Id_categorie_age),
   FOREIGN KEY(Id_vols) REFERENCES vols(Id_vols),
   FOREIGN KEY(Id_classes) REFERENCES classes(Id_classes),
   FOREIGN KEY(Id_categorie_age) REFERENCES categories_age(Id_categorie_age)
);

CREATE TABLE promotions(
   Id_vols INTEGER,
   Id_classes INTEGER,
   porcentage NUMERIC(15,3)  ,
   nb_siege INTEGER,
   PRIMARY KEY(Id_vols, Id_classes),
   FOREIGN KEY(Id_vols) REFERENCES vols(Id_vols),
   FOREIGN KEY(Id_classes) REFERENCES classes(Id_classes)
);

CREATE TABLE reservation_details(
   Id_reservations INTEGER,
   Id_categorie_age INTEGER,
   nb INTEGER NOT NULL,
   PRIMARY KEY(Id_reservations, Id_categorie_age),
   FOREIGN KEY(Id_reservations) REFERENCES reservations(Id_reservations),
   FOREIGN KEY(Id_categorie_age) REFERENCES categories_age(Id_categorie_age)
);
