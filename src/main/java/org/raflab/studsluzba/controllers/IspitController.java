package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.request.NastavnikRequest;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.IspitResponse;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.maperi.IspitMaper;
import org.raflab.studsluzba.maperi.NastavnikMaper;
import org.raflab.studsluzba.model.Ispit;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.services.IspitService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/ispit")
public class IspitController {
    final IspitService ispitService;
    @GetMapping("/all")
    public List<IspitResponse> getAllIspiti() {
        return ispitService.findAll();
    }
    @PostMapping(path = "/add")
    public IspitResponse addIspit(@RequestBody IspitRequest request) {
        return ispitService.saveIspit(request);
    }
}
