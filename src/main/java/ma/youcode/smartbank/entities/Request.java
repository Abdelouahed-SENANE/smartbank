package ma.youcode.smartbank.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
//@Table(name = "requests")
public class Request {
    @Id
    @NotBlank
    private String project;
    @NotBlank
    private String job;
    @Positive
    @NotBlank
    private double amount;
    @Positive
    @NotBlank
    private int duration;
    @Positive
    @NotBlank
    private double monthly;
    @NotBlank
    @Email
    private String email;
    @Pattern(regexp = "^(\\\\+\\\\d{1,3}[- ]?)?\\\\d{10}$" , message = "Entre Valide Telephone nombre")
    private String phone;
    @NotBlank
    private String lastname;
    @NotBlank
    private String firstname;
    @NotBlank
    private String cin;
    @Past
    private LocalDate birthday;
    @Past
    private LocalDate dateOfHire;
    @Positive
    private double income;
    private  RequestStatus status;

    public Request() {}

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
