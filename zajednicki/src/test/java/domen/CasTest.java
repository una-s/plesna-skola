/**
 * 
 */
package domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 
 */
class CasTest {

	Cas c;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		c = new Cas();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		c = null;
	}

	/**
	 * Test method for {@link domen.Cas#hashCode()}.
	 */
	@Test
	void testHashCode() {
		c.setNaziv("Salsa");
		c.setStilPlesa("Latino");

		Cas c2 = new Cas();
		c2.setNaziv("Salsa");
		c2.setStilPlesa("Latino");

		assertEquals(c.hashCode(), c2.hashCode());
	}

	/**
	 * Test method for
	 * {@link domen.Cas#Cas(java.lang.Long, java.lang.String, int, java.lang.String)}.
	 */
	@Test
	void testCasLongStringIntString() {
		Cas cas = new Cas(1L, "Salsa pocetni", 60, "Salsa");

		assertEquals(1L, cas.getIdCas());
		assertEquals("Salsa pocetni", cas.getNaziv());
		assertEquals(60, cas.getTrajanje());
		assertEquals("Salsa", cas.getStilPlesa());
	}

	/**
	 * Test method for {@link domen.Cas#setIdCas(java.lang.Long)}.
	 */
	@Test
	void testSetIdCas() {
		c.setIdCas(5L);
		assertEquals(5L, c.getIdCas());
	}

	/**
	 * Test method for {@link domen.Cas#setNaziv(java.lang.String)}.
	 */
	@Test
	void testSetNaziv() {
		c.setNaziv("Tango napredni");
		assertEquals("Tango napredni", c.getNaziv());
	}

	/**
	 * Test method for {@link domen.Cas#setTrajanje(int)}.
	 */
	@Test
	void testSetTrajanje() {
		c.setTrajanje(90);
		assertEquals(90, c.getTrajanje());
	}

	/**
	 * Test method for {@link domen.Cas#setStilPlesa(java.lang.String)}.
	 */
	@Test
	void testSetStilPlesa() {
		c.setStilPlesa("Tango");
		assertEquals("Tango", c.getStilPlesa());
	}

	/**
	 * Test method for {@link domen.Cas#toString()}.
	 */
	@Test
	void testToString() {
		c.setNaziv("Salsa pocetni");
		assertEquals("Salsa pocetni", c.toString());
	}

	/**
	 * Test method for {@link domen.Cas#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@DisplayName("Dva casa su jednaka ako imaju isti naziv i stil plesa")
	@CsvSource({ "Salsa, Latino, Salsa, Latino, true", "Salsa, Latino, Tango, Latino, false",
			"Salsa, Latino, Salsa, Standardni, false" })
	void testEqualsObject(String naziv1, String stil1, String naziv2, String stil2, boolean ocekivano) {
		c.setNaziv(naziv1);
		c.setStilPlesa(stil1);

		Cas c2 = new Cas();
		c2.setNaziv(naziv2);
		c2.setStilPlesa(stil2);

		assertEquals(ocekivano, c.equals(c2));
	}

	@Test
	void testEqualsIstiObjekat() {
		c.setNaziv("Salsa");
		c.setStilPlesa("Latino");
		assertTrue(c.equals(c));
	}

	@Test
	void testEqualsNull() {
		assertFalse(c.equals(null));
	}

	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(c.equals(new Ucenik()));
	}

	@Test
	void testVratiVrednostZaUbacivanje() {
		Cas cas = new Cas(1L, "Salsa", 60, "Latino");
		assertEquals("'Salsa', 60,'Latino'", cas.vratiVrednostZaUbacivanje());
	}

	@Test
	void testVratiVrednostZaIzmenu() {
		Cas cas = new Cas(1L, "Salsa", 60, "Latino");
		assertEquals("naziv='Salsa', trajanje=60, stilPlesa='Latino'", cas.vratiVrednostZaIzmenu());
	}

	@Test
	void testVratiPrimarniKljuc() {
		c.setIdCas(5L);
		assertEquals("idCas=5", c.vratiPrimarniKljuc());
	}

}
