package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudentPodaciRequest;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.maperi.StudentPodaciMaper;
import org.raflab.studsluzba.maperi.StudijskiProgramMaper;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.repositories.StudentPodaciRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentPodaciService {
    private final StudentPodaciRepository studentPodaciRepository;

    @Transactional
    public StudentPodaci saveStudentPodaci(StudentPodaciRequest request) {
        StudentPodaci entity = StudentPodaciMaper.toEntity(request);
        return studentPodaciRepository.save(entity);
    }



    @Transactional
    public StudentPodaciResponse updateStudent(Long id, StudentPodaciRequest request) {
        Optional<StudentPodaci> optionalStudent = studentPodaciRepository.findById(id);
        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student sa ID " + id + " ne postoji");
        }

        StudentPodaci student = optionalStudent.get();


        if (request.getIme() != null) student.setIme(request.getIme());
        if (request.getPrezime() != null) student.setPrezime(request.getPrezime());
        if (request.getSrednjeIme() != null) student.setSrednjeIme(request.getSrednjeIme());
        if (request.getJmbg() != null) student.setJmbg(request.getJmbg());
        if (request.getDatumRodjenja() != null) student.setDatumRodjenja(request.getDatumRodjenja());
        if (request.getMestoRodjenja() != null) student.setMestoRodjenja(request.getMestoRodjenja());
        if (request.getMestoPrebivalista() != null) student.setMestoPrebivalista(request.getMestoPrebivalista());
        if (request.getDrzavaRodjenja() != null) student.setDrzavaRodjenja(request.getDrzavaRodjenja());
        if (request.getDrzavljanstvo() != null) student.setDrzavljanstvo(request.getDrzavljanstvo());
        if (request.getNacionalnost() != null) student.setNacionalnost(request.getNacionalnost());
        if (request.getPol() != null) student.setPol(request.getPol());
        if (request.getAdresa() != null) student.setAdresa(request.getAdresa());
        if (request.getBrojTelefonaMobilni() != null) student.setBrojTelefonaMobilni(request.getBrojTelefonaMobilni());
        if (request.getBrojTelefonaFiksni() != null) student.setBrojTelefonaFiksni(request.getBrojTelefonaFiksni());
        if (request.getEmail() != null) student.setEmail(request.getEmail());
        if (request.getBrojLicneKarte() != null) student.setBrojLicneKarte(request.getBrojLicneKarte());
        if (request.getLicnuKartuIzdao() != null) student.setLicnuKartuIzdao(request.getLicnuKartuIzdao());
        if (request.getMestoStanovanja() != null) student.setMestoStanovanja(request.getMestoStanovanja());
        if (request.getAdresaStanovanja() != null) student.setAdresaStanovanja(request.getAdresaStanovanja());

        StudentPodaci updated = studentPodaciRepository.save(student);
        return StudentPodaciMaper.toResponse(updated);
    }


    @Transactional
    public void deleteStudent(Long id) {
        if (!studentPodaciRepository.existsById(id)) {
            throw new RuntimeException("Student sa ID " + id + " ne postoji");
        }
        studentPodaciRepository.deleteById(id);
    }

    // Get all students
    public List<StudentPodaciResponse> getAllStudents() {
        return studentPodaciRepository.findAll().stream()
                .map(StudentPodaciMaper::toResponse)
                .collect(Collectors.toList());
    }

    // Get student by ID
    public StudentPodaciResponse getStudentById(Long id) {
        StudentPodaci student = studentPodaciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student sa ID " + id + " ne postoji"));
        return StudentPodaciMaper.toResponse(student);
    }
    public StudentPodaci findById(Long id) {
        Optional<StudentPodaci> sp = studentPodaciRepository.findById(id);
        return sp.orElse(null);
    }

    public List<StudentPodaci> findAll() {
        return studentPodaciRepository.findAll();
    }

    public Page<StudentPodaci> findAllPaginated(Pageable pageable) {
        return studentPodaciRepository.findAll(pageable);
    }

}

