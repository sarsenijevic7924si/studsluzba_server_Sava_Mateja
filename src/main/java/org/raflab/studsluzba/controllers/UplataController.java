package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.UplataRequest;
import org.raflab.studsluzba.controllers.response.UplataResponse;
import org.raflab.studsluzba.services.UplataServis;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path="/api/uplata")
public class UplataController {
    private final UplataServis uplataService;


    @PostMapping("/add")
    public ResponseEntity<UplataResponse> createUplata(@RequestBody UplataRequest request) {
        UplataResponse response = uplataService.createUplata(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<List<UplataResponse>> getAllUplate() {
        List<UplataResponse> uplate = uplataService.getAllUplate();
        return ResponseEntity.ok(uplate);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UplataResponse> getUplataById(@PathVariable Long id) {
        UplataResponse response = uplataService.getUplataById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UplataResponse> updateUplata(@PathVariable Long id, @RequestBody UplataRequest request) {
        UplataResponse response = uplataService.updateUplata(id, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUplata(@PathVariable Long id) {
        uplataService.deleteUplata(id);
        return ResponseEntity.noContent().build();
    }
}
