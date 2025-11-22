package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;

import org.raflab.studsluzba.controllers.request.DrziPredmetRequest;
import org.raflab.studsluzba.controllers.response.DrziPredmetResponse;
import org.raflab.studsluzba.services.DrziPredmetService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/drzi-predmet")
@AllArgsConstructor
public class DrziPredmetController {

    final DrziPredmetService drziPredmetService;

    @PostMapping(path = "/add")
    public void saveDrziPredmet(@RequestBody DrziPredmetRequest request) {
        drziPredmetService.saveDrziPredmet(request);
    }
    @GetMapping("/all")
    public List<DrziPredmetResponse> getAll() {
        return drziPredmetService.findAll();
    }


    @GetMapping("/{id}")
    public DrziPredmetResponse getById(@PathVariable Long id) {
        return drziPredmetService.findById(id);
    }


    @PatchMapping("/update/{id}")
    public DrziPredmetResponse update(
            @PathVariable Long id,
            @RequestBody @Valid DrziPredmetRequest request) {
        return drziPredmetService.update(id, request);
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        drziPredmetService.delete(id);
        return "Uspešno obrisan DrziPredmet sa ID = " + id;
    }


}
