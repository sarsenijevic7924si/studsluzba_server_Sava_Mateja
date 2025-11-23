package org.raflab.studsluzba.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentIndeksRequest {
    @NonNull
    private int godina;
    @NotEmpty
    private String studProgramOznaka;
    private String nacinFinansiranja;
    @NotNull
    private boolean aktivan;
    private LocalDate vaziOd;
    @NotNull
    private Long studentId;
    @NotNull
    private int broj;

    private Integer ostvarenoEspb;
}