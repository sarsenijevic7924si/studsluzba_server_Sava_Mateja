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
    public ResponseEntity<PredIpsitnaObavezaResponse> create(@RequestBody PredIspitnaObavezaRequest request) {
        PredIpsitnaObavezaResponse response = predIspitnaObavezaService.create(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<List<PredIpsitnaObavezaResponse>> getAll() {
        List<PredIpsitnaObavezaResponse> list = predIspitnaObavezaService.getAll();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PredIpsitnaObavezaResponse> getById(@PathVariable Long id) {
        PredIpsitnaObavezaResponse response = predIspitnaObavezaService.getById(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<PredIpsitnaObavezaResponse> update(@PathVariable Long id,
                                                             @RequestBody PredIspitnaObavezaRequest request) {
        PredIpsitnaObavezaResponse response = predIspitnaObavezaService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        predIspitnaObavezaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
