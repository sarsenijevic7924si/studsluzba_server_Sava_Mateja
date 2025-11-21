package org.raflab.studsluzba.controllers;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudentIndeksRequest;
import org.raflab.studsluzba.controllers.request.StudentPodaciRequest;
import org.raflab.studsluzba.controllers.request.StudijskiProgramReqest;
import org.raflab.studsluzba.controllers.response.StudentIndeksResponse;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.controllers.response.StudijskiProgramResponse;
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

	//POdaci
	@PostMapping("/add")
	public StudentPodaciResponse addStudentPodaci(@RequestBody StudentPodaciRequest request) {
		return studentPodaciService.saveStudentPodaci(request);
	}

	@GetMapping("/{id}")
	public StudentPodaciResponse getStudentPodaci(@PathVariable Long id) {
		return studentPodaciService.getStudentById(id);
	}
	@GetMapping("/all")
	public List<StudentPodaciResponse> getStudentPodaci() {
		 return studentPodaciService.getAllStudents();
	}
	@DeleteMapping("/delete/{id}")
	public void DeletePodaci(@PathVariable Long id) {
		studentPodaciService.deleteStudent(id);
	}
	@PatchMapping("/update/{id}")
	public StudentPodaciResponse patch(@PathVariable Long id, @RequestBody StudentPodaciRequest request) {
		return studentPodaciService.updateStudent(id, request);
	}


	// -------------------- StudentIndeks --------------------
	@PostMapping("/indeks/add")
	public StudentIndeksResponse addStudentIndeks(@RequestBody StudentIndeksRequest request) {

		return studentIndeksService.saveIndeks(request);
	}

	@GetMapping("/indeks/{id}")
	public StudentIndeksResponse getStudentIndeks(@PathVariable Long id) {
		return studentIndeksService.findById(id);
	}
	@GetMapping("/indeks/aktivan/{id}")
	public StudentIndeksResponse getStudentAktivanIndeks(@PathVariable Long id) {
		return studentIndeksService.findByAktivanAndId(id);
	}
	@GetMapping("/indeks/all")
	public List<StudentIndeksResponse> getStudentIndeks() {
		return studentIndeksService.findAllIndeksi();
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

	@PatchMapping("/indeks/update/{id}")
	public StudentIndeksResponse updateStudentIndeks(@PathVariable Long id,
													 @RequestBody StudentIndeksRequest request) {

		return studentIndeksService.updateIndeks(id, request);
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
	)
	{
		return studentIndeksService.findStudentPodaciByIndeks(broj, godina, oznaka);
	}


}