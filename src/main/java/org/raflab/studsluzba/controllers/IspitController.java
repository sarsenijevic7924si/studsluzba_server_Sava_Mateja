package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.request.IspitniRokRequest;
import org.raflab.studsluzba.controllers.request.NastavnikRequest;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.IspitResponse;
import org.raflab.studsluzba.controllers.response.IspitniRokResponse;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.controllers.response.PrijavaIspitaResponse;
import org.raflab.studsluzba.maperi.IspitMaper;
import org.raflab.studsluzba.maperi.NastavnikMaper;
import org.raflab.studsluzba.model.Ispit;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.services.IspitService;
import org.raflab.studsluzba.services.PrijavaIspitaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/ispit")
public class IspitController {
    final IspitService ispitService;
    public final PrijavaIspitaService prijavaIspitaService;

    @GetMapping("/all")
    public List<IspitResponse> getAllIspiti() {
        return ispitService.findAll();
    }
    @PostMapping(path = "/add")
    public IspitResponse addIspit(@RequestBody IspitRequest request) {
        return ispitService.saveIspit(request);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteIspit(@PathVariable Long id) {
        ispitService.deleteIspit(id);
    }
    @PatchMapping(path = "/update/{id}")
    public IspitResponse patchPredmet(@PathVariable Long id, @RequestBody IspitRequest request) {
        return ispitService.updateIspit(id, request);
    }

}
