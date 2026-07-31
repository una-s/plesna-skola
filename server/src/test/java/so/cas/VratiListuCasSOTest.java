/**
 * 
 */
package so.cas;

import static org.junit.jupiter.api.Assertions.*;

import domen.Cas;
import domen.Ucenik;
import repository.db.DbRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
class VratiListuCasSOTest {

	VratiListuCasSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new VratiListuCasSO(repository);
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
	void testPreduslovNijeInstancaCas() {
		Ucenik u = new Ucenik();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Prosledjeni objekat nije instanca klase Cas!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		Cas c = new Cas();
		assertDoesNotThrow(() -> so.preduslov(c));
	}

	@Test
	void testIzvrsiOperacijuVracaListu() throws Exception {
		Cas c = new Cas();

		List<Cas> lista = new ArrayList<>();
		lista.add(new Cas(1L, "Salsa", 60, "Latino"));
		lista.add(new Cas(2L, "Tango", 90, "Standardni"));

		expect(repository.getAll(c)).andReturn((List) lista);
		replay(repository);

		so.izvrsiOperaciju(c);

		assertEquals(lista, so.getCasovi());
		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuPraznaLista() throws Exception {
		Cas c = new Cas();

		List<Cas> prazna = new ArrayList<>();

		expect(repository.getAll(c)).andReturn((List) prazna);
		replay(repository);

		Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(c));
		assertEquals("Casovi nisu vraceni!", ex.getMessage());
		verify(repository);
	}

}
