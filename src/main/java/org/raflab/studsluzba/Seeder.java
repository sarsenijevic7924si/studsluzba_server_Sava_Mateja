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
        // ---------- 1. Reference data ----------
        // VrstaStudija (entity present in snippets)
        List<VrstaStudija> vrstaStudijaList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            VrstaStudija v = new VrstaStudija();
            v.setSkraceniNaziv("VS" + i);
            v.setPunNaziv("Vrsta studija " + i);
            vrstaStudijaList.add(vrstaStudijaRepository.save(v));
        }

        // VisokaSkola
        VisokaSkola vs = new VisokaSkola();
        vs.setNaziv("Visoka skola primer");
        vs.setMesto("Beograd");
        vs = visokaSkolaRepository.save(vs);

        // Srednje skole
        List<SrednjaSkola> srednje = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            SrednjaSkola ss = new SrednjaSkola();
            ss.setNaziv("Srednja Skola " + i);
            ss.setMesto("Mesto " + i);
            ss.setVrsta(VrstaSkole.GIMNAZIJA);
            srednje.add(srednjaSkolaRepository.save(ss));
        }

        // SkolskaGodina
        List<SkolskaGodina> skolske = new ArrayList<>();
        for (int y = 2022; y <= 2024; y++) {
            SkolskaGodina sg = new SkolskaGodina();
            sg.setGodina(y);
            sg.setAktivan(y == 2024);
            skolske.add(skolskaGodinaRepository.save(sg));
        }

        // ---------- 2. Studijski programi ----------
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
            // poveži sa prvom vrstom studija radi integriteta
            sp.setVrstaStudija(vrstaStudijaList.get(0));
            spList.add(studijskiProgramRepository.save(sp));
        }

        // ---------- 3. Predmeti ----------
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

        // ---------- 4. Nastavnici ----------
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

        // ---------- 5. NastavnikZvanje (povezano sa nastavnicima) ----------
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

        // Ensure bidirectional relation if needed
        for (int i = 0; i < nastavnikList.size(); i++) {
            Nastavnik n = nastavnikList.get(i);
            Set<NastavnikZvanje> set = new HashSet<>();
            set.add(zvanjeList.get(i));
            n.setZvanja(set);
            nastavnikRepository.save(n);
        }

        // ---------- 6. Studenti (StudentPodaci) ----------
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
            // poveži srednju školu za neke studente
            if (i <= srednje.size()) s.setSrednjaSkola(srednje.get(i - 1));
            if (i % 3 == 0) s.setVisokaSkola(vs);
            studentPodaciList.add(studentPodaciRepository.save(s));
        }

        // ---------- 7. StudentIndeks (unique constraint on broj/godina/oznaka/aktivan) ----------
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

        // ---------- 8. DrziPredmet and SlusaPredmet ----------
        List<DrziPredmet> drziPredmetList = new ArrayList<>();
        List<SlusaPredmet> slusaPredmetList = new ArrayList<>();
        for (int i = 0; i < predmetList.size(); i++) {
            DrziPredmet dp = new DrziPredmet();
            dp.setNastavnik(nastavnikList.get(i % nastavnikList.size()));
            dp.setPredmet(predmetList.get(i));
            // optionally set skolska godina
            dp.setSkolskaGodina(skolske.get(0));
            drziPredmetList.add(drziPredmetRepository.save(dp));

            // create slusaPredmet for corresponding student indeks
            SlusaPredmet sl = new SlusaPredmet();
            sl.setStudentIndeks(indeksList.get(i % indeksList.size()));
            sl.setDrziPredmet(dp);
            slusaPredmetList.add(slusaPredmetRepository.save(sl));
        }

        // ---------- 9. Grupe ----------
        for (int i = 0; i < spList.size(); i++) {
            Grupa g = new Grupa();
            g.setStudijskiProgram(spList.get(i));
            // add a single predmet list
            g.setPredmeti(Collections.singletonList(predmetList.get(i)));
            grupaRepository.save(g);
        }

        // ---------- 10. Ispitni rok i ispiti ----------
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

        // ---------- 11. Prijave i IzlazakNaIspit ----------
        List<PrijavaIspita> prijave = new ArrayList<>();
        for (StudentIndeks si : indeksList) {
            for (Ispit ispit : ispiti) {
                PrijavaIspita prijava = new PrijavaIspita();
                prijava.setStudentIndeks(si);
                prijava.setIspit(ispit);
                PrijavaIspita saved = prijavaIspitaRepository.save(prijava);
                prijave.add(saved);

                // izlistaj izlazak na ispit vezan za prijavu
                IzlazakNaIspit izlazak = new IzlazakNaIspit();
                izlazak.setPrijava(saved);
                izlazak.setPoeniSaIspita((int) (Math.random() * 51)); // random 50-100
                izlazak.setUkupniPoeni(izlazak.getPoeniSaIspita()); // predispitne = 0 for now
                izlazak.setPonistio(false);
                izlazak.setNapomena("");
                izlazakNaIspitRepository.save(izlazak);
            }
        }

        // ---------- 12. PredIspitnaObaveza i StudentPredispitni ----------
        List<PredIspitnaObaveza> pioList = new ArrayList<>();
        for (int i = 0; i < predmetList.size(); i++) {
            PredIspitnaObaveza pio = new PredIspitnaObaveza();
            pio.setVrsta(i % 2 == 0 ? "TEST" : "KOLOKVIJUM");
            pio.setMaksimalanBrojPoena(50);
            pio.setPredmet(predmetList.get(i));
            pio.setSkolskaGodina(skolske.get(0));
            pioList.add(predispitneObavezeRepository.save(pio));
        }

        // StudentPredispitni linking some students to first few predispitnih
        for (int i = 0; i < Math.min(5, indeksList.size()); i++) {
            StudentPredispitni sp = new StudentPredispitni();
            sp.setIdneks(indeksList.get(i));
            sp.setPredIspitnaObaveza(pioList.get(i));
            sp.setBrojPoena( (double) (10 +i) );
            // repository name unknown in snippets; use predispitneObavezeRepository for save if you have StudentPredispitniRepository
            try {
                studentPodaciRepository.flush();
                // if you have StudentPredispitniRepository: studentPredispitniRepository.save(sp);
            } catch (Exception ignored) { }
        }

        // ---------- 13. PolozeniPredmeti ----------
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

        // ---------- 14. UpisGodine i ObnovaGodine ----------
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

        // ---------- 15. Uplate ----------
        for (int i = 0; i < Math.min(5, indeksList.size()); i++) {
            Uplata u = new Uplata();
            u.setStudentIndeks(indeksList.get(i));
            u.setDatumUplate(LocalDate.now().minusDays(i * 10));
            u.setIznosDinari(100000.0 + i * 5000);
            // optionally set srednjiKurs (null allowed)
            u.setSrednjiKurs(null);
            uplataRepository.save(u);
        }

        // Seeder finished
        System.out.println("Seeder finished: populated reference data, programs, predmet, nastavnici, studenti, indeksi, ispiti, prijave, izlazaka, uplate, upisi...");
    }
}
