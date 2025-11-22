package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.UpisGodineRequest;
import org.raflab.studsluzba.controllers.response.UpisGodineResponse;
import org.raflab.studsluzba.maperi.UpisGodineMaper;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.UpisGodine;
import org.raflab.studsluzba.repositories.SkolskaGodinaRepository;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.raflab.studsluzba.repositories.UpisGodineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpisGodineService {

    private final UpisGodineRepository upisGodineRepository;
    private final UpisGodineMaper upisGodineMapper;
    private final StudentIndeksRepository studentIndeksRepository;
    private final SkolskaGodinaRepository skolskaGodinaRepository;

    // CREATE
    public UpisGodineResponse create(UpisGodineRequest request) {
        StudentIndeks si=studentIndeksRepository.findById(request.getStudentIndeksId())
                .orElseThrow(() -> new RuntimeException("StudentIndeks sa datim ID ne postoji."));
        SkolskaGodina sg=skolskaGodinaRepository.findById(request.getStudentIndeksId())
                .orElseThrow(() -> new RuntimeException("Skolska godina sa datim ID ne postoji."));
        UpisGodine upis = upisGodineMapper.toEntity(request,si,sg);
        upis = upisGodineRepository.save(upis);
        return upisGodineMapper.toResponse(upis);
    }

    // READ - get by id
    public UpisGodineResponse getById(Long id) {
        Optional<UpisGodine> optional = upisGodineRepository.findById(id);
        return optional.map(upisGodineMapper::toResponse).orElse(null);
    }

    // READ - get all
    public List<UpisGodineResponse> getAll() {
        return upisGodineRepository.findAll()
                .stream()
                .map(upisGodineMapper::toResponse)
                .collect(Collectors.toList());
    }

    // UPDATE

    // DELETE
    public boolean delete(Long id) {
        if (!upisGodineRepository.existsById(id)) return false;
        upisGodineRepository.deleteById(id);
        return true;
    }

    // CUSTOM — find by student indeks
    public List<UpisGodineResponse> findByStudentIndeks(Long indeksId) {
        return upisGodineRepository.findByStudentIndeksId(indeksId)
                .stream()
                .map(upisGodineMapper::toResponse)
                .collect(Collectors.toList());
    }
}
