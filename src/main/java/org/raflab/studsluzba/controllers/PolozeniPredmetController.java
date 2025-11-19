package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.PolozeniPredmetRequest;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.PolozenPredmetResponse;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.model.PolozeniPredmeti;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.services.PolozeniPredmetService;
import org.raflab.studsluzba.services.PredmetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/predmet/polozeni")
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
    @GetMapping("/student_id/idndeks/")
    public List<PolozenPredmetResponse> getPolozeniByIndeks(@PathVariable Long indeksId) {
        return predmetService.findPolozeniByIndeks(indeksId);
    }
    @GetMapping("/StudenskiIndeks")
    public List<PolozenPredmetResponse> getPolozeniByStudentIndeks(
            @RequestParam int broj,
            @RequestParam int godina,
            @RequestParam String oznaka
    ) {
        return predmetService.findPolozeniByStudentIndeks(broj, godina, oznaka);
    }
    //findPolozeniByStudentIndeks
}
