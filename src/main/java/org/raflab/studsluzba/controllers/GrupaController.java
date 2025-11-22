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
    public  GrupaResponse createGrupa(@RequestBody GrupaRequest request) {

        return grupaService.createGrupa(request);
    }


    @GetMapping("/all")
    public List<GrupaResponse> getAllGrupe() {
        return grupaService.getAllGrupe();
    }


    @GetMapping("/{id}")
    public GrupaResponse getGrupaById(@PathVariable Long id) {
        return grupaService.getGrupaById(id);
    }


    @PatchMapping("/udpate/{id}")
    public GrupaResponse updateGrupa(@PathVariable Long id, @RequestBody GrupaRequest request) {
        return grupaService.updateGrupa(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGrupa(@PathVariable Long id) {
        grupaService.deleteGrupa(id);
    }
}
