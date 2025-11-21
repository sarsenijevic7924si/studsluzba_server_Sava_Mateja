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
    public VrstaStudijaResponse create(@RequestBody VrstaStudijaRequest request) {
        return vrstaStudijaService.create(request);
    }

    @GetMapping("/all")
    public List<VrstaStudijaResponse> getAll() {
        return vrstaStudijaService.getAll();
    }

    @GetMapping("/{id}")
    public VrstaStudijaResponse getById(@PathVariable Long id) {
        return vrstaStudijaService.getById(id);
    }

    @PatchMapping("/update/{id}")
    public VrstaStudijaResponse update(@PathVariable Long id,
                                                       @RequestBody VrstaStudijaRequest request) {
        return vrstaStudijaService.update(id,request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        vrstaStudijaService.delete(id);
    }
}
