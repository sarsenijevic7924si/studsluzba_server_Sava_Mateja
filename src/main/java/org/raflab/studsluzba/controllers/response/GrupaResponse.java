package org.raflab.studsluzba.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupaResponse {
    private Long id;
    private Long studijskiProgramID;
    private String studijskiProgramNaziv;
    private List<String> predmetiNazivi;

}
