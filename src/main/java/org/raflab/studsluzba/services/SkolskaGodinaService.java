package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.SkolskaGodinaRequest;
import org.raflab.studsluzba.controllers.response.SkolskaGodinaResponse;
import org.raflab.studsluzba.maperi.SkolskaGodinaMaper;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.SkolskaGodinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SkolskaGodinaService {
    private final SkolskaGodinaRepository skolskaGodinaRepository;
    private final SkolskaGodinaMaper skolskaGodinaMapper;

    public SkolskaGodinaResponse add(SkolskaGodinaRequest request) {
        SkolskaGodina sg = skolskaGodinaMapper.toEntity(request);
        skolskaGodinaRepository.save(sg);
        return skolskaGodinaMapper.toResponse(sg);
    }

    public List<SkolskaGodinaResponse> getAll() {
        return skolskaGodinaRepository.findAll()
                .stream()
                .map(skolskaGodinaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SkolskaGodinaResponse getById(Long id) {
        SkolskaGodina sg = skolskaGodinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skolska godina nije pronadjena"));
        return skolskaGodinaMapper.toResponse(sg);
    }

    public SkolskaGodinaResponse update(Long id, SkolskaGodinaRequest request) {
        SkolskaGodina sg = skolskaGodinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skolska godina nije pronadjena"));

        sg.setGodina(request.getGodina());
        sg.setAktivan(request.getAktivan());

        skolskaGodinaRepository.save(sg);
        return skolskaGodinaMapper.toResponse(sg);
    }

    public void delete(Long id) {
        skolskaGodinaRepository.deleteById(id);
    }
}
