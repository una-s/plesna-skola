/**
 * 
 */
package so.ucenik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.PlesniNivo;
import domen.Profesor;
import domen.Ucenik;
import repository.db.DbRepository;
import static org.easymock.EasyMock.*;

/**
 * 
 */
class ObrisiUcenikSOTest {
	ObrisiUcenikSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new ObrisiUcenikSO(repository);
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
		assertEquals("Prosledjeni objekat nije instanca klase Ucenik!", ex.getMessage());
	}

	@Test
	void testPreduslovNijeInstancaUcenik() {
		Profesor p = new Profesor();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(p));
		assertEquals("Prosledjeni objekat nije instanca klase Ucenik!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		Ucenik u = new Ucenik();
		assertDoesNotThrow(() -> so.preduslov(u));
	}

	@Test
	void testIzvrsiOperaciju() throws Exception {
		PlesniNivo pn = new PlesniNivo(1L, "Pocetni", 1);
		Ucenik u = new Ucenik(1L, "Marko", "Markovic", "0641234567", pn);

		repository.delete(u);
		expectLastCall();
		replay(repository);

		so.izvrsiOperaciju(u);

		verify(repository);
	}
}
