package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class SkolskaGodinaRequest {
    @NonNull
    private Integer godina;
    @NonNull
    private Boolean aktivan;
}
