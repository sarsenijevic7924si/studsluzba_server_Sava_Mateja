package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.VrstaStudijaRequest;
import org.raflab.studsluzba.controllers.response.VrstaStudijaResponse;
import org.raflab.studsluzba.services.VrstaStudijaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/vrsta-studija")
public class VrstaStudijaController {
    private final VrstaStudijaService vrstaStudijaService;

    @PostMapping("/add")
    public ResponseEntity<VrstaStudijaResponse> create(@RequestBody VrstaStudijaRequest request) {
        VrstaStudijaResponse response = vrstaStudijaService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VrstaStudijaResponse>> getAll() {
        List<VrstaStudijaResponse> list = vrstaStudijaService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VrstaStudijaResponse> getById(@PathVariable Long id) {
        VrstaStudijaResponse response = vrstaStudijaService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VrstaStudijaResponse> update(@PathVariable Long id,
                                                       @RequestBody VrstaStudijaRequest request) {
        VrstaStudijaResponse response = vrstaStudijaService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vrstaStudijaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
