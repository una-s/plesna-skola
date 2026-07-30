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
class PrSTest {

	PrS prs;
	Profesor profesor;
	Sertifikat sertifikat;
	Date datum;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		prs = new PrS();
		profesor = new Profesor(1L, "Ana", "Anic", "0641112223", "aanic", "sifra123");
		sertifikat = new Sertifikat(1L, "Centar za Savremenu Igru", "Salsa instruktor");
		datum = new Date();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		prs = null;
		profesor = null;
		sertifikat = null;
		datum = null;
	}

	/**
	 * Test method for {@link domen.PrS#hashCode()}.
	 */
	@Test
	void testHashCode() {
		prs.setDatumIzdavanja(datum);
		prs.setProfesor(profesor);
		prs.setSertifikat(sertifikat);

		PrS prs2 = new PrS();
		prs2.setDatumIzdavanja(datum);
		prs2.setProfesor(profesor);
		prs2.setSertifikat(sertifikat);

		assertEquals(prs.hashCode(), prs2.hashCode());
	}

	/**
	 * Test method for
	 * {@link domen.PrS#PrS(java.util.Date, domen.Profesor, domen.Sertifikat)}.
	 */
	@Test
	void testPrSDateProfesorSertifikat() {
		PrS p = new PrS(datum, profesor, sertifikat);

		assertEquals(datum, p.getDatumIzdavanja());
		assertEquals(profesor, p.getProfesor());
		assertEquals(sertifikat, p.getSertifikat());
	}

	/**
	 * Test method for {@link domen.PrS#setDatumIzdavanja(java.util.Date)}.
	 */
	@Test
	void testSetDatumIzdavanja() {
		prs.setDatumIzdavanja(datum);
		assertEquals(datum, prs.getDatumIzdavanja());
	}

	/**
	 * Test method for {@link domen.PrS#setProfesor(domen.Profesor)}.
	 */
	@Test
	void testSetProfesor() {
		prs.setProfesor(profesor);
		assertEquals(profesor, prs.getProfesor());
	}

	/**
	 * Test method for {@link domen.PrS#setSertifikat(domen.Sertifikat)}.
	 */
	@Test
	void testSetSertifikat() {
		prs.setSertifikat(sertifikat);
		assertEquals(sertifikat, prs.getSertifikat());
	}

	/**
	 * Test method for {@link domen.PrS#toString()}.
	 */
	@Test
	void testToString() {
		prs.setProfesor(profesor);
		prs.setSertifikat(sertifikat);
		prs.setDatumIzdavanja(datum);

		String rezultat = prs.toString();

		assertTrue(rezultat.contains("Ana Anic"));
		assertTrue(rezultat.contains("Salsa instruktor"));
	}

	/**
	 * Test method for {@link domen.PrS#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsIsto() {
		prs.setDatumIzdavanja(datum);
		prs.setProfesor(profesor);
		prs.setSertifikat(sertifikat);

		PrS prs2 = new PrS();
		prs2.setDatumIzdavanja(datum);
		prs2.setProfesor(profesor);
		prs2.setSertifikat(sertifikat);

		assertTrue(prs.equals(prs2));
	}

	@Test
	void testEqualsRazlicitSertifikat() {
		prs.setDatumIzdavanja(datum);
		prs.setProfesor(profesor);
		prs.setSertifikat(sertifikat);

		Sertifikat drugi = new Sertifikat(2L, "Step Up Dance Center", "Tango instruktor");
		PrS prs2 = new PrS();
		prs2.setDatumIzdavanja(datum);
		prs2.setProfesor(profesor);
		prs2.setSertifikat(drugi);

		assertFalse(prs.equals(prs2));
	}

	@Test
	void testEqualsIstiObjekat() {
		prs.setProfesor(profesor);
		assertTrue(prs.equals(prs));
	}

	@Test
	void testEqualsNull() {
		assertFalse(prs.equals(null));
	}

	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(prs.equals(new Ucenik()));
	}

	@Test
	void testVratiVrednostZaUbacivanje() {
		Profesor prof = new Profesor(3L, "Ana", "Anic", "064", "aanic", "sifra");
		Sertifikat sert = new Sertifikat(5L, "Centar za Savremenu Igru", "Salsa instruktor");

		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(2026, java.util.Calendar.JANUARY, 15, 0, 0, 0);
		Date datum = cal.getTime();

		PrS p = new PrS(datum, prof, sert);

		assertEquals("3, 5, '2026-01-15'", p.vratiVrednostZaUbacivanje());
	}

	@Test
	void testVratiVrednostZaIzmenu() {
		Profesor prof = new Profesor(3L, "Ana", "Anic", "064", "aanic", "sifra");
		Sertifikat sert = new Sertifikat(5L, "Centar za Savremenu Igru", "Salsa instruktor");

		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(2026, java.util.Calendar.JANUARY, 15, 0, 0, 0);
		Date datum = cal.getTime();

		PrS p = new PrS(datum, prof, sert);

		assertEquals("profesor = 3, sertifikat = 5, datumIzdavanja = '2026-01-15'", p.vratiVrednostZaIzmenu());
	}
}
