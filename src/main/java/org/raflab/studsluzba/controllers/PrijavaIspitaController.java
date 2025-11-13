package org.raflab.studsluzba.controllers;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.PrijavaIspitaRequest;
import org.raflab.studsluzba.controllers.response.PrijavaIspitaResponse;
import org.raflab.studsluzba.services.PrijavaIspitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prijava-ispita")
@RequiredArgsConstructor
public class PrijavaIspitaController {
    private final PrijavaIspitaService prijavaIspitaService;

    @GetMapping("/all")
    public List<PrijavaIspitaResponse> getAll() {
        return prijavaIspitaService.findAll();
    }

    @GetMapping("/{id}")
    public PrijavaIspitaResponse getById(@PathVariable Long id) {
        return prijavaIspitaService.findById(id);
    }

    @PostMapping("/add")
    public PrijavaIspitaResponse create(@RequestBody PrijavaIspitaRequest request) {
        return prijavaIspitaService.createPrijavaIspita(request);
    }

    @PutMapping("/{id}")
    public PrijavaIspitaResponse update(@PathVariable Long id, @RequestBody PrijavaIspitaRequest request) {
        return prijavaIspitaService.updatePrijavaIspita(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        prijavaIspitaService.deleteById(id);
    }
}
