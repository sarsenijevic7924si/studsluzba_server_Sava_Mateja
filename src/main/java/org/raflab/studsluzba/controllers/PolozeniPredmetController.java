package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.PolozeniPredmetRequest;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.PolozenPredmetResponse;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.model.PolozeniPredmeti;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.services.PolozeniPredmetService;
import org.raflab.studsluzba.services.PredmetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/polozen-predmet")
public class PolozeniPredmetController {
    final PolozeniPredmetService predmetService;

    @GetMapping(path = "/all")
    public Iterable<PolozeniPredmeti> getPredmeti(){
        return predmetService.findAll();
    }

    @PostMapping(path = "/add")
    public PolozenPredmetResponse addPredmet(@RequestBody PolozeniPredmetRequest request) {
        return predmetService.dodajPolozeniIspit(request);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deletePolozeniPredmet(@PathVariable Long id) {
        predmetService.deletePolozeniPredmet(id);
    }
    @PatchMapping(path = "/update/{id}")
    public PolozenPredmetResponse patchPredmet(@PathVariable Long id, @RequestBody PolozeniPredmetRequest request) {
        return predmetService.updatePolozeniPredmet(id, request);
    }
}
