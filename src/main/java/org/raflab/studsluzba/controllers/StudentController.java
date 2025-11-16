package org.raflab.studsluzba.controllers;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudentIndeksRequest;
import org.raflab.studsluzba.controllers.request.StudentPodaciRequest;
import org.raflab.studsluzba.controllers.response.StudentIndeksResponse;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.maperi.StudentIndeksMaper;
import org.raflab.studsluzba.maperi.StudentPodaciMaper;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.services.StudentIndeksService;
import org.raflab.studsluzba.services.StudentPodaciService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/student")
public class StudentController {

	private final StudentPodaciService studentPodaciService;
	private final StudentIndeksService studentIndeksService;
	private final StudentPodaciMaper studentPodaciMaper;
	private final StudentIndeksMaper studentIndeksMaper;

	// -------------------- StudentPodaci --------------------
	@PostMapping("/add")
	public StudentPodaciResponse addStudentPodaci(@RequestBody StudentPodaciRequest request) {
		StudentPodaci saved = studentPodaciService.saveStudentPodaci(request);
		return studentPodaciMaper.toResponse(saved);
	}





	@GetMapping("/{id}")
	public StudentPodaciResponse getStudentPodaci(@PathVariable Long id) {
		StudentPodaci sp = studentPodaciService.findById(id);
		return studentPodaciMaper.toResponse(sp);
	}

	// -------------------- StudentIndeks --------------------
	@PostMapping("/indeks/add")
	public StudentIndeksResponse addStudentIndeks(@RequestBody StudentIndeksRequest request) {
		StudentIndeks saved = studentIndeksService.saveIndeks(request);
		return studentIndeksMaper.toResponse(saved);
	}

	@GetMapping("/indeks/{id}")
	public StudentIndeksResponse getStudentIndeks(@PathVariable Long id) {
		StudentIndeks si = studentIndeksService.findById(id);
		return studentIndeksMaper.toResponse(si);
	}

	//@GetMapping("/indeksi/{studentPodaciId}")
	//public List<StudentIndeksResponse> getIndeksiForStudentPodaci(@PathVariable Long studentPodaciId) {
		//return studentIndeksService.findIndeksiForStudentPodaciId(studentPodaciId)
			//	.stream()
			////	.map(studentIndeksMaper::toResponse)
			//	.collect(Collectors.toList());
	//}

	//@GetMapping("/indeks/search")
//	public Page<StudentIndeksResponse> searchIndeksi(@RequestParam(required = false) String ime,
	//												 @RequestParam(required = false) String prezime,
			//										 @RequestParam(required = false) String studProgram,
					///								 @RequestParam(required = false) Integer godina,
					//								 @RequestParam(required = false) Integer broj,
					//								 @RequestParam(defaultValue = "0") int page,
					//								 @RequestParam(defaultValue = "10") int size) {
	//	return studentIndeksService.searchIndeksi(ime, prezime, studProgram, godina, broj,
	//					PageRequest.of(page, size, Sort.by("id").descending()))
	//			.map(studentIndeksMaper::toResponse);
	//}

	@PutMapping("/indeks/update/{id}")
	public StudentIndeksResponse updateStudentIndeks(@PathVariable Long id,
													 @RequestBody StudentIndeksRequest request) {
		StudentIndeks updated = studentIndeksService.updateIndeks(id, request);
		return studentIndeksMaper.toResponse(updated);
	}

	@DeleteMapping("/indeks/delete/{id}")
	public void deleteStudentIndeks(@PathVariable Long id) {
		studentIndeksService.deleteIndeks(id);
	}
	@GetMapping("/indeks/podaci")
	public StudentPodaciResponse findByIndeks(
			@RequestParam int broj,
			@RequestParam int godina,
			@RequestParam String oznaka
	) {
		return studentIndeksService.findStudentPodaciByIndeks(broj, godina, oznaka);
	}


}