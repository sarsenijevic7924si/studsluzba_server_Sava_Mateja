package org.raflab.studsluzba.controllers;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.UpisGodineRequest;
import org.raflab.studsluzba.controllers.response.UpisGodineResponse;
import org.raflab.studsluzba.services.UpisGodineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/upis-godine")
@RequiredArgsConstructor
public class UpisGodineController {

    private final UpisGodineService upisGodineService;

    // CREATE
    @PostMapping("/add")
    public UpisGodineResponse create(@RequestBody UpisGodineRequest request) {
        return upisGodineService.create(request);
    }

    // GET by id
    @GetMapping("/{id}")
    public UpisGodineResponse getById(@PathVariable Long id) {
        return upisGodineService.getById(id);
    }

    // GET all
    @GetMapping("/all")
    public List<UpisGodineResponse> getAll() {
        return upisGodineService.getAll();
    }

    // UPDATE


    // DELETE
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return upisGodineService.delete(id);
    }

    // CUSTOM — find by student indeks
    @GetMapping("/by-student/{indeksId}")
    public List<UpisGodineResponse> findByStudent(
            @PathVariable Long indeksId) {

        return upisGodineService.findByStudentIndeks(indeksId);
    }
}
