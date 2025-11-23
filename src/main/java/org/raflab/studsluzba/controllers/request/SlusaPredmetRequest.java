package org.raflab.studsluzba.controllers.request;


import lombok.Data;
import lombok.NonNull;

@Data
public class SlusaPredmetRequest {
    @NonNull
    private Long studentIndeksId;
    @NonNull
    private Long drziPredmetId;
}

