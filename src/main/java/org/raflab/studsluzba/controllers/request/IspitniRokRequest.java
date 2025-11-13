package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.raflab.studsluzba.model.Ispit;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
@Data
public class IspitniRokRequest {
    private LocalDate pocetakRoka;
    private LocalDate zavrsetakRoka;
    private Long skolskaGodinaId;
}
