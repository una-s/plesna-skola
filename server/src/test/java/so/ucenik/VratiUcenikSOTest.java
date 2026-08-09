/**
 * 
 */
package so.ucenik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.Profesor;
import domen.Ucenik;
import repository.db.DbRepository;
import static org.easymock.EasyMock.*;

/**
* 
*/
class VratiUcenikSOTest {

	VratiUcenikSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new VratiUcenikSO(repository);
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
	void testPreduslovNullId() {
		Ucenik u = new Ucenik(); // ID je null (prazan Ucenik)
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Ucenik mora imati ID!", ex.getMessage());
	}

	@Test
	void testPreduslovNijeInstancaUcenik() {
		Profesor p = new Profesor();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(p));
		assertEquals("Prosledjeni objekat nije instanca klase Ucenik!", ex.getMessage());
	}

	@Test
	void testPreduslovBezId() {
		Ucenik u = new Ucenik();
		u.setIdUcenik(0L);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Ucenik mora imati ID!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		Ucenik u = new Ucenik();
		u.setIdUcenik(5L);
		assertDoesNotThrow(() -> so.preduslov(u));
	}

	@Test
	void testIzvrsiOperacijuUspesno() throws Exception {
		Ucenik ulazni = new Ucenik();
		ulazni.setIdUcenik(1L);

		Ucenik vraceni = new Ucenik(1L, "Marko", "Markovic", "064", null);

		expect(repository.get(ulazni)).andReturn(vraceni);
		replay(repository);

		so.izvrsiOperaciju(ulazni);

		assertEquals(vraceni, so.getUcenik());
		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuNeuspesno() throws Exception {
		Ucenik ulazni = new Ucenik();
		ulazni.setIdUcenik(1L);

		expect(repository.get(ulazni)).andReturn(null);
		replay(repository);

		Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(ulazni));
		assertEquals("Sistem ne moze da nadje ucenika!", ex.getMessage());
		verify(repository);
	}

}
