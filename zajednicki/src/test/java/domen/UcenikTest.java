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
class UcenikTest {

	Ucenik u;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		u = new Ucenik();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		u = null;
	}

	/**
	 * Test method for {@link domen.Ucenik#hashCode()}.
	 */
	@Test
	void testHashCode() {
		u.setBrojTelefona("0641111111");

		Ucenik u2 = new Ucenik();
		u2.setBrojTelefona("0641111111");

		assertEquals(u.hashCode(), u2.hashCode());
	}

	/**
	 * Test method for {@link domen.Ucenik#Ucenik()}.
	 */
	@Test
	void testUcenik() {
		assertNotNull(u);
	}

	/**
	 * Test method for
	 * {@link domen.Ucenik#Ucenik(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, domen.PlesniNivo)}.
	 */
	@Test
	void testUcenikLongStringStringStringPlesniNivo() {
		PlesniNivo pn = new PlesniNivo();
		Ucenik ucenik = new Ucenik(1L, "Ivo", "Ivic", "0641234567", pn);

		assertEquals(1L, ucenik.getIdUcenik());
		assertEquals("Ivo", ucenik.getIme());
		assertEquals("Ivic", ucenik.getPrezime());
		assertEquals("0641234567", ucenik.getBrojTelefona());
		assertEquals(pn, ucenik.getPlesniNivo());
	}

	/**
	 * Test method for {@link domen.Ucenik#setIdUcenik(java.lang.Long)}.
	 */
	@Test
	void testSetIdUcenik() {
		u.setIdUcenik(5L);
		assertEquals(5L, u.getIdUcenik());
	}

	/**
	 * Test method for {@link domen.Ucenik#setIme(java.lang.String)}.
	 */
	@Test
	void testSetIme() {
		u.setIme("Marko");
		assertEquals("Marko", u.getIme());
	}

	/**
	 * Test method for {@link domen.Ucenik#setPrezime(java.lang.String)}.
	 */
	@Test
	void testSetPrezime() {
		u.setPrezime("Markovic");
		assertEquals("Markovic", u.getPrezime());
	}

	/**
	 * Test method for {@link domen.Ucenik#setBrojTelefona(java.lang.String)}.
	 */
	@Test
	void testSetBrojTelefona() {
		u.setBrojTelefona("0653334444");
		assertEquals("0653334444", u.getBrojTelefona());
	}

	/**
	 * Test method for {@link domen.Ucenik#setPlesniNivo(domen.PlesniNivo)}.
	 */
	@Test
	void testSetPlesniNivo() {
		PlesniNivo pn = new PlesniNivo();
		u.setPlesniNivo(pn);
		assertEquals(pn, u.getPlesniNivo());
	}

	/**
	 * Test method for {@link domen.Ucenik#toString()}.
	 */
	@Test
	void testToString() {
		u.setIme("Ivo");
		u.setPrezime("Ivic");

		String rezultat = u.toString();

		assertEquals("Ivo Ivic", rezultat);
	}

	/**
	 * Test method for {@link domen.Ucenik#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@DisplayName("Dva ucenika su jednaka ako imaju isti broj telefona")
	@CsvSource({ "0641111111, 0641111111, true", "0641111111, 0642222222, false" })
	void testEqualsObject(String tel1, String tel2, boolean ocekivano) {
		u.setBrojTelefona(tel1);

		Ucenik u2 = new Ucenik();
		u2.setBrojTelefona(tel2);

		assertEquals(ocekivano, u.equals(u2));
	}

	@Test
	void testEqualsIstiObjekat() {
		u.setBrojTelefona("0641111111");
		assertTrue(u.equals(u));
	}

	@Test
	void testEqualsNull() {
		assertFalse(u.equals(null));
	}

	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(u.equals(new PlesniNivo()));
	}

	@Test
	void testUslovZaSelectSamoIme() {
		u.setIme("Marko");
		assertEquals("u.ime LIKE '%Marko%'", u.uslovZaSelect());
	}

	@Test
	void testUslovZaSelectImeIPrezime() {
		u.setIme("Marko");
		u.setPrezime("Markovic");
		assertEquals("u.ime LIKE '%Marko%' AND u.prezime LIKE '%Markovic%'", u.uslovZaSelect());
	}

	@Test
	void testUslovZaSelectSvaPolja() {
		PlesniNivo pn = new PlesniNivo(2L, "Srednji", 2);
		u.setIme("Marko");
		u.setPrezime("Markovic");
		u.setBrojTelefona("0641112223");
		u.setPlesniNivo(pn);
		assertEquals(
				"u.ime LIKE '%Marko%' AND u.prezime LIKE '%Markovic%' AND u.idPlesniNivo = 2 AND u.brojTelefona = '0641112223'",
				u.uslovZaSelect());
	}

	@Test
	void testUslovZaSelectPrazno() {
		assertEquals("", u.uslovZaSelect());
	}

	@Test
	void testVratiVrednostZaUbacivanje() {
		PlesniNivo pn = new PlesniNivo(2L, "Srednji", 2);
		Ucenik ucenik = new Ucenik(1L, "Marko", "Markovic", "0641112223", pn);
		assertEquals("'Marko', 'Markovic', '0641112223', 2", ucenik.vratiVrednostZaUbacivanje());
	}

	@Test
	void testVratiVrednostZaIzmenu() {
		PlesniNivo pn = new PlesniNivo(2L, "Srednji", 2);
		Ucenik ucenik = new Ucenik(1L, "Marko", "Markovic", "0641112223", pn);
		assertEquals("ime = 'Marko', prezime = 'Markovic', brojTelefona = '0641112223', idPlesniNivo = 2",
				ucenik.vratiVrednostZaIzmenu());
	}

	@Test
	void testVratiPrimarniKljuc() {
		u.setIdUcenik(5L);
		assertEquals("idUcenik = 5", u.vratiPrimarniKljuc());
	}
}
