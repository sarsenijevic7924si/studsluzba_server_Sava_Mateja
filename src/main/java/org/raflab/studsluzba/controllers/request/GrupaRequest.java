package org.raflab.studsluzba.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrupaRequest {
    @NotNull
    private Long studijskiProgramID;
    @NotNull
    private List<Long> predmetiID;
}
