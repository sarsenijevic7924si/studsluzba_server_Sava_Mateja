package org.raflab.studsluzba.controllers;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.SrednjaSkolaRequest;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.services.SrednjaSkolaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/srednja-skola")
@RequiredArgsConstructor
public class SrednjaSkolaController {
    private final SrednjaSkolaService srednjaSkolaService;
    @GetMapping("/all")
    public List<SrednjaSkolaResponse> getAll() {
        return srednjaSkolaService.findAll();
    }


    @GetMapping("/{id}")
    public SrednjaSkolaResponse getById(@PathVariable Long id) {
        return srednjaSkolaService.findById(id);
    }


    @PostMapping("/add")
    public SrednjaSkolaResponse addSrednjaSkola(@RequestBody SrednjaSkolaRequest request) {
        return srednjaSkolaService.saveSrednjaSkola(request);
    }


    @PatchMapping("/update/{id}")
    public SrednjaSkolaResponse updateSrednjaSkola(@PathVariable Long id,
                                                   @RequestBody SrednjaSkolaRequest request) {
        return srednjaSkolaService.updateSrednjaSkola(id, request);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteSrednjaSkola(@PathVariable Long id) {
        srednjaSkolaService.deleteSrednjaSkola(id);
    }
}
