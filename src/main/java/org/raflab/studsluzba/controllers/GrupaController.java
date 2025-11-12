package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.GrupaRequest;
import org.raflab.studsluzba.controllers.response.GrupaResponse;
import org.raflab.studsluzba.services.GrupaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path="/api/grupa")
public class GrupaController {
    private final GrupaService grupaService;


    @PostMapping("/add")
    public ResponseEntity<GrupaResponse> createGrupa(@RequestBody GrupaRequest request) {
        if (request.getStudijskiProgramID() == null) {
            throw new RuntimeException("Morate uneti studijskiProgramId!");
        }
        GrupaResponse response = grupaService.createGrupa(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<List<GrupaResponse>> getAllGrupe() {
        List<GrupaResponse> grupe = grupaService.getAllGrupe();
        return ResponseEntity.ok(grupe);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GrupaResponse> getGrupaById(@PathVariable Long id) {
        GrupaResponse response = grupaService.getGrupaById(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/udpate/{id}")
    public ResponseEntity<GrupaResponse> updateGrupa(@PathVariable Long id, @RequestBody GrupaRequest request) {
        GrupaResponse response = grupaService.updateGrupa(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGrupa(@PathVariable Long id) {
        grupaService.deleteGrupa(id);
        return ResponseEntity.noContent().build();
    }
}
