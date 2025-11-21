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
    public UplataResponse createUplata(@RequestBody UplataRequest request) {
        return uplataService.createUplata(request);
    }


    @GetMapping("/all")
    public List<UplataResponse> getAllUplate() {
        return uplataService.getAllUplate();
    }


    @GetMapping("/{id}")
    public UplataResponse getUplataById(@PathVariable Long id) {
        return uplataService.getUplataById(id);
    }

    @PutMapping("/update/{id}")
    public UplataResponse updateUplata(@PathVariable Long id, @RequestBody UplataRequest request) {
        return uplataService.updateUplata(id,request);
    }


    @DeleteMapping("/delete/{id}")
    public void       vdeleteUplata(@PathVariable Long id) {
        uplataService.deleteUplata(id);
    }
}
