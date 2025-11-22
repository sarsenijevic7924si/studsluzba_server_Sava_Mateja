package org.raflab.studsluzba.controllers;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.NastavnikRequest;
import org.raflab.studsluzba.controllers.response.NastavnikResponse;
import org.raflab.studsluzba.maperi.NastavnikMaper;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.services.NastavnikService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@AllArgsConstructor //Spring automatski injektuje kroz konstruktor
@RequestMapping(path = "/api/nastavnik")
public class NastavnikController {

	final NastavnikService nastavnikService;
	
	@PostMapping(path = "/add")
	public NastavnikResponse addNewNastavnik(@RequestBody @Valid NastavnikRequest nastavnikRequest) {
		return nastavnikService.save(nastavnikRequest);
	}
	
	@GetMapping(path = "/all")
	public List<NastavnikResponse> getAllNastavnik() {
		return nastavnikService.findAll();
	}

	@GetMapping(path = "/{id}")
	public NastavnikResponse getNastavnikById(@PathVariable Long id) {


		return nastavnikService.findById(id);
	}
	
	@GetMapping(path = "/search")
	public NastavnikResponse search(
			@RequestParam(required = false) String ime,
			@RequestParam(required = false) String prezime){

        return nastavnikService.findByImeAndPrezime(ime, prezime);
	}
	@PatchMapping(path = "/update/{id}")
	public NastavnikResponse updateNastavnik(
			@PathVariable Long id,
			@RequestBody @Valid NastavnikRequest nastavnikRequest) {
		return nastavnikService.update(id, nastavnikRequest);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteNastavnik(@PathVariable Long id) {
		nastavnikService.delete(id);
	}
	
}
