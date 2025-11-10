package org.raflab.studsluzba.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.raflab.studsluzba.model.IspitniRok;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.PrijavaIspita;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IspitRequest {
    @NotNull
    private LocalDate datumOdrzavanja;
    @NotNull
    private LocalTime vremePocetka;

    private Boolean jeZakljucen=false;

    @NotNull
    private Long nastavnikId;

    @NotNull
    private Long predmetId;

    @NotNull
    private Long ispitniRokId;

}
