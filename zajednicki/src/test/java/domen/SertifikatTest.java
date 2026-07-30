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
class SertifikatTest {

	Sertifikat s;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		s = new Sertifikat();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		s = null;
	}

	/**
	 * Test method for {@link domen.Sertifikat#hashCode()}.
	 */
	@Test
	void testHashCode() {
		s.setInstitucija("Plesna Akademija Korak");
		s.setNaziv("Salsa instruktor");

		Sertifikat s2 = new Sertifikat();
		s2.setInstitucija("Plesna Akademija Korak");
		s2.setNaziv("Salsa instruktor");

		assertEquals(s.hashCode(), s2.hashCode());
	}

	/**
	 * Test method for
	 * {@link domen.Sertifikat#Sertifikat(java.lang.Long, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testSertifikatLongStringString() {
		Sertifikat sert = new Sertifikat(1L, "Plesna Akademija Korak", "Salsa instruktor");

		assertEquals(1L, sert.getIdSertifikat());
		assertEquals("Plesna Akademija Korak", sert.getInstitucija());
		assertEquals("Salsa instruktor", sert.getNaziv());
	}

	/**
	 * Test method for {@link domen.Sertifikat#setIdSertifikat(java.lang.Long)}.
	 */
	@Test
	void testSetIdSertifikat() {
		s.setIdSertifikat(5L);
		assertEquals(5L, s.getIdSertifikat());
	}

	/**
	 * Test method for {@link domen.Sertifikat#setInstitucija(java.lang.String)}.
	 */
	@Test
	void testSetInstitucija() {
		s.setInstitucija("Plesni savez Srbije");
		assertEquals("Plesni savez Srbije", s.getInstitucija());
	}

	/**
	 * Test method for {@link domen.Sertifikat#setNaziv(java.lang.String)}.
	 */
	@Test
	void testSetNaziv() {
		s.setNaziv("Tango instruktor");
		assertEquals("Tango instruktor", s.getNaziv());
	}

	/**
	 * Test method for {@link domen.Sertifikat#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
	@DisplayName("Dva sertifikata su jednaka ako imaju istu instituciju i naziv")
	@CsvSource({ "Plesna Akademija Korak, Salsa, Plesna Akademija Korak, Salsa, true",
			"Plesna Akademija Korak, Salsa, Institut za Igru i Ples, Salsa, false",
			"Institut za Igru i Ples, Salsa, Institut za Igru i Ples, Tango, false" })
	void testEqualsObject(String inst1, String naziv1, String inst2, String naziv2, boolean ocekivano) {
		s.setInstitucija(inst1);
		s.setNaziv(naziv1);

		Sertifikat s2 = new Sertifikat();
		s2.setInstitucija(inst2);
		s2.setNaziv(naziv2);

		assertEquals(ocekivano, s.equals(s2));
	}

	@Test
	void testEqualsIstiObjekat() {
		s.setInstitucija("Plesna Akademija Korak");
		s.setNaziv("Salsa");
		assertTrue(s.equals(s));
	}

	@Test
	void testEqualsNull() {
		assertFalse(s.equals(null));
	}

	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(s.equals(new Ucenik()));
	}

	/**
	 * Test method for {@link domen.Sertifikat#toString()}.
	 */
	@Test
	void testToString() {
		s.setNaziv("Salsa instruktor");
		s.setInstitucija("Akademija Savremenog Plesa");

		assertEquals("Salsa instruktor Akademija Savremenog Plesa", s.toString());
	}

	@Test
	void testVratiVrednostZaUbacivanje() {
		Sertifikat sert = new Sertifikat(1L, "Akademija Savremenog Plesa", "Salsa instruktor");
		assertEquals("'Akademija Savremenog Plesa', 'Salsa instruktor'", sert.vratiVrednostZaUbacivanje());
	}

	@Test
	void testVratiVrednostZaIzmenu() {
		Sertifikat sert = new Sertifikat(1L, "Akademija Savremenog Plesa", "Salsa instruktor");
		assertEquals("institucija = 'Akademija Savremenog Plesa', naziv = 'Salsa instruktor'", sert.vratiVrednostZaIzmenu());
	}

	@Test
	void testVratiPrimarniKljuc() {
		s.setIdSertifikat(5L);
		assertEquals("idSertifikat = 5", s.vratiPrimarniKljuc());
	}

}
