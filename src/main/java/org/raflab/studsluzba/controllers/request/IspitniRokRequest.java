package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.raflab.studsluzba.model.Ispit;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
@Data
public class IspitniRokRequest {
    @NotNull
    private LocalDate pocetakRoka;
    @NotNull
    private LocalDate zavrsetakRoka;
    @NotNull
    private Long skolskaGodinaId;
}
