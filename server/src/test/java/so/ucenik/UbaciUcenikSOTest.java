/**
 * 
 */
package so.ucenik;

import static org.junit.jupiter.api.Assertions.*;
import static org.easymock.EasyMock.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.PlesniNivo;
import domen.Profesor;
import domen.Ucenik;
import repository.db.DbRepository;

/**
 * 
 */
class UbaciUcenikSOTest {

	UbaciUcenikSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new UbaciUcenikSO(repository);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		so = null;
		repository = null;
	}

	@Test
	void testPreduslovNull() {
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(null));
		assertEquals("Ucenik ne sme biti null!", ex.getMessage());
	}

	@Test
	void testPreduslovNijeInstancaUcenik() {
		Profesor p = new Profesor();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(p));
		assertEquals("Prosledjeni objekat nije instanca klase Ucenik!", ex.getMessage());
	}

	@Test
	void testPreduslovPraznoIme() {
		Ucenik u = new Ucenik();
		u.setIme("");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Ime ne sme biti prazno!", ex.getMessage());
	}

	@Test
	void testPreduslovPraznoPrezime() {
		Ucenik u = new Ucenik();
		u.setIme("Marko");
		u.setPrezime("");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Prezime ne sme biti prazno!", ex.getMessage());
	}

	@Test
	void testPreduslovPrazanTelefon() {
		Ucenik u = new Ucenik();
		u.setIme("Marko");
		u.setPrezime("Markovic");
		u.setBrojTelefona("");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Broj telefona ne sme biti prazan!", ex.getMessage());
	}

	@Test
	void testPreduslovNullPlesniNivo() {
		Ucenik u = new Ucenik();
		u.setIme("Marko");
		u.setPrezime("Markovic");
		u.setBrojTelefona("0641112223");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Plesni nivo ne sme biti prazan!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		PlesniNivo pn = new PlesniNivo(1L, "Pocetni", 1);
		Ucenik u = new Ucenik(1L, "Marko", "Markovic", "0641112223", pn);
		assertDoesNotThrow(() -> so.preduslov(u));
	}

	@Test
	void testIzvrsiOperaciju() throws Exception {
		PlesniNivo pn = new PlesniNivo(1L, "Pocetni", 1);
		Ucenik u = new Ucenik(1L, "Marko", "Markovic", "0641112223", pn);

		repository.add(u);
		expectLastCall();
		replay(repository);

		so.izvrsiOperaciju(u);

		verify(repository);
	}
}
