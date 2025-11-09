package org.raflab.studsluzba.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudijskiProgramReqest;
import org.raflab.studsluzba.controllers.response.StudijskiProgramResponse;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.raflab.studsluzba.services.StudijskiProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path="/api/studprogram")
public class StudijskiProgramiController {
	


	private final StudijskiProgramService service;

	@PostMapping("/add")
	public StudijskiProgramResponse add(@RequestBody StudijskiProgramReqest request) {
		return service.saveStudijskiProgram(request);
	}

	@PatchMapping("/update/{id}")
	public StudijskiProgramResponse patch(@PathVariable Long id, @RequestBody StudijskiProgramReqest request) {
		return service.updateStudijskiProgram(id, request);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteStudijskiProgram(id);
	}

	@GetMapping("/all")
	public List<StudijskiProgram> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public StudijskiProgramResponse findById(@PathVariable Long id) {
		return service.findById(id);
	}
}
