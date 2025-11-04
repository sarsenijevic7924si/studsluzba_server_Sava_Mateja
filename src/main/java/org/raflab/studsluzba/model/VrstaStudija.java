package org.raflab.studsluzba.model;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.LongToIntFunction;

import javax.persistence.*;

@Entity
@Data
public class VrstaStudija {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String skraceniNaziv;
    private String punNaziv;

}
