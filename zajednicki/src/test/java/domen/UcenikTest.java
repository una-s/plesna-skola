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
		u=new Ucenik();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		u=null;
	}

	/**
	 * Test method for {@link domen.Ucenik#hashCode()}.
	 */
	@Test
	final void testHashCode() {
		 assertEquals(3, u.hashCode());
	}

	/**
	 * Test method for {@link domen.Ucenik#Ucenik()}.
	 */
	@Test
	final void testUcenik() {
		assertNotNull(u);
	}

	/**
	 * Test method for {@link domen.Ucenik#Ucenik(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, domen.PlesniNivo)}.
	 */
	@Test
	final void testUcenikLongStringStringStringPlesniNivo() {
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
	final void testSetIdUcenik() {
		 u.setIdUcenik(5L);
		 assertEquals(5L, u.getIdUcenik());
	}

	/**
	 * Test method for {@link domen.Ucenik#setIme(java.lang.String)}.
	 */
	@Test
	final void testSetIme() {
		u.setIme("Marko");
	    assertEquals("Marko", u.getIme());
	}

	/**
	 * Test method for {@link domen.Ucenik#setPrezime(java.lang.String)}.
	 */
	@Test
	final void testSetPrezime() {
		u.setPrezime("Markovic");
	    assertEquals("Markovic", u.getPrezime());
	}

	/**
	 * Test method for {@link domen.Ucenik#setBrojTelefona(java.lang.String)}.
	 */
	@Test
	final void testSetBrojTelefona() {
		u.setBrojTelefona("0653334444");
	    assertEquals("0653334444", u.getBrojTelefona());
	}

	/**
	 * Test method for {@link domen.Ucenik#setPlesniNivo(domen.PlesniNivo)}.
	 */
	@Test
	final void testSetPlesniNivo() {
		PlesniNivo pn = new PlesniNivo();
	    u.setPlesniNivo(pn);
	    assertEquals(pn, u.getPlesniNivo());
	}

	/**
	 * Test method for {@link domen.Ucenik#toString()}.
	 */
	@Test
	final void testToString() {
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
	@CsvSource({
	    "0641111111, 0641111111, true",
	    "0641111111, 0642222222, false"
	})
	final void testEqualsObject(String tel1, String tel2, boolean ocekivano) {
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
}
