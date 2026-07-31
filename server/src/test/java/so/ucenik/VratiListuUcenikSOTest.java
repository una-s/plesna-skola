/**
 * 
 */
package so.ucenik;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Profesor;
import domen.Ucenik;
import repository.db.DbRepository;

/**
 * 
 */
class VratiListuUcenikSOTest {

	VratiListuUcenikSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new VratiListuUcenikSO(repository);
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
	void testIzvrsiOperacijuVracaListu() throws Exception {
		Ucenik u = new Ucenik();

		List<Ucenik> lista = new ArrayList<>();
		lista.add(new Ucenik(1L, "Marko", "Markovic", "064", null));
		lista.add(new Ucenik(2L, "Ana", "Anic", "065", null));

		expect(repository.getAll(u)).andReturn((List) lista);
		replay(repository);

		so.izvrsiOperaciju(u);

		assertEquals(2, so.getUcenici().size());
		assertEquals(lista, so.getUcenici());
		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuPraznaLista() throws Exception {
		Ucenik u = new Ucenik();

		List<Ucenik> prazna = new ArrayList<>();

		expect(repository.getAll(u)).andReturn((List) prazna);
		replay(repository);

		so.izvrsiOperaciju(u);

		assertTrue(so.getUcenici().isEmpty());
		verify(repository);
	}

}
