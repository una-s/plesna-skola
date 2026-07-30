/**
 * 
 */
package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class StavkaEvidencijeCasovaTest {

	StavkaEvidencijeCasova stavka;
	EvidencijaCasova evidencija;
	Cas cas;
	Date datumPrisustva;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		stavka = new StavkaEvidencijeCasova();
		evidencija = new EvidencijaCasova();
		evidencija.setIdEvidencijaCasova(1L);
		cas = new Cas(1L, "Salsa pocetni", 60, "Salsa");
		datumPrisustva = new Date();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		stavka = null;
		evidencija = null;
		cas = null;
		datumPrisustva = null;
	}

	/**
	 * Test method for {@link domen.StavkaEvidencijeCasova#hashCode()}.
	 */
	@Test
	void testHashCode() {
		stavka.setRb(100L);
		stavka.setEvidencijaCasova(evidencija);

		StavkaEvidencijeCasova stavka2 = new StavkaEvidencijeCasova();
		stavka2.setRb(100L);
		stavka2.setEvidencijaCasova(evidencija);

		assertEquals(stavka.hashCode(), stavka2.hashCode());
	}

	/**
	 * Test method for
	 * {@link domen.StavkaEvidencijeCasova#StavkaEvidencijeCasova(domen.EvidencijaCasova, java.lang.Long, int, java.util.Date, java.lang.String, domen.Cas)}.
	 */
	@Test
	void testStavkaEvidencijeCasovaEvidencijaCasovaLongIntDateStringCas() {
		StavkaEvidencijeCasova s = new StavkaEvidencijeCasova(evidencija, 1L, 5, datumPrisustva, "Odlicno", cas);

		assertEquals(evidencija, s.getEvidencijaCasova());
		assertEquals(1L, s.getRb());
		assertEquals(5, s.getOcena());
		assertEquals(datumPrisustva, s.getDatumPrisustva());
		assertEquals("Odlicno", s.getNapomena());
		assertEquals(cas, s.getCas());
	}

	/**
	 * Test method for
	 * {@link domen.StavkaEvidencijeCasova#setEvidencijaCasova(domen.EvidencijaCasova)}.
	 */
	@Test
	void testSetEvidencijaCasova() {
		stavka.setEvidencijaCasova(evidencija);
		assertEquals(evidencija, stavka.getEvidencijaCasova());
	}

	/**
	 * Test method for {@link domen.StavkaEvidencijeCasova#setRb(java.lang.Long)}.
	 */
	@Test
	void testSetRb() {
		stavka.setRb(3L);
		assertEquals(3L, stavka.getRb());
	}

	/**
	 * Test method for {@link domen.StavkaEvidencijeCasova#setOcena(int)}.
	 */
	@Test
	void testSetOcena() {
		stavka.setOcena(4);
		assertEquals(4, stavka.getOcena());
	}

	/**
	 * Test method for
	 * {@link domen.StavkaEvidencijeCasova#setDatumPrisustva(java.util.Date)}.
	 */
	@Test
	void testSetDatumPrisustva() {
		stavka.setDatumPrisustva(datumPrisustva);
		assertEquals(datumPrisustva, stavka.getDatumPrisustva());
	}

	/**
	 * Test method for
	 * {@link domen.StavkaEvidencijeCasova#setNapomena(java.lang.String)}.
	 */
	@Test
	void testSetNapomena() {
		stavka.setNapomena("Dobar napredak");
		assertEquals("Dobar napredak", stavka.getNapomena());
	}

	/**
	 * Test method for {@link domen.StavkaEvidencijeCasova#setCas(domen.Cas)}.
	 */
	@Test
	void testSetCas() {
		stavka.setCas(cas);
		assertEquals(cas, stavka.getCas());
	}

	/**
	 * Test method for {@link domen.StavkaEvidencijeCasova#toString()}.
	 */
	@Test
	void testToString() {
		stavka.setRb(2L);
		stavka.setOcena(5);

		assertEquals("2 5", stavka.toString());
	}

	/**
	 * Test method for
	 * {@link domen.StavkaEvidencijeCasova#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsIsto() {
		stavka.setRb(100L);
		stavka.setEvidencijaCasova(evidencija);

		StavkaEvidencijeCasova stavka2 = new StavkaEvidencijeCasova();
		stavka2.setRb(100L);
		stavka2.setEvidencijaCasova(evidencija);

		assertTrue(stavka.equals(stavka2));
	}

	@Test
	void testEqualsRazlicitRb() {
		stavka.setRb(100L);
		stavka.setEvidencijaCasova(evidencija);

		StavkaEvidencijeCasova stavka2 = new StavkaEvidencijeCasova();
		stavka2.setRb(200L);
		stavka2.setEvidencijaCasova(evidencija);

		assertFalse(stavka.equals(stavka2));
	}

	@Test
	void testEqualsIstiObjekat() {
		stavka.setRb(100L);
		assertTrue(stavka.equals(stavka));
	}

	@Test
	void testEqualsNull() {
		assertFalse(stavka.equals(null));
	}

	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(stavka.equals(new Ucenik()));
	}

}
