package dto;

import annotation.FieldAnnotation;

public class ReservationDto {
    @FieldAnnotation(name = "idReservation")
    private String idReservation;

    @FieldAnnotation(name = "dateReservation")
    private String dateReservation;

    @FieldAnnotation(name = "idStatut")
    private String idStatut;

    @FieldAnnotation(name = "idClasse")
    private String idClasse;

    @FieldAnnotation(name = "idVol")
    private String idVol;

    
}
