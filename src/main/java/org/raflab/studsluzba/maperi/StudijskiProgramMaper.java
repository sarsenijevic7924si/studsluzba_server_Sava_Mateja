package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.StudijskiProgramReqest;
import org.raflab.studsluzba.controllers.response.StudijskiProgramResponse;
import org.raflab.studsluzba.model.StudijskiProgram;

public class StudijskiProgramMaper {
    public static StudijskiProgram toEntity(StudijskiProgramReqest sr)
    {
        StudijskiProgram sp=new StudijskiProgram();
        sp.setOznaka(sr.getOznaka());
        sp.setNaziv(sr.getNaziv());
        sp.setGodinaAkreditacije(sr.getGodinaAkreditacije());
        sp.setZvanje(sr.getZvanje());
        sp.setTrajanjeGodina(sr.getTrajanjeGodina());
        sp.setTrajanjeSemestara(sr.getTrajanjeSemestara());
        sp.setVrstaStudija(sr.getVrstaStudija());
        sp.setUkupnoEspb(sr.getUkupnoEspb());
        return sp;
    }
    public static StudijskiProgramResponse toResponse(StudijskiProgram sp)
    {
        StudijskiProgramResponse sr= new StudijskiProgramResponse();
        sr.setId(sp.getId());
        sr.setOznaka(sp.getOznaka());
        sr.setNaziv(sp.getNaziv());
        sr.setGodinaAkreditacije(sp.getGodinaAkreditacije());
        sr.setZvanje(sp.getZvanje());
        sr.setTrajanjeGodina(sp.getTrajanjeGodina());
        sr.setTrajanjeSemestara(sp.getTrajanjeSemestara());
        sr.setVrstaStudija(sp.getVrstaStudija());
        sr.setUkupnoEspb(sp.getUkupnoEspb());
        return sr;
    }
}
