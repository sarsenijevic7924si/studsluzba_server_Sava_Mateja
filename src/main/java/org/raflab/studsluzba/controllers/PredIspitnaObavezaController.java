package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.PredIspitnaObavezaRequest;
import org.raflab.studsluzba.controllers.response.PredIpsitnaObavezaResponse;
import org.raflab.studsluzba.services.PredIspitnaObavezaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path="/api/pred-ispitna-obaveza")
public class PredIspitnaObavezaController {
    private final PredIspitnaObavezaService predIspitnaObavezaService;

    @PostMapping("/add")
    public PredIpsitnaObavezaResponse create(@RequestBody PredIspitnaObavezaRequest request) {
        return predIspitnaObavezaService.create(request);
    }


    @GetMapping("/all")
    public List<PredIpsitnaObavezaResponse> getAll() {
        return predIspitnaObavezaService.getAll();
    }


    @GetMapping("/{id}")
    public PredIpsitnaObavezaResponse getById(@PathVariable Long id) {
        return predIspitnaObavezaService.getById(id);
    }


    @PatchMapping("/update/{id}")
    public PredIpsitnaObavezaResponse update(@PathVariable Long id,
                                                             @RequestBody PredIspitnaObavezaRequest request) {
        return predIspitnaObavezaService.update(id,request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        predIspitnaObavezaService.delete(id);
    }
}
