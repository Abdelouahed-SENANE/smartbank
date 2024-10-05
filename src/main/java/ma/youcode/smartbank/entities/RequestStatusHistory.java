package ma.youcode.smartbank.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "request_status_history")
public class RequestStatusHistory {

    @Id
    @GeneratedValue(generator = "UUID")
    private  UUID historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid", nullable = false)
    private Request request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statusname" , nullable = false)
    private Status status;

    @UpdateTimestamp
    private LocalDateTime changedAt;

    public RequestStatusHistory(){}
    public RequestStatusHistory(Request request , Status status){
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
