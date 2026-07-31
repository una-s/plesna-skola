/**
 * 
 */
package so.ucenik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;
import domen.PlesniNivo;
import domen.Profesor;
import domen.Ucenik;
import repository.db.DbRepository;

/**
* 
*/
class PretraziUcenikSOTest {

	PretraziUcenikSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new PretraziUcenikSO(repository);
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
	void testPreduslovNijeInstancaUcenik() {
		Profesor p = new Profesor();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(p));
		assertEquals("Prosledjeni objekat nije instanca klase Ucenik!", ex.getMessage());
	}

	@Test
	void testPreduslovSviKriterijumiPrazni() {
		Ucenik u = new Ucenik();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Barem jedan kriterijum za pretragu mora biti popunjen!", ex.getMessage());
	}

	@Test
	void testPreduslovImePopunjeno() {
		Ucenik u = new Ucenik();
		u.setIme("Marko");
		assertDoesNotThrow(() -> so.preduslov(u));
	}

	@Test
	void testPreduslovNivoPopunjen() {
		Ucenik u = new Ucenik();
		u.setPlesniNivo(new PlesniNivo(1L, "Pocetni", 1));
		assertDoesNotThrow(() -> so.preduslov(u));
	}

	@Test
	void testIzvrsiOperacijuVracaListu() throws Exception {
		Ucenik u = new Ucenik();
		u.setIme("Marko");

		List<Ucenik> lista = new ArrayList<>();
		lista.add(new Ucenik(1L, "Marko", "Markovic", "064", null));

		expect(repository.getAll(u)).andReturn((List) lista);
		replay(repository);

		so.izvrsiOperaciju(u);

		assertEquals(1, so.getUcenici().size());
		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuNemaRezultata() throws Exception {
		Ucenik u = new Ucenik();
		u.setIme("Nepostojeci");

		List<Ucenik> prazna = new ArrayList<>();

		expect(repository.getAll(u)).andReturn((List) prazna);
		replay(repository);

		Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(u));
		assertEquals("Sistem ne moze da nadje ucenike po zadatim kriterijumima!", ex.getMessage());
		verify(repository);
	}

}
