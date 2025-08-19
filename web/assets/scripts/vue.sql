


CREATE OR REPLACE VIEW vue_vols_promotions AS
SELECT 
    r.id_vol,
    rd.id_classe,
    COUNT(*) AS nb_sieges_promotion
FROM reservation_details rd
JOIN reservations r 
    ON rd.id_reservation = r.id_reservation
JOIN conf_vol cv 
    ON rd.id_classe = cv.id_classe
   AND rd.id_categorie_age = cv.id_categorie_age
   AND r.id_vol = cv.id_vol
WHERE rd.prix < cv.montant
  AND r.id_statut != (SELECT id_statut FROM statuts WHERE statut = 'Annulee')
GROUP BY r.id_vol, rd.id_classe;




CREATE OR REPLACE VIEW vue_stock_billets_date AS
SELECT 
    rp.id_vol,
    rp.id_classe,
    rp.capacite AS capacite_configuree,
    rp.montant AS prix_unitaire,
    rp.date_fin,
    rp.date_debut,
    rp.capacite - COALESCE(
        (
            SELECT COUNT(*)
            FROM reservations r
            JOIN reservation_details rd ON r.id_reservation = rd.id_reservation
            WHERE  r.id_statut != (SELECT id_statut FROM statuts WHERE statut = 'Annulee')
            AND r.id_vol = rp.id_vol
              AND rd.id_classe = rp.id_classe
              AND r.date_reservation > rp.date_debut
              AND r.date_reservation <= rp.date_fin
        ), 0
    ) AS stock_disponible
FROM (
    SELECT 
        rp.*,
        COALESCE(
            LAG(rp.date_fin) OVER (PARTITION BY rp.id_vol, rp.id_classe ORDER BY rp.date_fin),
            '1970-01-01'::date
        ) AS date_debut
    FROM reservation_prix rp
) rp;



CREATE OR REPLACE VIEW vue_reservations_prix_utilises AS
SELECT 
    r.id_reservation,
    r.id_vol,
    rd.id_classe,
    r.date_reservation,
    r.id_statut,
    rp.montant AS prix_configure,
    rp.id_reservation_prix,
    rp.date_debut,
    rp.date_fin,
    rd.prix AS prix_facture,
    COUNT(rd.id_reservation_detail) AS nb_sieges
FROM reservations r
JOIN reservation_details rd 
    ON r.id_reservation = rd.id_reservation
JOIN (
    SELECT 
        rp.*,
        COALESCE(
            LAG(rp.date_fin) OVER (PARTITION BY rp.id_vol, rp.id_classe ORDER BY rp.date_fin),
            '1970-01-01'::date
        ) AS date_debut
    FROM reservation_prix rp
) rp 
    ON r.id_vol = rp.id_vol
   AND rd.id_classe = rp.id_classe
   AND r.date_reservation > rp.date_debut
   AND r.date_reservation <= rp.date_fin
GROUP BY 
    r.id_reservation,r.id_statut, r.id_vol, rd.id_classe, r.date_reservation,rp.id_reservation_prix,
    rp.montant, rp.date_debut, rp.date_fin, rd.prix;

