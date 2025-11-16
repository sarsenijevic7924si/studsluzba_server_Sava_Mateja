package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import org.raflab.studsluzba.model.Ispit;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
@Data
public class PrijavaIspitaResponse {
    private Long Id;
    private LocalDate datumPrijave;
    private Long ispitID;
    private Long studentId;
}
