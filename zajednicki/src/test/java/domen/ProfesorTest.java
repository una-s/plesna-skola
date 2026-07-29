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
class ProfesorTest {

	Profesor p;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		p=new Profesor();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		p=null;
	}

	/**
	 * Test method for {@link domen.Profesor#hashCode()}.
	 */
	@Test
	void testHashCode() {
	    p.setKorisnickoIme("aanic");
	    p.setSifra("sifra123");
	    
	    Profesor p2 = new Profesor();
	    p2.setKorisnickoIme("aanic");
	    p2.setSifra("sifra123");
	    
	    assertEquals(p.hashCode(), p2.hashCode());
	}

	/**
	 * Test method for {@link domen.Profesor#Profesor(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	 void testProfesorLongStringStringStringStringString() {
		 Profesor prof = new Profesor(1L, "Ana", "Anic", "0641112223", "aanic", "sifra123");

	        assertEquals(1L, prof.getIdProfesor());
	        assertEquals("Ana", prof.getIme());
	        assertEquals("Anic", prof.getPrezime());
	        assertEquals("0641112223", prof.getBrojTelefona());
	        assertEquals("aanic", prof.getKorisnickoIme());
	        assertEquals("sifra123", prof.getSifra());
	}

	/**
	 * Test method for {@link domen.Profesor#setIdProfesor(java.lang.Long)}.
	 */
	@Test
	 void testSetIdProfesor() {
		p.setIdProfesor(5L);
        assertEquals(5L, p.getIdProfesor());
	}

	/**
	 * Test method for {@link domen.Profesor#setIme(java.lang.String)}.
	 */
	@Test
	 void testSetIme() {
		 p.setIme("Marko");
	     assertEquals("Marko", p.getIme());
	}

	/**
	 * Test method for {@link domen.Profesor#setPrezime(java.lang.String)}.
	 */
	@Test
	 void testSetPrezime() {
		 p.setPrezime("Markovic");
	     assertEquals("Markovic", p.getPrezime());
	}

	/**
	 * Test method for {@link domen.Profesor#setBrojTelefona(java.lang.String)}.
	 */
	@Test
	 void testSetBrojTelefona() {
		 p.setBrojTelefona("0653334444");
	     assertEquals("0653334444", p.getBrojTelefona());
	}

	/**
	 * Test method for {@link domen.Profesor#setKorisnickoIme(java.lang.String)}.
	 */
	@Test
	 void testSetKorisnickoIme() {
		p.setKorisnickoIme("mmarkovic");
        assertEquals("mmarkovic", p.getKorisnickoIme());
	}

	/**
	 * Test method for {@link domen.Profesor#setSifra(java.lang.String)}.
	 */
	@Test
	 void testSetSifra() {
		 p.setSifra("tajna");
	     assertEquals("tajna", p.getSifra());
	}

	/**
	 * Test method for {@link domen.Profesor#toString()}.
	 */
	@Test
	 void testToString() {
		 p.setIme("Ana");
	     p.setPrezime("Anic");

	     assertEquals("Ana Anic", p.toString());
	}

	/**
	 * Test method for {@link domen.Profesor#equals(java.lang.Object)}.
	 */
	@ParameterizedTest
    @DisplayName("Dva profesora su jednaka ako imaju isto korisnicko ime i sifru")
    @CsvSource({
        "aanic, sifra123, aanic, sifra123, true",
        "aanic, sifra123, bbbob, sifra123, false",
        "aanic, sifra123, aanic, drugaSifra, false"
    })
	 void testEqualsObject(String kor1, String sif1, String kor2, String sif2, boolean ocekivano) {
        p.setKorisnickoIme(kor1);
        p.setSifra(sif1);

        Profesor p2 = new Profesor();
        p2.setKorisnickoIme(kor2);
        p2.setSifra(sif2);

        assertEquals(ocekivano, p.equals(p2));
    }
	
	@Test
    void testEqualsIstiObjekat() {
        p.setKorisnickoIme("aanic");
        p.setSifra("sifra123");
        assertTrue(p.equals(p));
    }

    @Test
    void testEqualsNull() {
        assertFalse(p.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(p.equals(new Ucenik()));
    }

}
