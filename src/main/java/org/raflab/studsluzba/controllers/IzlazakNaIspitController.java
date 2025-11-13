package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IzlazakNaIspitRequest;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.IzlazakNaIspitResponse;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.model.IzlazakNaIspit;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.repositories.IzlazakNaIspitRepository;
import org.raflab.studsluzba.services.IzlazakNaIspitService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/izlazak-na-ispit")
public class IzlazakNaIspitController {
    final IzlazakNaIspitService izlazakNaIspitService;
    @GetMapping(path = "/all")
    public Iterable<IzlazakNaIspitResponse> getAllIzlazak(){
       return izlazakNaIspitService.findall();
    }
    @PostMapping(path = "/add")
    public IzlazakNaIspitResponse addIzlazak(@RequestBody IzlazakNaIspitRequest request) {
        return izlazakNaIspitService.saveIzlazak(request);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteIzlazak(@PathVariable Long id) {
        izlazakNaIspitService.deleteIzlazak(id);
    }
    @PatchMapping(path = "/update/{id}")
    public IzlazakNaIspitResponse patchPredmet(@PathVariable Long id, @RequestBody IzlazakNaIspitRequest request) {
        return izlazakNaIspitService.updateIzlazakNaIspit(id, request);
    }
}
