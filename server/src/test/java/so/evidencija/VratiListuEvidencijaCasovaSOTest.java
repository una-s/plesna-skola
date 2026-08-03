/**
 * 
 */
package so.evidencija;

import static org.junit.jupiter.api.Assertions.*;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.EvidencijaCasova;
import domen.Ucenik;
import repository.db.DbRepository;

/**
 * 
 */
class VratiListuEvidencijaCasovaSOTest {
	VratiListuEvidencijaCasovaSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new VratiListuEvidencijaCasovaSO(repository);
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
		assertEquals("Prosledjeni objekat nije instanca klase EvidencijeCasova!", ex.getMessage());
	}

	@Test
	void testPreduslovNijeInstanca() {
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(new Ucenik()));
		assertEquals("Prosledjeni objekat nije instanca klase EvidencijeCasova!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		EvidencijaCasova ec = new EvidencijaCasova();
		assertDoesNotThrow(() -> so.preduslov(ec));
	}

	@Test
	void testIzvrsiOperacijuVracaListu() throws Exception {
		EvidencijaCasova ec = new EvidencijaCasova();

		List<EvidencijaCasova> lista = new ArrayList<>();
		EvidencijaCasova e1 = new EvidencijaCasova();
		e1.setIdEvidencijaCasova(1L);
		EvidencijaCasova e2 = new EvidencijaCasova();
		e2.setIdEvidencijaCasova(2L);
		lista.add(e1);
		lista.add(e2);

		expect(repository.getAll(ec)).andReturn((List) lista);
		replay(repository);

		so.izvrsiOperaciju(ec);

		assertEquals(lista, so.getEvidencije());
		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuPraznaLista() throws Exception {
		EvidencijaCasova ec = new EvidencijaCasova();

		List<EvidencijaCasova> prazna = new ArrayList<>();

		expect(repository.getAll(ec)).andReturn((List) prazna);
		replay(repository);

		so.izvrsiOperaciju(ec);

		assertTrue(so.getEvidencije().isEmpty());
		verify(repository);
	}

}
