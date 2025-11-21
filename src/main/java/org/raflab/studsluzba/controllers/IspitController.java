package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.request.IspitniRokRequest;
import org.raflab.studsluzba.controllers.request.NastavnikRequest;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.*;
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

    @GetMapping("/{id}")
    public IspitResponse getAllIspiti(@PathVariable Long id) {
        return ispitService.findById(id);
    }
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
    @GetMapping("/{ispitId}/prijavljeni")
    public List<StudentIndeksResponse> getPrijavljeni(@PathVariable Long ispitId) {
        return ispitService.getPrijavljeniStudenti(ispitId);
    }
    @GetMapping("/prosek/{ispitId}")
    public Double getAvg(@PathVariable Long ispitId) {
        return ispitService.avg(ispitId);
    }
    @GetMapping("/polozeni")
    public List<IspitResponse> getPolozeniByStudentIndeks(
            @RequestParam int broj,
            @RequestParam int godina,
            @RequestParam String oznaka
    ) {
        return ispitService.finPolozeniIpsit(broj, godina, oznaka);
    }
    @GetMapping("/nepolozeni")
    public List<IspitResponse> getNePolozeniByStudentIndeks(
            @RequestParam int broj,
            @RequestParam int godina,
            @RequestParam String oznaka
    ) {
        return ispitService.finNePolozeniIpsit(broj, godina, oznaka);
    }
}
