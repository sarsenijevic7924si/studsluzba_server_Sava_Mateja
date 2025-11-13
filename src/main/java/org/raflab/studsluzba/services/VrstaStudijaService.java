package org.raflab.studsluzba.services;

import org.raflab.studsluzba.controllers.request.VrstaStudijaRequest;
import org.raflab.studsluzba.controllers.response.VrstaStudijaResponse;
import org.raflab.studsluzba.maperi.VrstaStudijaMaper;
import org.raflab.studsluzba.model.VrstaStudija;
import org.raflab.studsluzba.repositories.VrstaStudijaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VrstaStudijaService {
    @Autowired
    private VrstaStudijaRespository vrstaStudijaRepository;


    public VrstaStudijaResponse create(VrstaStudijaRequest request) {
        VrstaStudija entity = VrstaStudijaMaper.toEntity(request);
        VrstaStudija saved = vrstaStudijaRepository.save(entity);
        return VrstaStudijaMaper.toResponse(saved);
    }


    public List<VrstaStudijaResponse> getAll() {
        return vrstaStudijaRepository.findAll()
                .stream()
                .map(VrstaStudijaMaper::toResponse)
                .collect(Collectors.toList());
    }


    public VrstaStudijaResponse getById(Long id) {
        VrstaStudija entity = vrstaStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VrstaStudija nije pronađena"));
        return VrstaStudijaMaper.toResponse(entity);
    }


    public VrstaStudijaResponse update(Long id, VrstaStudijaRequest request) {
        VrstaStudija entity = vrstaStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VrstaStudija nije pronađena"));

        entity.setSkraceniNaziv(request.getSkraceniNaziv());
        entity.setPunNaziv(request.getPunNaziv());

        VrstaStudija updated = vrstaStudijaRepository.save(entity);
        return VrstaStudijaMaper.toResponse(updated);
    }


    public void delete(Long id) {
        if (!vrstaStudijaRepository.existsById(id)) {
            throw new RuntimeException("VrstaStudija nije pronađena");
        }
        vrstaStudijaRepository.deleteById(id);
    }
}
