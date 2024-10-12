package ma.youcode.smartbank.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "histories")
public class History {

    @Id
    @GeneratedValue(generator = "UUID")
    private  UUID historyId;

    @ManyToOne
    @JoinColumn(name = "requestId", nullable = false)
    private Request request;

    @ManyToOne
    @JoinColumn(name = "statusName" , nullable = false)
    private Status status;

    @UpdateTimestamp
    private LocalDateTime changedAt;

    public History(){}
    public History(Request request , Status status){
        this.request = request;
        this.status = status;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt ;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public void setHistoryId(UUID historyId) {
        this.historyId = historyId;
    }

    public UUID getHistoryId() {
        return historyId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
