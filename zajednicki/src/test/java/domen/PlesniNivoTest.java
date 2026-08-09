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
class PlesniNivoTest {

	PlesniNivo pn;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		pn = new PlesniNivo();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		pn = null;
	}

	/**
	 * Test method for {@link domen.PlesniNivo#hashCode()}.
	 */
	@Test
	void testHashCode() {
		pn.setOpis("Pocetni");
		pn.setNivo(1);

		PlesniNivo pn2 = new PlesniNivo();
		pn2.setOpis("Pocetni");
		pn2.setNivo(1);

		assertEquals(pn.hashCode(), pn2.hashCode());
	}

	/**
	 * Test method for
	 * {@link domen.PlesniNivo#PlesniNivo(java.lang.Long, java.lang.String, int)}.
	 */
	@Test
	void testPlesniNivoLongStringInt() {
		PlesniNivo p = new PlesniNivo(1L, "Pocetni", 1);

		assertEquals(1L, p.getIdPlesniNivo());
		assertEquals("Pocetni", p.getOpis());
		assertEquals(1, p.getNivo());
	}

	/**
	 * Test method for {@link domen.PlesniNivo#setIdPlesniNivo(java.lang.Long)}.
	 */
	@Test
	void testSetIdPlesniNivo() {
		pn.setIdPlesniNivo(5L);
		assertEquals(5L, pn.getIdPlesniNivo());
	}

	/**
	 * Test method for {@link domen.PlesniNivo#setOpis(java.lang.String)}.
	 */
	@Test
	void testSetOpis() {
		pn.setOpis("Napredni");
		assertEquals("Napredni", pn.getOpis());
	}

	/**
	 * Test method for {@link domen.PlesniNivo#setNivo(int)}.
	 */
	@Test
	void testSetNivo() {
		pn.setNivo(3);
		assertEquals(3, pn.getNivo());
	}

	/**
	 * Test method for {@link domen.PlesniNivo#toString()}.
	 */
	@Test
	void testToString() {
		pn.setOpis("Srednji");
		pn.setNivo(2);

		assertEquals("Srednji 2", pn.toString());
	}

	/**
	 * Test method for {@link domen.PlesniNivo#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@DisplayName("Dva plesna nivoa su jednaka ako imaju isti opis i nivo")
	@CsvSource({ "Pocetni, 1, Pocetni, 1, true", "Pocetni, 1, Napredni, 1, false", "Pocetni, 1, Pocetni, 2, false" })
	void testEqualsObject(String opis1, int nivo1, String opis2, int nivo2, boolean ocekivano) {
		pn.setOpis(opis1);
		pn.setNivo(nivo1);

		PlesniNivo pn2 = new PlesniNivo();
		pn2.setOpis(opis2);
		pn2.setNivo(nivo2);

		assertEquals(ocekivano, pn.equals(pn2));
	}

	@Test
	void testEqualsIstiObjekat() {
		pn.setOpis("Pocetni");
		pn.setNivo(1);
		assertTrue(pn.equals(pn));
	}

	@Test
	void testEqualsNull() {
		assertFalse(pn.equals(null));
	}

	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(pn.equals(new Ucenik()));
	}

	@Test
	void testVratiVrednostZaUbacivanje() {
		PlesniNivo p = new PlesniNivo(1L, "Pocetni", 1);
		assertEquals("'Pocetni', 1", p.vratiVrednostZaUbacivanje());
	}

	@Test
	void testVratiVrednostZaIzmenu() {
		PlesniNivo p = new PlesniNivo(1L, "Pocetni", 1);
		assertEquals("opis = 'Pocetni', nivo = 1", p.vratiVrednostZaIzmenu());
	}

	@Test
	void testVratiPrimarniKljuc() {
		pn.setIdPlesniNivo(5L);
		assertEquals("idPlesniNivo = 5", pn.vratiPrimarniKljuc());
	}
}
