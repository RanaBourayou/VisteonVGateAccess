package visteon.gestionacces.Entities;

import jakarta.persistence.*;

@Entity
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVisitor;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String companyName;
    @Column(unique = true)
    private Integer cin;

    @Column(unique = true)
    private Integer passportNumber;
    @Enumerated(EnumType.STRING)
    private VisitorType visitorType;



    public Visitor(Long idVisitor, String firstName, String lastName, String email, int phoneNumber, String companyName, int cin, int passportNumber, VisitorType visitorType) {
        this.idVisitor = idVisitor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.cin = cin;
        this.passportNumber = passportNumber;
        this.visitorType = visitorType;
    }

    public Visitor() {

    }

    public Long getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(Long idVisitor) {
        this.idVisitor = idVisitor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public VisitorType getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(VisitorType visitorType) {
        this.visitorType = visitorType;
    }
}

