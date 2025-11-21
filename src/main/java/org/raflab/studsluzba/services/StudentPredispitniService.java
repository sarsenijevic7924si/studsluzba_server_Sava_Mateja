package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudentPredispitniRequest;
import org.raflab.studsluzba.controllers.response.StudentPredispitniResponse;
import org.raflab.studsluzba.maperi.StudentPredispitniMaper;
import org.raflab.studsluzba.model.PredIspitnaObaveza;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.StudentPredispitni;
import org.raflab.studsluzba.repositories.PredIspitnaObavezaRepository;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.raflab.studsluzba.repositories.StudentPredispitniRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentPredispitniService {
    private final StudentPredispitniRepository studentPredispitniRepository;
    private final StudentIndeksRepository studentIndeksRepository;
    private final PredIspitnaObavezaRepository predIspitnaObavezaRepository;

    @Transactional(readOnly = true)
    public List<StudentPredispitniResponse> findAll() {
        return studentPredispitniRepository.findAll().stream()
                .map(StudentPredispitniMaper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StudentPredispitniResponse findById(Long id) {
        StudentPredispitni sp = studentPredispitniRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predispitni ne postoji"));
        return StudentPredispitniMaper.toResponse(sp);
    }

    @Transactional
    public StudentPredispitniResponse create(StudentPredispitniRequest request) {
        StudentIndeks studentIndeks = studentIndeksRepository.findById(request.getStudentIndeksId())
                .orElseThrow(() -> new RuntimeException("Student ne postoji"));

        PredIspitnaObaveza obaveza = predIspitnaObavezaRepository.findById(request.getPredIspitnaObavezaId())
                .orElseThrow(() -> new RuntimeException("Predispitna obaveza ne postoji"));

        StudentPredispitni sp = StudentPredispitniMaper.toEntity(request, studentIndeks, obaveza);

        StudentPredispitni saved = studentPredispitniRepository.save(sp);
        return StudentPredispitniMaper.toResponse(saved);
    }

    @Transactional
    public StudentPredispitniResponse update(Long id, StudentPredispitniRequest request) {
        StudentPredispitni sp = studentPredispitniRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predispitni ne postoji"));

        if (request.getStudentIndeksId() != null) {
            StudentIndeks studentIndeks = studentIndeksRepository.findById(request.getStudentIndeksId())
                    .orElseThrow(() -> new RuntimeException("Student ne postoji"));
            sp.setIdneks(studentIndeks);
        }

        if (request.getPredIspitnaObavezaId() != null) {
            PredIspitnaObaveza obaveza = predIspitnaObavezaRepository.findById(request.getPredIspitnaObavezaId())
                    .orElseThrow(() -> new RuntimeException("Predispitna obaveza ne postoji"));
            sp.setPredIspitnaObaveza(obaveza);
        }

        if (request.getBrojPoena() != null) {
            sp.setBrojPoena(request.getBrojPoena());
        }

        StudentPredispitni updated = studentPredispitniRepository.save(sp);
        return StudentPredispitniMaper.toResponse(updated);
    }

    @Transactional
    public void deleteById(Long id) {
        studentPredispitniRepository.deleteById(id);
    }
}
