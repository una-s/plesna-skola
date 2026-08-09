/**
 * 
 */
package so.sertifikat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.*;

import domen.Sertifikat;
import domen.Ucenik;
import repository.db.DbRepository;

/**
 * 
 */
class UbaciSertifikatSOTest {

	UbaciSertifikatSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new UbaciSertifikatSO(repository);
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
		assertEquals("Sertifikat ne sme biti null!", ex.getMessage());
	}

	@Test
	void testPreduslovNijeInstancaSertifikat() {
		Ucenik u = new Ucenik();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
		assertEquals("Prosledjeni objekat nije instanca klase Sertifikat!", ex.getMessage());
	}

	@Test
	void testPreduslovPraznaInstitucija() {
		Sertifikat s = new Sertifikat();
		s.setInstitucija("");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(s));
		assertEquals("Institucija ne sme biti prazna!", ex.getMessage());
	}

	@Test
	void testPreduslovPrazanNaziv() {
		Sertifikat s = new Sertifikat();
		s.setInstitucija("FON");
		s.setNaziv("");
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(s));
		assertEquals("Naziv sertifikata ne sme biti prazan!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		Sertifikat s = new Sertifikat(1L, "FON", "Salsa instruktor");
		assertDoesNotThrow(() -> so.preduslov(s));
	}

	@Test
	void testIzvrsiOperaciju() throws Exception {
		Sertifikat s = new Sertifikat(1L, "FON", "Salsa instruktor");

		repository.add(s);
		expectLastCall();
		replay(repository);

		so.izvrsiOperaciju(s);

		verify(repository);
	}

}
