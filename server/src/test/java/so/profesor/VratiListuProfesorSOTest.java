/**
 * 
 */
package so.profesor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Profesor;
import domen.Ucenik;
import repository.db.DbRepository;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

/**
* 
*/
class VratiListuProfesorSOTest {

	VratiListuProfesorSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new VratiListuProfesorSO(repository);
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
	void testPreduslovNijeInstancaProfesor() {
		Ucenik u = new Ucenik();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Prosledjeni objekat nije instanca klase Profesor!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		Profesor p = new Profesor();
		assertDoesNotThrow(() -> so.preduslov(p));
	}

	@Test
	void testIzvrsiOperacijuVracaListu() throws Exception {
		Profesor p = new Profesor();

		List<Profesor> lista = new ArrayList<>();
		lista.add(new Profesor(1L, "Ana", "Anic", "064", "aanic", "sifra"));
		lista.add(new Profesor(2L, "Marko", "Maric", "065", "mmaric", "sifra2"));

		expect(repository.getAll(p)).andReturn((List) lista);
		replay(repository);

		so.izvrsiOperaciju(p);

		assertEquals(2, so.getProfesori().size());
		assertEquals(lista, so.getProfesori());
		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuNull() throws Exception {
		Profesor p = new Profesor();

		expect(repository.getAll(p)).andReturn(null);
		replay(repository);

		Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(p));
		assertEquals("Zaposleni nisu vraceni!", ex.getMessage());
		verify(repository);
	}

}
