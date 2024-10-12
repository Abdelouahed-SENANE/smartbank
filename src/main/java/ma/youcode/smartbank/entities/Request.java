package ma.youcode.smartbank.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Timestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID requestId;

    @NotBlank(message = "Project name cannot be blank")
    @Size(min = 3, max = 100, message = "Project name must be between 3 and 100 characters")
    private String projectName;

    @NotBlank(message = "Job name cannot be blank")
    @Size(min = 3, max = 100, message = "Job name must be between 3 and 100 characters")
    private String jobName;

    @Positive(message = "Amount must be positive")
    private double amount;

    @NotBlank(message = "Civility is required")
    private String civility;

    @Min(value = 1, message = "Duration must be at least 1 month")
    private int duration;

    @Positive(message = "Monthly amount must be positive")
    private double monthly;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")
    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Lastname cannot be blank")
    @Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters")
    private String lastname;

    @NotBlank(message = "Firstname cannot be blank")
    @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
    private String firstname;

    @NotBlank(message = "CIN is required")
    @Size(min = 5, max = 20, message = "CIN must be between 5 and 20 characters")
    private String cin;

    @Past(message = "Birthday must be a past date")
    private LocalDate birthday;

    @PastOrPresent(message = "Date of hire must be in the past or present")
    private LocalDate dateOfHire;

    @Positive(message = "Income must be positive")
    private double income;

    @OneToMany(mappedBy = "request")
    private List<History> histories = new ArrayList<>();

    @Positive(message = "Fees must be positive")
    private double fees;
    @UpdateTimestamp
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Request() {}

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
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

    public void setIncome(double income) {
        this.income = income;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public List<History> getStatusHistories() {
        return histories;
    }

    public void addStatus(Status status) {
        History history = new History(this, status);
        histories.add(history);
    }
}
