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
class UbaciEvidencijaCasovaSOTest {
	UbaciEvidencijaCasovaSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new UbaciEvidencijaCasovaSO(repository);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		so = null;
		repository = null;
	}

	// Helper: pravi validnu evidenciju koju pojedini testovi kvare
	private EvidencijaCasova validnaEvidencija() {
		Calendar cal = Calendar.getInstance();
		cal.set(2025, Calendar.SEPTEMBER, 1, 0, 0, 0);
		Date pocetak = cal.getTime();
		cal.set(2026, Calendar.JUNE, 1, 0, 0, 0);
		Date zavrsetak = cal.getTime();
		cal.set(2025, Calendar.OCTOBER, 1, 0, 0, 0);
		Date prisustvo = cal.getTime();

		EvidencijaCasova ec = new EvidencijaCasova();
		ec.setSkolskaGodina("2025/2026");
		ec.setDatumPocetka(pocetak);
		ec.setDatumZavrsetka(zavrsetak);
		ec.setProfesor(new Profesor(1L, "Ana", "Anic", "064", "aanic", "sifra"));
		ec.setUcenik(new Ucenik(1L, "Marko", "Markovic", "065", null));

		List<StavkaEvidencijeCasova> stavke = new ArrayList<>();
		StavkaEvidencijeCasova stavka = new StavkaEvidencijeCasova();
		stavka.setCas(new Cas(1L, "Salsa", 60, "Latino"));
		stavka.setOcena(5);
		stavka.setDatumPrisustva(prisustvo);
		StavkaEvidencijeCasova stavka1 = new StavkaEvidencijeCasova();
		stavka1.setCas(new Cas(2L, "Hip-Hop", 90, "Latino"));
		stavka1.setOcena(4);
		stavka1.setDatumPrisustva(prisustvo);
		stavke.add(stavka);
		stavke.add(stavka1);
		ec.setStavke(stavke);

		return ec;
	}

	private Date datum(int god, int mesec, int dan) {
		Calendar cal = Calendar.getInstance();
		cal.set(god, mesec, dan, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// 1. null
	@Test
	void testPreduslovNull() {
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(null));
		assertEquals("Evidencija ne sme biti null!", ex.getMessage());
	}

	// 2. nije instanca
	@Test
	void testPreduslovNijeInstanca() {
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(new Ucenik()));
		assertEquals("Prosledjeni objekat nije instanca klase EvidencijaCasova!", ex.getMessage());
	}

	// 3. prazna skolska godina
	@Test
	void testPreduslovPraznaSkolskaGodina() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setSkolskaGodina("");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Skolska godina ne sme biti prazna!", ex.getMessage());
	}

	// 4. datum pocetka null
	@Test
	void testPreduslovNullDatumPocetka() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setDatumPocetka(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum pocetka ne sme biti null!", ex.getMessage());
	}

	// 5. datum zavrsetka null
	@Test
	void testPreduslovNullDatumZavrsetka() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setDatumZavrsetka(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum zavrsetka ne sme biti null!", ex.getMessage());
	}

	// 6. zavrsetak pre pocetka
	@Test
	void testPreduslovZavrsetakPrePocetka() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setDatumZavrsetka(datum(2024, Calendar.JUNE, 1));
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum zavrsetka mora biti nakon datuma pocetka!", ex.getMessage());
	}

	// 7. los format skolske godine
	@Test
	void testPreduslovLosFormatGodine() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setSkolskaGodina("2025-2026");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Skolska godina mora biti u formatu 'xxxx/xxxx'!", ex.getMessage());
	}

	// 8. bez stavki
	@Test
	void testPreduslovBezStavki() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setStavke(new ArrayList<>());
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Evidencija mora imati bar jednu stavku!", ex.getMessage());
	}

	// 9. profesor null
	@Test
	void testPreduslovNullProfesor() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setProfesor(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Profesor ne sme biti null!", ex.getMessage());
	}

	// 10. ucenik null
	@Test
	void testPreduslovNullUcenik() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.setUcenik(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Ucenik ne sme biti null!", ex.getMessage());
	}

	// 11. godina pocetka != prva godina skolske godine
	@Test
	void testPreduslovGodinaPocetkaNeodgovara() {
		EvidencijaCasova ec = validnaEvidencija();
		// pocetak u 2024 umesto 2025, ali zavrsetak ostaje 2026 i posle pocetka
		ec.setDatumPocetka(datum(2024, Calendar.SEPTEMBER, 1));
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Godina datuma pocetka mora biti jednaka prvoj godini skolske godine!", ex.getMessage());
	}

	// 12. godina zavrsetka != druga godina skolske godine
	@Test
	void testPreduslovGodinaZavrsetkaNeodgovara() {
		EvidencijaCasova ec = validnaEvidencija();
		// zavrsetak u 2027 umesto 2026 (i dalje posle pocetka)
		ec.setDatumZavrsetka(datum(2027, Calendar.JUNE, 1));
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Godina datuma zavrsetka mora biti jednaka drugoj godini skolske godine!", ex.getMessage());
	}

	// 13. cas u stavci null
	@Test
	void testPreduslovStavkaCasNull() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().get(0).setCas(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Cas u stavci ne sme biti null!", ex.getMessage());
	}

	// 14. ocena van opsega
	@Test
	void testPreduslovOcenaVanOpsega() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().get(0).setOcena(6);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Ocena mora biti izmedju 1 i 5!", ex.getMessage());
	}

	// 15a. datum prisustva null
	@Test
	void testPreduslovStavkaDatumNull() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().get(0).setDatumPrisustva(null);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum prisustva ne sme biti null!", ex.getMessage());
	}

	// 15b. datum prisustva van opsega
	@Test
	void testPreduslovDatumPrisustvaVanOpsega() {
		EvidencijaCasova ec = validnaEvidencija();
		ec.getStavke().get(0).setDatumPrisustva(datum(2026, Calendar.JULY, 1)); // posle zavrsetka
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Datum prisustva mora biti izmedju datuma pocetka i zavrsetka evidencije!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		EvidencijaCasova ec = validnaEvidencija();
		assertDoesNotThrow(() -> so.preduslov(ec));
	}

	@Test
	void testIzvrsiOperaciju() throws Exception {
		EvidencijaCasova ec = validnaEvidencija();

		// add se zove za evidenciju + za svaku stavku
		repository.add(ec);
		expectLastCall();
		for (StavkaEvidencijeCasova s : ec.getStavke()) {
			repository.add(s);
			expectLastCall();
		}
		replay(repository);

		so.izvrsiOperaciju(ec);

		verify(repository);
	}

}
