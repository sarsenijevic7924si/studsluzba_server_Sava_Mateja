package org.raflab.studsluzba.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path="/api/studprogram")
public class StudijskiProgramiController {
	

	final StudijskiProgramRepository studijskiProgramiRepo;
	
	@GetMapping(path = "/all/sorted")
	public List<StudijskiProgram> getAllStudProgramiSortedDesc(){
		return studijskiProgramiRepo.getAllSortedByGodinaDesc();
	}

	@GetMapping(path="/oznaka/all")
	public Iterable<String> getAllStudProgramOznaka() {
		return studijskiProgramiRepo.findAllOznaka();
	}
}
