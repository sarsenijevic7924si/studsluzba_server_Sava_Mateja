package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class PredmetService {

    final PredmetRepository predmetRepository;

    public Iterable<Predmet> findAll(){
        return predmetRepository.findAll();
    }

    public List<Predmet> getPredmetForGodinaAkreditacije(Integer godinaAkreditacije){
        return predmetRepository.getPredmetForGodinaAkreditacije(godinaAkreditacije);
    }

}
