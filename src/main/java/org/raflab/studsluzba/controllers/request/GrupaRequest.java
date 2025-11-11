package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import java.util.List;

@Data
public class GrupaRequest {
    private Long studijskiProgramID;
    private List<Long> predmetiID;
}
