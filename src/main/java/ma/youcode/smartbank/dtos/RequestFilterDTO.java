package ma.youcode.smartbank.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class RequestFilterDTO {

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotNull(message = "date cannot be empty")
    private LocalDate creationDate;

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public  String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }
}
