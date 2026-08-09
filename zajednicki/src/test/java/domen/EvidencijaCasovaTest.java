/**
 * 
 */
package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class EvidencijaCasovaTest {

	EvidencijaCasova ec;
	Profesor profesor;
	Ucenik ucenik;
	Date datumPocetka;
	Date datumZavrsetka;
	List<StavkaEvidencijeCasova> stavke;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		ec = new EvidencijaCasova();
		profesor = new Profesor(1L, "Ana", "Anic", "0641112223", "aanic", "sifra123");
		PlesniNivo pn = new PlesniNivo(1L, "Pocetni", 1);
		ucenik = new Ucenik(1L, "Marko", "Markovic", "0651112223", pn);
		datumPocetka = new Date();
		datumZavrsetka = new Date();
		stavke = new ArrayList<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		ec = null;
		profesor = null;
		ucenik = null;
		datumPocetka = null;
		datumZavrsetka = null;
		stavke = null;
	}

	/**
	 * Test method for {@link domen.EvidencijaCasova#hashCode()}.
	 */
	@Test
	void testHashCode() {
		ec.setIdEvidencijaCasova(100L);

		EvidencijaCasova ec2 = new EvidencijaCasova();
		ec2.setIdEvidencijaCasova(100L);

		assertEquals(ec.hashCode(), ec2.hashCode());
	}

	/**
	 * Test method for
	 * {@link domen.EvidencijaCasova#EvidencijaCasova(java.lang.Long, java.lang.String, java.util.Date, java.util.Date, int, double, int, domen.Profesor, domen.Ucenik, java.util.List)}.
	 */
	@Test
	void testEvidencijaCasovaLongStringDateDateIntDoubleIntProfesorUcenikListOfStavkaEvidencijeCasova() {
		EvidencijaCasova e = new EvidencijaCasova(1L, "2025/2026", datumPocetka, datumZavrsetka, 120, 4.5, 10, profesor,
				ucenik, stavke);

		assertEquals(1L, e.getIdEvidencijaCasova());
		assertEquals("2025/2026", e.getSkolskaGodina());
		assertEquals(datumPocetka, e.getDatumPocetka());
		assertEquals(datumZavrsetka, e.getDatumZavrsetka());
		assertEquals(120, e.getTrajanjeEvidencije());
		assertEquals(4.5, e.getProsecnaOcena());
		assertEquals(10, e.getBrojPrisustva());
		assertEquals(profesor, e.getProfesor());
		assertEquals(ucenik, e.getUcenik());
		assertEquals(stavke, e.getStavke());
	}

	/**
	 * Test method for
	 * {@link domen.EvidencijaCasova#setIdEvidencijaCasova(java.lang.Long)}.
	 */
	@Test
	void testSetIdEvidencijaCasova() {
		ec.setIdEvidencijaCasova(5L);
		assertEquals(5L, ec.getIdEvidencijaCasova());
	}

	/**
	 * Test method for
	 * {@link domen.EvidencijaCasova#setSkolskaGodina(java.lang.String)}.
	 */
	@Test
	void testSetSkolskaGodina() {
		ec.setSkolskaGodina("2025/2026");
		assertEquals("2025/2026", ec.getSkolskaGodina());
	}

	/**
	 * Test method for
	 * {@link domen.EvidencijaCasova#setDatumPocetka(java.util.Date)}.
	 */
	@Test
	void testSetDatumPocetka() {
		ec.setDatumPocetka(datumPocetka);
		assertEquals(datumPocetka, ec.getDatumPocetka());
	}

	/**
	 * Test method for
	 * {@link domen.EvidencijaCasova#setDatumZavrsetka(java.util.Date)}.
	 */
	@Test
	final void testSetDatumZavrsetka() {
		ec.setDatumZavrsetka(datumZavrsetka);
		assertEquals(datumZavrsetka, ec.getDatumZavrsetka());
	}

	/**
	 * Test method for {@link domen.EvidencijaCasova#setTrajanjeEvidencije(int)}.
	 */
	@Test
	final void testSetTrajanjeEvidencije() {
		ec.setTrajanjeEvidencije(120);
		assertEquals(120, ec.getTrajanjeEvidencije());
	}

	/**
	 * Test method for {@link domen.EvidencijaCasova#setProsecnaOcena(double)}.
	 */
	@Test
	void testSetProsecnaOcena() {
		ec.setProsecnaOcena(4.5);
		assertEquals(4.5, ec.getProsecnaOcena());
	}

	/**
	 * Test method for {@link domen.EvidencijaCasova#setBrojPrisustva(int)}.
	 */
	@Test
	void testSetBrojPrisustva() {
		ec.setBrojPrisustva(10);
		assertEquals(10, ec.getBrojPrisustva());
	}

	/**
	 * Test method for {@link domen.EvidencijaCasova#setProfesor(domen.Profesor)}.
	 */
	@Test
	void testSetProfesor() {
		ec.setProfesor(profesor);
		assertEquals(profesor, ec.getProfesor());
	}

	/**
	 * Test method for {@link domen.EvidencijaCasova#setUcenik(domen.Ucenik)}.
	 */
	@Test
	final void testSetUcenik() {
		ec.setStavke(stavke);
		assertEquals(stavke, ec.getStavke());
	}

	/**
	 * Test method for {@link domen.EvidencijaCasova#setStavke(java.util.List)}.
	 */
	@Test
	final void testSetStavke() {
		ec.setStavke(stavke);
		assertEquals(stavke, ec.getStavke());
	}

	/**
	 * Test method for {@link domen.EvidencijaCasova#toString()}.
	 */
	@Test
	final void testToString() {
		ec.setSkolskaGodina("2025/2026");
		ec.setUcenik(ucenik);
		ec.setProfesor(profesor);

		String rezultat = ec.toString();

		assertTrue(rezultat.contains("2025/2026"));
		assertTrue(rezultat.contains("Marko Markovic"));
		assertTrue(rezultat.contains("Ana Anic"));
	}

	/**
	 * Test method for {@link domen.EvidencijaCasova#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsIsto() {
		ec.setIdEvidencijaCasova(100L);

		EvidencijaCasova ec2 = new EvidencijaCasova();
		ec2.setIdEvidencijaCasova(100L);

		assertTrue(ec.equals(ec2));
	}

	@Test
	void testEqualsRazlicitId() {
		ec.setIdEvidencijaCasova(100L);

		EvidencijaCasova ec2 = new EvidencijaCasova();
		ec2.setIdEvidencijaCasova(200L);

		assertFalse(ec.equals(ec2));
	}

	@Test
	void testEqualsIstiObjekat() {
		ec.setIdEvidencijaCasova(100L);
		assertTrue(ec.equals(ec));
	}

	@Test
	void testEqualsNull() {
		assertFalse(ec.equals(null));
	}

	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(ec.equals(new Ucenik()));
	}

	@Test
	void testUslovZaSelectPoId() {
		ec.setIdEvidencijaCasova(5L);
		assertEquals("ec.idEvidencijaCasova = 5", ec.uslovZaSelect());
	}

	@Test
	void testUslovZaSelectPoProfesoru() {
		Profesor prof = new Profesor(3L, "Ana", "Anic", "064", "aanic", "sifra");
		ec.setProfesor(prof);
		assertEquals("ec.profesor = 3", ec.uslovZaSelect());
	}

	@Test
	void testUslovZaSelectProfesorIUcenik() {
		Profesor prof = new Profesor(3L, "Ana", "Anic", "064", "aanic", "sifra");
		PlesniNivo pn = new PlesniNivo(1L, "Pocetni", 1);
		Ucenik uc = new Ucenik(7L, "Marko", "Markovic", "065", pn);
		ec.setProfesor(prof);
		ec.setUcenik(uc);
		assertEquals("ec.profesor = 3 AND ec.ucenik = 7", ec.uslovZaSelect());
	}

	@Test
	void testUslovZaSelectSvaTriKriterijuma() {
		Profesor prof = new Profesor(3L, "Ana", "Anic", "064", "aanic", "sifra");
		PlesniNivo pn = new PlesniNivo(1L, "Pocetni", 1);
		Ucenik uc = new Ucenik(7L, "Marko", "Markovic", "065", pn);
		ec.setProfesor(prof);
		ec.setUcenik(uc);
		ec.setSkolskaGodina("2025/2026");
		assertEquals("ec.profesor = 3 AND ec.ucenik = 7 AND ec.skolskaGodina = '2025/2026'", ec.uslovZaSelect());
	}

	@Test
	void testUslovZaSelectPrazno() {
		assertEquals("", ec.uslovZaSelect());
	}

	@Test
	void testVratiPrimarniKljuc() {
		ec.setIdEvidencijaCasova(5L);
		assertEquals("idEvidencijaCasova = 5", ec.vratiPrimarniKljuc());
	}

}
