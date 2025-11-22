package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.StudentPodaciRequest;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.model.SrednjaSkola;
import org.raflab.studsluzba.model.StudentPodaci;
import org.springframework.stereotype.Component;

@Component
public class StudentPodaciMaper {
    public static StudentPodaci toEntity(StudentPodaciRequest request, SrednjaSkola srednjaSkola) {
        if (request == null) return null;

        StudentPodaci student = new StudentPodaci();
        student.setIme(request.getIme());
        student.setPrezime(request.getPrezime());
        student.setSrednjeIme(request.getSrednjeIme());
        student.setJmbg(request.getJmbg());
        student.setDatumRodjenja(request.getDatumRodjenja());
        student.setMestoRodjenja(request.getMestoRodjenja());
        student.setMestoPrebivalista(request.getMestoPrebivalista());
        student.setDrzavaRodjenja(request.getDrzavaRodjenja());
        student.setDrzavljanstvo(request.getDrzavljanstvo());
        student.setNacionalnost(request.getNacionalnost());
        student.setPol(request.getPol());
        student.setAdresa(request.getAdresa());
        student.setBrojTelefonaMobilni(request.getBrojTelefonaMobilni());
        student.setBrojTelefonaFiksni(request.getBrojTelefonaFiksni());
        student.setEmail(request.getEmail());
        student.setBrojLicneKarte(request.getBrojLicneKarte());
        student.setLicnuKartuIzdao(request.getLicnuKartuIzdao());
        student.setMestoStanovanja(request.getMestoStanovanja());
        student.setAdresaStanovanja(request.getAdresaStanovanja());
        student.setUspehSrednja(request.getUspehSrednja());
        student.setUspehPrijemni(request.getUspehPrijemni());
        student.setSrednjaSkola(srednjaSkola);

        return student;
    }


    public static StudentPodaciResponse toResponse(StudentPodaci student) {
        if (student == null) return null;

        StudentPodaciResponse response = new StudentPodaciResponse();
        response.setId(student.getId());
        response.setIme(student.getIme());
        response.setPrezime(student.getPrezime());
        response.setSrednjeIme(student.getSrednjeIme());
        response.setJmbg(student.getJmbg());
        response.setDatumRodjenja(student.getDatumRodjenja());
        response.setMestoRodjenja(student.getMestoRodjenja());
        response.setMestoPrebivalista(student.getMestoPrebivalista());
        response.setDrzavaRodjenja(student.getDrzavaRodjenja());
        response.setDrzavljanstvo(student.getDrzavljanstvo());
        response.setNacionalnost(student.getNacionalnost());
        response.setPol(student.getPol());
        response.setAdresa(student.getAdresa());
        response.setBrojTelefonaMobilni(student.getBrojTelefonaMobilni());
        response.setBrojTelefonaFiksni(student.getBrojTelefonaFiksni());
        response.setEmail(student.getEmail());
        response.setBrojLicneKarte(student.getBrojLicneKarte());
        response.setLicnuKartuIzdao(student.getLicnuKartuIzdao());
        response.setMestoStanovanja(student.getMestoStanovanja());
        response.setAdresaStanovanja(student.getAdresaStanovanja());
        response.setNazivSrednja(
                student.getSrednjaSkola() == null
                        ? ""
                        : student.getSrednjaSkola().getNaziv()
        );

        return response;
    }
}
