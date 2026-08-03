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
import domen.Profesor;
import domen.Ucenik;
import repository.db.DbRepository;

/**
 * 
 */
class PretraziEvidencijaCasovaSOTest {
	PretraziEvidencijaCasovaSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new PretraziEvidencijaCasovaSO(repository);
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
	void testPreduslovNijeInstanca() {
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(new Ucenik()));
		assertEquals("Prosledjeni objekat nije instanca klase EvidencijaCasova!", ex.getMessage());
	}

	@Test
	void testPreduslovSviKriterijumiPrazni() {
		EvidencijaCasova ec = new EvidencijaCasova();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("Morate uneti bar jedan kriterijum pretrage!", ex.getMessage());
	}

	@Test
	void testPreduslovProfesorPopunjen() {
		EvidencijaCasova ec = new EvidencijaCasova();
		ec.setProfesor(new Profesor(1L, "Ana", "Anic", "064", "aanic", "sifra"));
		assertDoesNotThrow(() -> so.preduslov(ec));
	}

	@Test
	void testPreduslovSkolskaGodinaPopunjena() {
		EvidencijaCasova ec = new EvidencijaCasova();
		ec.setSkolskaGodina("2025/2026");
		assertDoesNotThrow(() -> so.preduslov(ec));
	}

	@Test
	void testIzvrsiOperacijuVracaListu() throws Exception {
		EvidencijaCasova ec = new EvidencijaCasova();
		ec.setSkolskaGodina("2025/2026");

		List<EvidencijaCasova> lista = new ArrayList<>();
		EvidencijaCasova e1 = new EvidencijaCasova();
		e1.setIdEvidencijaCasova(1L);
		lista.add(e1);

		expect(repository.getAll(ec)).andReturn((List) lista);
		replay(repository);

		so.izvrsiOperaciju(ec);

		assertEquals(lista, so.getEvidencije());
		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuNemaRezultata() throws Exception {
		EvidencijaCasova ec = new EvidencijaCasova();
		ec.setSkolskaGodina("2025/2026");

		List<EvidencijaCasova> prazna = new ArrayList<>();

		expect(repository.getAll(ec)).andReturn((List) prazna);
		replay(repository);

		Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(ec));
		assertEquals("Sistem ne moze da nadje evidencije casova po zadatim kriterijumima!", ex.getMessage());
		verify(repository);
	}

}
