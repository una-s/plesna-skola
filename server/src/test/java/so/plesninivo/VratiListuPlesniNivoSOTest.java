/**
 * 
 */
package so.plesninivo;

import static org.junit.jupiter.api.Assertions.*;
import domen.PlesniNivo;
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
class VratiListuPlesniNivoSOTest {
	VratiListuPlesniNivoSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new VratiListuPlesniNivoSO(repository);
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
		assertEquals("Prosledjeni objekat nije instanca klase PlesniNivo!", ex.getMessage());
	}

	@Test
	void testPreduslovNijeInstancaPlesniNivo() {
		Ucenik u = new Ucenik();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Prosledjeni objekat nije instanca klase PlesniNivo!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		PlesniNivo pn = new PlesniNivo();
		assertDoesNotThrow(() -> so.preduslov(pn));
	}

	@Test
	void testIzvrsiOperacijuVracaListu() throws Exception {
		PlesniNivo pn = new PlesniNivo();

		List<PlesniNivo> lista = new ArrayList<>();
		lista.add(new PlesniNivo(1L, "Pocetni", 1));
		lista.add(new PlesniNivo(2L, "Srednji", 2));

		expect(repository.getAll(pn)).andReturn((List) lista);
		replay(repository);

		so.izvrsiOperaciju(pn);

		assertEquals(lista, so.getPlesniNivoi());
		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuPraznaLista() throws Exception {
		PlesniNivo pn = new PlesniNivo();

		List<PlesniNivo> prazna = new ArrayList<>();

		expect(repository.getAll(pn)).andReturn((List) prazna);
		replay(repository);

		Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(pn));
		assertEquals("PlesniNivo-i nisu vraceni!", ex.getMessage());
		verify(repository);
	}
}
