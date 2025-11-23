package org.raflab.studsluzba.controllers;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudentPredispitniRequest;
import org.raflab.studsluzba.controllers.response.StudentPredispitniResponse;
import org.raflab.studsluzba.model.StudentPredispitni;
import org.raflab.studsluzba.repositories.StudentPredispitniRepository;
import org.raflab.studsluzba.services.StudentPredispitniService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/predispitni")
@RequiredArgsConstructor
public class StudentPredispitniController {
    private final StudentPredispitniService service;

    @GetMapping("/all")
    public List<StudentPredispitniResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StudentPredispitniResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public StudentPredispitniResponse create(@RequestBody StudentPredispitniRequest request) {
        return service.create(request);
    }

    @PatchMapping("/update/{id}")
    public StudentPredispitniResponse update(@PathVariable Long id,
                                             @RequestBody StudentPredispitniRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
