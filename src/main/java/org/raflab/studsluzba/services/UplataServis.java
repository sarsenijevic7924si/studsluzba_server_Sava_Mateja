package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.UplataRequest;
import org.raflab.studsluzba.controllers.response.UplataResponse;
import org.raflab.studsluzba.controllers.response.VracaSumuResponse;
import org.raflab.studsluzba.maperi.UplataMaper;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.Uplata;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.raflab.studsluzba.repositories.UplataRepository;
import org.raflab.studsluzba.utils.UplataUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UplataServis {
    private final UplataRepository uplataRepository;
    private final StudentIndeksRepository studentIndeksRepository;
    private final UplataMaper uplataMapper;


    public UplataResponse createUplata(UplataRequest request) {
        StudentIndeks studentIndeks = studentIndeksRepository.findById(request.getStudentIndeksId())
                .orElseThrow(() -> new RuntimeException("StudentIndeks nije pronađen sa ID: " + request.getStudentIndeksId()));

        Uplata uplata = uplataMapper.toEntity(request, studentIndeks);
        Double srednjiKurs = UplataUtils.getSrednjiKursEUR();
        uplata.setSrednjiKurs(srednjiKurs);
        Uplata sacuvana = uplataRepository.save(uplata);
        return uplataMapper.toResponse(sacuvana);
    }


    public List<UplataResponse> getAllUplate() {
        return uplataRepository.findAll()
                .stream()
                .map(uplataMapper::toResponse)
                .collect(Collectors.toList());
    }


    public UplataResponse getUplataById(Long id) {
        Uplata uplata = uplataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Uplata nije pronađena sa ID: " + id));
        return uplataMapper.toResponse(uplata);
    }


    public UplataResponse updateUplata(Long id, UplataRequest request) {
        Uplata uplata = uplataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Uplata nije pronađena sa ID: " + id));

        StudentIndeks studentIndeks = studentIndeksRepository.findById(request.getStudentIndeksId())
                .orElseThrow(() -> new RuntimeException("StudentIndeks nije pronađen sa ID: " + request.getStudentIndeksId()));

        uplata.setStudentIndeks(studentIndeks);
        uplata.setDatumUplate(request.getDatumUplate());
        uplata.setIznosDinari(request.getIznosDinari());

        Uplata sacuvana = uplataRepository.save(uplata);
        return uplataMapper.toResponse(sacuvana);
    }


    public void deleteUplata(Long id) {
        if (!uplataRepository.existsById(id)) {
            throw new RuntimeException("Uplata nije pronađena sa ID: " + id);
        }
        uplataRepository.deleteById(id);
    }
    public VracaSumuResponse getPreostalaUplataEUR(Long studentIndeksId) {
        double sumaDinari = uplataRepository
                .findByStudentIndeks_Id(studentIndeksId)
                .stream()
                .mapToDouble(u -> u.getIznosDinari() != null ? u.getIznosDinari() : 0)
                .sum();


        double kurs = UplataUtils.getSrednjiKursEUR();
        Double skolirinaUeur=3000.0;

        double preostaloEur = skolirinaUeur - (sumaDinari /kurs);
        VracaSumuResponse vracaSumuResponse=new VracaSumuResponse();
        vracaSumuResponse.setEvri(preostaloEur);
        double preosaloDin=preostaloEur*kurs;
        vracaSumuResponse.setDinari(preosaloDin);

        return vracaSumuResponse;
    }
    public List<UplataResponse> getUplatePoIndeksu(Integer broj,Integer godina,String oznaka) {
        List<Uplata>uplataLista=uplataRepository.findByStudentIndeks(broj,godina,oznaka);
        return uplataLista
                .stream()
                .map(uplataMapper::toResponse)
                .collect(Collectors.toList());
    }
}
