package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.NastavnikRequest;
import org.raflab.studsluzba.controllers.response.NastavnikResponse;
import org.raflab.studsluzba.maperi.NastavnikMaper;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.NastavnikZvanje;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NastavnikService {

    final NastavnikRepository nastavnikRepository;
    final NastavnikZvanjeServis nastavnikZvanjeServis;


    public NastavnikResponse save(NastavnikRequest nastavnikRequest) {
        Nastavnik nastavnik=NastavnikMaper.toNastavnik(nastavnikRequest);
        return NastavnikMaper.toNastavnikResponse(nastavnikRepository.save(nastavnik));
    }

    public List<NastavnikResponse> findAll() {
        List<Nastavnik> nastavnici=nastavnikRepository.findAll();
        return nastavnici.stream().map(NastavnikMaper::toNastavnikResponse).collect(Collectors.toList());
    }

    public NastavnikResponse findById(Long id) {
        Nastavnik nastavnik=nastavnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nastavnik nije pronađena"));
        return NastavnikMaper.toNastavnikResponse(nastavnik);
    }

    public NastavnikResponse findByImeAndPrezime(String ime, String prezime) {
        Nastavnik nastavnik=nastavnikRepository.findByImeAndPrezime(ime,prezime)
                .orElseThrow(() -> new RuntimeException("Nastavnik nije pronađena"));
        return NastavnikMaper.toNastavnikResponse(nastavnik);

    }
    public NastavnikResponse update(Long id, NastavnikRequest nastavnikRequest) {
        Nastavnik existing = nastavnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nastavnik nije pronađen"));


        existing.setIme(nastavnikRequest.getIme());
        existing.setPrezime(nastavnikRequest.getPrezime());
        existing.setSrednjeIme(nastavnikRequest.getSrednjeIme());
        existing.setEmail(nastavnikRequest.getEmail());
        existing.setBrojTelefona(nastavnikRequest.getBrojTelefona());
        existing.setAdresa(nastavnikRequest.getAdresa());
        existing.setZvanja(nastavnikRequest.getZvanja());
        existing.setDatumRodjenja(nastavnikRequest.getDatumRodjenja());
        existing.setPol(nastavnikRequest.getPol());
        existing.setJmbg(nastavnikRequest.getJmbg());

        return NastavnikMaper.toNastavnikResponse(nastavnikRepository.save(existing));
    }


    public void delete(Long id) {

        nastavnikRepository.deleteById(id);


    }
}
