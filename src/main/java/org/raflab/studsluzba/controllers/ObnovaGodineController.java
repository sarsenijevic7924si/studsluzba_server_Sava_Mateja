package org.raflab.studsluzba.controllers;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.ObnovaGodineRequest;
import org.raflab.studsluzba.controllers.response.ObnovaGodineResponse;
import org.raflab.studsluzba.services.ObnovaGodineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obnova")
@RequiredArgsConstructor
public class ObnovaGodineController {

    private final ObnovaGodineService service;

    @GetMapping("/{indeksId}")
    public List<ObnovaGodineResponse> getObnove(@PathVariable Long indeksId) {
        return service.getObnoveZaStudenta(indeksId);
    }

    @PostMapping("/add")
    public ObnovaGodineResponse addObnova(@RequestBody ObnovaGodineRequest request) {
        return service.obnovi(request);
    }

    @PatchMapping("/update/{id}")
    public ObnovaGodineResponse updateObnova(
            @PathVariable Long id,
            @RequestBody ObnovaGodineRequest request
    ) {
        return service.updateObnova(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteObnova(@PathVariable Long id) {
        service.deleteObnova(id);
        return "Obnova godine je uspešno obrisana.";
    }
}
