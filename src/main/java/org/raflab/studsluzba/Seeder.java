package org.raflab.studsluzba;

import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired private IspitRepository ispitRepository;
    @Autowired private IspitniRokRepository ispitniRokRepository;
    @Autowired private PrijavaIspitaRepository prijavaIspitaRepository;
    @Autowired private IzlazakNaIspitRepository izlazakNaIspitRepository;
    @Autowired private SkolskaGodinaRepository skolskaGodinaRepository;
    @Autowired private PredIspitnaObavezaRepository predispitneObavezeRepository;
    @Autowired private StudijskiProgramRepository studijskiProgramRepository;
    @Autowired private PredmetRepository predmetRepository;
    @Autowired private NastavnikRepository nastavnikRepository;
    @Autowired private NastavnikZvanjeRepository nastavnikZvanjeRepository;
    @Autowired private StudentPodaciRepository studentPodaciRepository;
    @Autowired private StudentIndeksRepository studentIndeksRepository;
    @Autowired private DrziPredmetRepository drziPredmetRepository;
    @Autowired private SlusaPredmetRepository slusaPredmetRepository;
    @Autowired private GrupaRepository grupaRepository;
    @Autowired private PolozeniPremetRepository polozeniPredmetiRepository;
    @Autowired private UpisGodineRepository upisGodineRepository;
    @Autowired private ObnovaGodineRepository obnovaGodineRepository;
    @Autowired private UplataRepository uplataRepository;
    @Autowired private VisokaSkolaRepository visokaSkolaRepository;
    @Autowired private SrednjaSkolaRepository srednjaSkolaRepository;
    @Autowired private VrstaStudijaRespository vrstaStudijaRepository;
    @Autowired private NastavnikZvanjeRepository nzRepo; // already declared but safe
    @Autowired private PredIspitnaObavezaRepository pioRepo; // alias
    // add other repos if you have them

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        List<VrstaStudija> vrstaStudijaList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            VrstaStudija v = new VrstaStudija();
            v.setSkraceniNaziv("VS" + i);
            v.setPunNaziv("Vrsta studija " + i);
            vrstaStudijaList.add(vrstaStudijaRepository.save(v));
        }


        VisokaSkola vs = new VisokaSkola();
        vs.setNaziv("Visoka skola primer");
        vs.setMesto("Beograd");
        vs = visokaSkolaRepository.save(vs);


        List<SrednjaSkola> srednje = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            SrednjaSkola ss = new SrednjaSkola();
            ss.setNaziv("Srednja Skola " + i);
            ss.setMesto("Mesto " + i);
            ss.setVrsta(VrstaSkole.GIMNAZIJA);
            srednje.add(srednjaSkolaRepository.save(ss));
        }


        List<SkolskaGodina> skolske = new ArrayList<>();
        for (int y = 2022; y <= 2024; y++) {
            SkolskaGodina sg = new SkolskaGodina();
            sg.setGodina(y);
            sg.setAktivan(y == 2024);
            skolske.add(skolskaGodinaRepository.save(sg));
        }


        List<StudijskiProgram> spList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            StudijskiProgram sp = new StudijskiProgram();
            sp.setOznaka("SP" + i);
            sp.setNaziv("Program " + i);
            sp.setGodinaAkreditacije(2015 + i);
            sp.setZvanje("Dipl. inženjer " + i);
            sp.setTrajanjeGodina(4);
            sp.setTrajanjeSemestara(8);
            sp.setUkupnoEspb(240);

            sp.setVrstaStudija(vrstaStudijaList.get(0));
            spList.add(studijskiProgramRepository.save(sp));
        }


        List<Predmet> predmetList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Predmet p = new Predmet();
            p.setSifra("PR" + String.format("%02d", i));
            p.setNaziv("Predmet " + i);
            p.setOpis("Opis predmeta " + i);
            p.setEspb(5 + (i % 6));
            p.setStudProgram(spList.get((i - 1) % spList.size()));
            p.setObavezan(i % 2 == 0);
            predmetList.add(predmetRepository.save(p));
        }


        List<Nastavnik> nastavnikList = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            Nastavnik n = new Nastavnik();
            n.setIme("Nastavnik" + i);
            n.setPrezime("Prezime" + i);
            n.setSrednjeIme("Srednje" + i);
            n.setEmail("nastavnik" + i + "@example.com");
            n.setBrojTelefona("06012345" + i);
            n.setAdresa("Adresa " + i);
            n.setDatumRodjenja(LocalDate.of(1975 + i, (i % 12) + 1, (i % 25) + 1));
            n.setPol(i % 2 == 0 ? 'M' : 'F');
            n.setJmbg("8001012345" + i);
            nastavnikList.add(nastavnikRepository.save(n));
        }


        List<NastavnikZvanje> zvanjeList = new ArrayList<>();
        for (int i = 1; i <= nastavnikList.size(); i++) {
            NastavnikZvanje nz = new NastavnikZvanje();
            nz.setDatumIzbora(LocalDate.of(2018 + i, 1 + (i % 12), 1 + (i % 25)));
            nz.setNaucnaOblast("Oblast " + i);
            nz.setUzaNaucnaOblast("Uza oblast " + i);
            nz.setZvanje(i % 2 == 0 ? "Docent" : "Vanredni profesor");
            nz.setAktivno(true);
            nz.setNastavnik(nastavnikList.get(i - 1));
            zvanjeList.add(nastavnikZvanjeRepository.save(nz));
        }


        for (int i = 0; i < nastavnikList.size(); i++) {
            Nastavnik n = nastavnikList.get(i);
            Set<NastavnikZvanje> set = new HashSet<>();
            set.add(zvanjeList.get(i));
            n.setZvanja(set);
            nastavnikRepository.save(n);
        }


        List<StudentPodaci> studentPodaciList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            StudentPodaci s = new StudentPodaci();
            s.setIme("Student" + i);
            s.setPrezime("Prezime" + i);
            s.setSrednjeIme("Srednje" + i);
            s.setJmbg("0010101234" + i);
            s.setDatumRodjenja(LocalDate.of(2000 + (i%5), (i%12)+1, (i%25)+1));
            s.setMestoRodjenja("Mesto" + i);
            s.setMestoPrebivalista("Prebivaliste " + i);
            s.setDrzavaRodjenja("Srbija");
            s.setDrzavljanstvo("Srbija");
            s.setNacionalnost("Srpska");
            s.setPol(i % 2 == 0 ? 'F' : 'M');
            s.setAdresa("Adresa " + i);
            s.setBrojTelefonaMobilni("06123456" + i);
            s.setEmail("student" + i + "@example.com");
            s.setUspehSrednja(4.0 + (i % 2));
            s.setUspehPrijemni(80 + i);

            if (i <= srednje.size()) s.setSrednjaSkola(srednje.get(i - 1));
            if (i % 3 == 0) s.setVisokaSkola(vs);
            studentPodaciList.add(studentPodaciRepository.save(s));
        }


        List<StudentIndeks> indeksList = new ArrayList<>();
        int idxCounter = 1;
        for (int i = 0; i < studentPodaciList.size(); i++) {
            StudentIndeks si = new StudentIndeks();
            si.setBroj(idxCounter++);
            si.setGodina(2023);
            si.setStudProgramOznaka(spList.get(i % spList.size()).getOznaka());
            si.setNacinFinansiranja(i % 2 == 0 ? "Budzet" : "Samofinansiranje");
            si.setAktivan(true);
            si.setVaziOd(LocalDate.of(2023, 10, 1 + (i % 20)));
            si.setStudent(studentPodaciList.get(i));
            si.setStudijskiProgram(spList.get(i % spList.size()));
            si.setOstvarenoEspb(0);
            indeksList.add(studentIndeksRepository.save(si));
        }


        List<DrziPredmet> drziPredmetList = new ArrayList<>();
        List<SlusaPredmet> slusaPredmetList = new ArrayList<>();
        for (int i = 0; i < predmetList.size(); i++) {
            DrziPredmet dp = new DrziPredmet();
            dp.setNastavnik(nastavnikList.get(i % nastavnikList.size()));
            dp.setPredmet(predmetList.get(i));
            dp.setSkolskaGodina(skolske.get(0));
            drziPredmetList.add(drziPredmetRepository.save(dp));


            SlusaPredmet sl = new SlusaPredmet();
            sl.setStudentIndeks(indeksList.get(i % indeksList.size()));
            sl.setDrziPredmet(dp);
            slusaPredmetList.add(slusaPredmetRepository.save(sl));
        }


        for (int i = 0; i < spList.size(); i++) {
            Grupa g = new Grupa();
            g.setStudijskiProgram(spList.get(i));

            g.setPredmeti(Collections.singletonList(predmetList.get(i)));
            grupaRepository.save(g);
        }


        IspitniRok rok1 = new IspitniRok();
        rok1.setPocetakRoka(LocalDate.of(2025, 6, 1));
        rok1.setZavrsetakRoka(LocalDate.of(2025, 6, 30));
        rok1.setSkolskaGodina(skolske.get(0));
        ispitniRokRepository.save(rok1);

        List<Ispit> ispiti = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Ispit ispit = new Ispit();
            ispit.setDatumOdrzavanja(LocalDate.of(2025, 6, 10 + i));
            ispit.setVremePocetka(LocalTime.of(9 + i, 0));
            ispit.setJeZakljucen(false);
            ispit.setNastavnik(nastavnikList.get(i % nastavnikList.size()));
            ispit.setPredmet(predmetList.get(i));
            ispit.setIspitniRok(rok1);
            ispiti.add(ispitRepository.save(ispit));
        }


        List<PrijavaIspita> prijave = new ArrayList<>();
        for (StudentIndeks si : indeksList) {
            for (Ispit ispit : ispiti) {
                PrijavaIspita prijava = new PrijavaIspita();
                prijava.setStudentIndeks(si);
                prijava.setIspit(ispit);
                PrijavaIspita saved = prijavaIspitaRepository.save(prijava);
                prijave.add(saved);


                IzlazakNaIspit izlazak = new IzlazakNaIspit();
                izlazak.setPrijava(saved);
                izlazak.setPoeniSaIspita((int) (Math.random() * 51));
                izlazak.setUkupniPoeni(izlazak.getPoeniSaIspita());
                izlazak.setPonistio(false);
                izlazak.setNapomena("");
                izlazakNaIspitRepository.save(izlazak);
            }
        }


        List<PredIspitnaObaveza> pioList = new ArrayList<>();
        for (int i = 0; i < predmetList.size(); i++) {
            PredIspitnaObaveza pio = new PredIspitnaObaveza();
            pio.setVrsta(i % 2 == 0 ? "TEST" : "KOLOKVIJUM");
            pio.setMaksimalanBrojPoena(50);
            pio.setPredmet(predmetList.get(i));
            pio.setSkolskaGodina(skolske.get(0));
            pioList.add(predispitneObavezeRepository.save(pio));
        }


        for (int i = 0; i < Math.min(5, indeksList.size()); i++) {
            StudentPredispitni sp = new StudentPredispitni();
            sp.setIdneks(indeksList.get(i));
            sp.setPredIspitnaObaveza(pioList.get(i));
            sp.setBrojPoena( (double) (10 +i) );

            try {
                studentPodaciRepository.flush();

            } catch (Exception ignored) { }
        }


        for (int i = 0; i < Math.min(5, indeksList.size()); i++) {
            PolozeniPredmeti pp = new PolozeniPredmeti();
            pp.setStudentIndeks(indeksList.get(i));
            pp.setPredmet(predmetList.get(i));
            pp.setOcena(6 + i);
            pp.setDatumPolaganja(LocalDate.of(2025, 6, 15 + i));
            pp.setDatumPriznavanja(LocalDate.of(2025, 6, 20 + i));
            pp.setUstanova("Univerzitet " + (i+1));
            pp.setNacin(NacinOcene.POLAGANJE);
            polozeniPredmetiRepository.save(pp);
        }


        for (int i = 0; i < Math.min(5, indeksList.size()); i++) {
            UpisGodine ug = new UpisGodine();
            ug.setStudentIndeks(indeksList.get(i));
            ug.setSkolskaGodina(skolske.get(0));
            ug.setGodinaKojaSeUpisuje(1 + (i % 4));
            ug.setDatumUpisa(LocalDate.of(2023, 10, 1 + i));
            ug.setNapomena("Bez napomene");
            upisGodineRepository.save(ug);

            ObnovaGodine og = new ObnovaGodine();
            og.setStudentIndeks(indeksList.get(i));
            og.setSkolskaGodina(skolske.get(0));
            og.setDatumObnove(LocalDate.of(2024, 9, 1 + i));
            og.setNapomena("Obnova primer");
            obnovaGodineRepository.save(og);
        }


        for (int i = 0; i < Math.min(5, indeksList.size()); i++) {
            Uplata u = new Uplata();
            u.setStudentIndeks(indeksList.get(i));
            u.setDatumUplate(LocalDate.now().minusDays(i * 10));
            u.setIznosDinari(100000.0 + i * 5000);

            u.setSrednjiKurs(null);
            uplataRepository.save(u);
        }


        System.out.println("Seeder finished: populated reference data, programs, predmet, nastavnici, studenti, indeksi, ispiti, prijave, izlazaka, uplate, upisi...");
    }
}
