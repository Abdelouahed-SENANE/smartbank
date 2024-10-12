package ma.youcode.smartbank.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
//import org.hibernate.annotations.Immutable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "statuses")
//@Immutable
public class Status {

    @Id
    @Column(name = "statusname", nullable = false, unique = true)
    private String statusName;

    @OneToMany(mappedBy = "status")
    private List<History> histories = new ArrayList<>();

    public Status(){}

    public @NotNull String getStatusName() {
        return statusName;
    }

    public void setStatusName(@NotNull String statusName) {
        this.statusName = statusName;
    }

    public List<History> getStatusHistories() {
        return histories;
    }

    public void addHistories(History history) {
        histories.add(history);
    }
}
