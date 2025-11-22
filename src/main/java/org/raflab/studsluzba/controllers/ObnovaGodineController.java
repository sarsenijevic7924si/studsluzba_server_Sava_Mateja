package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.ObnovaGodineRequest;
import org.raflab.studsluzba.controllers.response.ObnovaGodineResponse;
import org.raflab.studsluzba.model.ObnovaGodine;
import org.raflab.studsluzba.services.ObnovaGodineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/obnova")

public class ObnovaGodineController {
    final ObnovaGodineService service;
    @GetMapping("/{indeksId}")
    public List<ObnovaGodineResponse> getObnove(@PathVariable Long indeksId) {
        return service.getObnoveZaStudenta(indeksId);
    }@PostMapping("/add")

        public ObnovaGodineResponse addObnova(@RequestBody ObnovaGodineRequest request) {
        return service.obnovi(request);
    }

}
