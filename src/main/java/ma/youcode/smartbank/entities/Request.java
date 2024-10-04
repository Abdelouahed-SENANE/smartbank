package ma.youcode.smartbank.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity

public class Request {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID requestId;


    private String project;
    private String job;


    private double amount;


    private int duration;


    private double monthly;



    private String email;

    private String phone;

    private String lastname;

    private String firstname;

    private String cin;

    private LocalDate birthday;

    private LocalDate dateOfHire;

    private double income;
    private  RequestStatus status;

    public Request() {}

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String nameProject) {
        this.project = nameProject;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getMonthly() {
        return monthly;
    }

    public void setMonthly(double monthly) {
        this.monthly = monthly;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(LocalDate dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double totalMonthlyIncome) {
        this.income = totalMonthlyIncome;
    }

    public enum RequestStatus {
        EN_ATTENTE,
        APPROUVÉ,
        REJETÉ,
        TERMINÉ
    }
}
