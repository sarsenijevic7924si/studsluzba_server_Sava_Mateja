package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class RezultatiStudentiRequest {
    @NonNull
    private Long StudentIndeksId;
}
