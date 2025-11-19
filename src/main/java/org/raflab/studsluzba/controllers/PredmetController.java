package org.raflab.studsluzba.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.services.PredmetService;
import org.raflab.studsluzba.services.SlusaPredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/predmet")
public class PredmetController {
	
	final PredmetService predmetService;
	private final SlusaPredmetService slusaPredmetService;
	
	@GetMapping(path = "/all")
	public Iterable<Predmet> getPredmeti(){
		return predmetService.findAll();
	}
	
	@GetMapping(path = "/all/{godinaAkreditacije}")
	public List<Predmet> getPredmetiForGodinaAkreditacije(@PathVariable Integer godinaAkreditacije){
		return predmetService.getPredmetForGodinaAkreditacije(godinaAkreditacije);
	}
	@PostMapping(path = "/add")
	public PredmetResponse addPredmet(@RequestBody PredmetRequest request) {
		return predmetService.savePredmet(request);
	}
	@DeleteMapping(path = "/delete/{id}")
	public void deletePredmet(@PathVariable Long id) {
		predmetService.deletePredmet(id);
	}
	@PatchMapping(path = "/update/{id}")
	public PredmetResponse patchPredmet(@PathVariable Long id, @RequestBody PredmetRequest request) {
		return predmetService.updatePredmet(id, request);
	}
	@GetMapping(path = "/ne-polozeni/")
	public List<PredmetResponse> getNePolozeniPredmeti(
			@RequestParam int broj,
			@RequestParam int godina,
			@RequestParam String oznaka){
		return predmetService.getNePolozeniPredmeti(broj,godina,oznaka);
	}

}
