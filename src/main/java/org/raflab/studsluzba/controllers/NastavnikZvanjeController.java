package org.raflab.studsluzba.controllers;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
import org.raflab.studsluzba.controllers.response.NastavnikZvanjeResponse;
import org.raflab.studsluzba.maperi.NastavnikZvanjeMaper;
import org.raflab.studsluzba.model.NastavnikZvanje;
import org.raflab.studsluzba.services.NastavnikZvanjeServis;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/nastavnik/zvanje")
@RequiredArgsConstructor
public class NastavnikZvanjeController {
    private final NastavnikZvanjeServis zvanjeService;

    @PostMapping("/add")
    public NastavnikZvanjeResponse addZvanje(@RequestBody NastavnikZvanjeRequest request) {
        return zvanjeService.saveNastavnikZvanje(request);
    }

    @GetMapping("/{id}")
    public NastavnikZvanjeResponse getZvanje(@PathVariable Long id) {
        return zvanjeService.findByIdResponse(id);
    }

    @GetMapping("/nastavnik/{nastavnikId}")
    public List<NastavnikZvanjeResponse> getAllForNastavnik(@PathVariable Long nastavnikId) {
        return zvanjeService.findAllForNastavnikResponse(nastavnikId);
    }

    @PatchMapping("/update/{id}")
    public NastavnikZvanjeResponse updateZvanje(@PathVariable Long id, @RequestBody NastavnikZvanjeRequest request) {
        return zvanjeService.updateNastavnikZvanje(id,request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteZvanje(@PathVariable Long id) {
        zvanjeService.deleteNastavnikZvanje(id);
    }
}
