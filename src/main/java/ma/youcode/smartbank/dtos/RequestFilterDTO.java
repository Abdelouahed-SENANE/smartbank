package ma.youcode.smartbank.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class RequestFilterDTO {

    private String statusName;

    private LocalDate creationDate;

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public  String getStatusName() {
        return statusName;
    }

    public void setStatusName( String name) {
        this.statusName = name;
    }
}
