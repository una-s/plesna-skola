/**
 * 
 */
package so.evidencija;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import domen.Cas;
import domen.EvidencijaCasova;
import domen.Profesor;
import domen.StavkaEvidencijeCasova;
import domen.Ucenik;
import repository.db.DbRepository;

/**
 * 
 */
class PromeniEvidencijaCasovaSOTest {

	PromeniEvidencijaCasovaSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new PromeniEvidencijaCasovaSO(repository);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		so = null;
		repository = null;
	}

	private Date datum(int god, int mesec, int dan) {
		Calendar cal = Calendar.getInstance();
		cal.set(god, mesec, dan, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	private StavkaEvidencijeCasova stavka(Long rb, int ocena) {
		StavkaEvidencijeCasova s = new StavkaEvidencijeCasova();
		s.setRb(rb);
		s.setCas(new Cas(1L, "Salsa", 60, "Latino"));
		s.setOcena(ocena);
		s.setDatumPrisustva(datum(2025, Calendar.OCTOBER, 1));
		return s;
	}

	private EvidencijaCasova validnaEvidencija() {
		EvidencijaCasova ec = new EvidencijaCasova();
		ec.setIdEvidencijaCasova(1L);
		ec.setSkolskaGodina("2025/2026");
		ec.setDatumPocetka(datum(2025, Calendar.SEPTEMBER, 1));
		ec.setDatumZavrsetka(datum(2026, Calendar.JUNE, 1));
		ec.setProfesor(new Profesor(1L, "Ana", "Anic", "064", "aanic", "sifra"));
		ec.setUcenik(new Ucenik(1L, "Marko", "Markovic", "065", null));

		List<StavkaEvidencijeCasova> stavke = new ArrayList<>();
		stavke.add(stavka(1L, 5));
		ec.setStavke(stavke);

		return ec;
	}

	@Test
	void testPreduslovNijeInstanca() {
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(new Ucenik()));
		assertEquals("Prosledjeni objekat nije instanca klase EvidencijaCasova!", ex.getMessage());
	}

	@Test
	void testPreduslovNullProfesor() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setProfesor(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Profesor mora biti izabran!", ex.getMessage());
	}

	@Test
	void testPreduslovNullUcenik() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setUcenik(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Ucenik mora biti izabran!", ex.getMessage());
	}

	@Test
	void testPreduslovPraznaSkolskaGodina() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setSkolskaGodina("");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Skolska godina ne sme biti prazna!", ex.getMessage());
	}

	@Test
	void testPreduslovLosFormatGodine() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setSkolskaGodina("2025-2026");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Skolska godina mora biti u formatu 'xxxx/xxxx'!", ex.getMessage());
	}

	@Test
	void testPreduslovNullDatumPocetka() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setDatumPocetka(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum pocetka ne sme biti null!", ex.getMessage());
	}

	@Test
	void testPreduslovNullDatumZavrsetka() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setDatumZavrsetka(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum zavrsetka ne sme biti null!", ex.getMessage());
	}

	@Test
	void testPreduslovZavrsetakPrePocetka() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setDatumZavrsetka(datum(2024, Calendar.JUNE, 1));
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum zavrsetka mora biti nakon datuma pocetka!", ex.getMessage());
	}

	@Test
	void testPreduslovBezStavki() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setStavke(new ArrayList<>());
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Evidencija casova mora imati bar jednu stavku!", ex.getMessage());
	}

	@Test
	void testPreduslovGodinaPocetkaNeodgovara() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setDatumPocetka(datum(2024, Calendar.SEPTEMBER, 1));
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Godina datuma pocetka mora biti jednaka prvoj godini skolske godine!", ex.getMessage());
	}

	@Test
	void testPreduslovGodinaZavrsetkaNeodgovara() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setDatumZavrsetka(datum(2027, Calendar.JUNE, 1));
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Godina datuma zavrsetka mora biti jednaka drugoj godini skolske godine!", ex.getMessage());
	}

	@Test
	void testPreduslovStavkaCasNull() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().get(0).setCas(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Cas u stavci ne sme biti null!", ex.getMessage());
	}

	@Test
	void testPreduslovOcenaVanOpsega() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().get(0).setOcena(6);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Ocena mora biti izmedju 1 i 5!", ex.getMessage());
	}

	@Test
	void testPreduslovDatumPrisustvaNull() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().get(0).setDatumPrisustva(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum prisustva ne sme biti null!", ex.getMessage());
	}

	@Test
	void testPreduslovDatumPrisustvaVanOpsega() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().get(0).setDatumPrisustva(datum(2026, Calendar.JULY, 1));
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum prisustva mora biti izmedju datuma pocetka i zavrsetka evidencije!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		EvidencijaCasova ec = validnaEvidencija();
		assertDoesNotThrow(() -> so.preduslov(ec));
	}

	@Test
	void testIzvrsiOperacijuNoveStavke() throws Exception {
		// Baza prazna -> SVE nove stavke se add-uju
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().add(stavka(2L, 4));
		ec.getStavke().add(stavka(3L, 3));
		// sve nove stavke dobijaju evidenciju (kao sto metoda radi)
		for (StavkaEvidencijeCasova s : ec.getStavke()) {
			s.setEvidencijaCasova(ec);
		}

		List<StavkaEvidencijeCasova> stareStavke = new ArrayList<>(); // baza prazna

		repository.edit(ec);
		expectLastCall();
		expect(repository.getAll(anyObject(StavkaEvidencijeCasova.class))).andReturn((List) stareStavke);
		for (StavkaEvidencijeCasova s : ec.getStavke()) {
			repository.add(s);
			expectLastCall();
		}
		replay(repository);

		so.izvrsiOperaciju(ec);

		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuEditPostojece() throws Exception {
		// Sve nove stavke vec postoje u bazi -> sve se EDIT-uju
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().add(stavka(2L, 4));
		for (StavkaEvidencijeCasova s : ec.getStavke()) {
			s.setEvidencijaCasova(ec);
		}

		// baza ima iste stavke (isti rb + ista evidencija) -> equals se poklapa
		List<StavkaEvidencijeCasova> stareStavke = new ArrayList<>();
		StavkaEvidencijeCasova stara1 = stavka(1L, 5);
		stara1.setEvidencijaCasova(ec);
		StavkaEvidencijeCasova stara2 = stavka(2L, 5);
		stara2.setEvidencijaCasova(ec);
		stareStavke.add(stara1);
		stareStavke.add(stara2);

		repository.edit(ec);
		expectLastCall();
		expect(repository.getAll(anyObject(StavkaEvidencijeCasova.class))).andReturn((List) stareStavke);
		// ni jedna se ne brise (sve postoje u novom spisku)
		// sve nove postoje u bazi -> edit svake
		for (StavkaEvidencijeCasova s : ec.getStavke()) {
			repository.edit(s);
			expectLastCall();
		}
		replay(repository);

		so.izvrsiOperaciju(ec);

		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuDeleteUklonjene() throws Exception {
		EvidencijaCasova ec = validnaEvidencija(); // rb=1
		ec.getStavke().add(stavka(2L, 4)); // rb=2
		for (StavkaEvidencijeCasova s : ec.getStavke()) {
			s.setEvidencijaCasova(ec);
		}

		// baza ima rb=1, rb=2, rb=3
		List<StavkaEvidencijeCasova> stareStavke = new ArrayList<>();
		for (long rb = 1; rb <= 3; rb++) {
			StavkaEvidencijeCasova stara = stavka(rb, 5);
			stara.setEvidencijaCasova(ec);
			stareStavke.add(stara);
		}

		repository.edit(ec);
		expectLastCall();
		expect(repository.getAll(anyObject(StavkaEvidencijeCasova.class))).andReturn((List) stareStavke);

		// prodji kroz SVE stare stavke; ako stara nije u novoj listi -> delete
		for (StavkaEvidencijeCasova stara : stareStavke) {
			if (!ec.getStavke().contains(stara)) {
				repository.delete(stara);
				expectLastCall();
			}
		}
		// za svaku novu stavku koja postoji u bazi -> edit
		for (StavkaEvidencijeCasova nova : ec.getStavke()) {
			repository.edit(nova);
			expectLastCall();
		}
		replay(repository);

		so.izvrsiOperaciju(ec);

		verify(repository);
	}
}
