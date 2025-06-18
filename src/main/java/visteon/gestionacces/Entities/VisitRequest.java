package visteon.gestionacces.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
public class VisitRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVisitRequest;
    @Column(nullable = false)
    private LocalDate VisitDate;

    @Column(nullable = false)
    private  LocalDate expectedArrival;

    @Column(nullable = false)
    private LocalDate expectedDeparture;

    private String visitPurpose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitRequestStatus visitRequestStatus;

    @ManyToOne
    private User requester;

    @ManyToOne
    private Visitor visitor;

    private String personneConcernee;


    public VisitRequest(Long idVisitRequest, LocalDate visitDate, LocalDate expectedArrival, LocalDate expectedDeparture, String visitPurpose, VisitRequestStatus visitRequestStatus, User requester, Visitor visitor, String personneConcernee) {
        this.idVisitRequest = idVisitRequest;
        VisitDate = visitDate;
        this.expectedArrival = expectedArrival;
        this.expectedDeparture = expectedDeparture;
        this.visitPurpose = visitPurpose;
        this.visitRequestStatus = visitRequestStatus;
        this.requester = requester;
        this.visitor = visitor;
        this.personneConcernee = personneConcernee;
    }

    public VisitRequest() {

    }

    public Long getIdVisitRequest() {
        return idVisitRequest;
    }

    public void setIdVisitRequest(Long idVisitRequest) {
        this.idVisitRequest = idVisitRequest;
    }

    public LocalDate getVisitDate() {
        return VisitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        VisitDate = visitDate;
    }

    public LocalDate getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(LocalDate expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public LocalDate getExpectedDeparture() {
        return expectedDeparture;
    }

    public void setExpectedDeparture(LocalDate expectedDeparture) {
        this.expectedDeparture = expectedDeparture;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    public VisitRequestStatus getVisitRequestStatus() {
        return visitRequestStatus;
    }

    public void setVisitRequestStatus(VisitRequestStatus visitRequestStatus) {
        this.visitRequestStatus = visitRequestStatus;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getPersonneConcernee() {
        return personneConcernee;
    }

    public void setPersonneConcernee(String personneConcernee) {
        this.personneConcernee = personneConcernee;
    }
}
