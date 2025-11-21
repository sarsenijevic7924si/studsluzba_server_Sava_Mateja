package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.request.IspitniRokRequest;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.IspitResponse;
import org.raflab.studsluzba.controllers.response.IspitniRokResponse;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.services.IspitService;
import org.raflab.studsluzba.services.IspitniRokService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/ispitnirok")
public class IspitniRokController {
    final IspitniRokService ispitniService;
    @GetMapping("/all")
    public List<IspitniRokResponse> getAllRokovi() {
        return ispitniService.findAll();
    }
    @PostMapping(path = "/add")
    public IspitniRokResponse addIspit(@RequestBody IspitniRokRequest request) {
        return ispitniService.saveIspitniRok(request);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteRok(@PathVariable Long id) {
        ispitniService.deleteIspitniRok(id);
    }
    @PatchMapping(path = "/update/{id}")
    public IspitniRokResponse patchPredmet(@PathVariable Long id, @RequestBody IspitniRokRequest request) {
        return ispitniService.updateIspitniRok(id, request);
    }
    @GetMapping("/{id}")
    public IspitniRokResponse getRokId(@PathVariable Long id)
    {
        return ispitniService.findById(id);
    }
}
