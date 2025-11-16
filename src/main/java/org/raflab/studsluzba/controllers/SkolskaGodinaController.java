package org.raflab.studsluzba.controllers;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.SkolskaGodinaRequest;
import org.raflab.studsluzba.controllers.response.SkolskaGodinaResponse;
import org.raflab.studsluzba.services.SkolskaGodinaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skolskagodina")
@RequiredArgsConstructor
public class SkolskaGodinaController {
    private final SkolskaGodinaService skolskaGodinaService;

    @PostMapping("/add")
    public SkolskaGodinaResponse add(@RequestBody SkolskaGodinaRequest request) {
        return skolskaGodinaService.add(request);
    }

    @GetMapping("/all")
    public List<SkolskaGodinaResponse> getAll() {
        return skolskaGodinaService.getAll();
    }

    @GetMapping("/{id}")
    public SkolskaGodinaResponse getById(@PathVariable Long id) {
        return skolskaGodinaService.getById(id);
    }

    @PutMapping("/update/{id}")
    public SkolskaGodinaResponse update(@PathVariable Long id,
                                        @RequestBody SkolskaGodinaRequest request) {
        return skolskaGodinaService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        skolskaGodinaService.delete(id);
    }
}
