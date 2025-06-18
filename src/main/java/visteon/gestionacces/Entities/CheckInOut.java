package visteon.gestionacces.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CheckInOut {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCheckInOut;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean approved;
    private String notes;
    @OneToOne
    private VisitRequest visitRequest;

    @ManyToOne
    private User handledBy;
}
