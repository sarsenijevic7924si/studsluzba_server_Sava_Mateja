package org.raflab.studsluzba.controllers;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.SlusaPredmetRequest;
import org.raflab.studsluzba.controllers.response.SlusaPredmetResponse;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.services.SlusaPredmetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slusa-predmet")
@RequiredArgsConstructor
public class SlusaPredmetController {

    private final SlusaPredmetService slusaPredmetService;

    @PostMapping("/add")
    public SlusaPredmetResponse add(@RequestBody SlusaPredmetRequest request) {
        return slusaPredmetService.saveSlusaPredmet(request);
    }


    @GetMapping("/{id}")
    public SlusaPredmetResponse findById(@PathVariable("id") Long id) {
        return slusaPredmetService.findById(id);
    }

    @GetMapping("/all")
    public List<SlusaPredmetResponse> findAll() {
        return slusaPredmetService.findAll();
    }
    @PutMapping("/update/{id}")
    public SlusaPredmetResponse update(@PathVariable Long id,
                                       @RequestBody SlusaPredmetRequest request) {
        return slusaPredmetService.updateSlusaPredmet(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        slusaPredmetService.deleteSlusaPredmet(id);
    }
}
