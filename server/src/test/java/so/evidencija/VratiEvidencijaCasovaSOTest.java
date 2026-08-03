/**
 * 
 */
package so.evidencija;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import domen.Cas;
import domen.EvidencijaCasova;
import domen.StavkaEvidencijeCasova;
import domen.Ucenik;
import repository.db.DbRepository;

/**
 * 
 */
class VratiEvidencijaCasovaSOTest {

	VratiEvidencijaCasovaSO so;
	DbRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new VratiEvidencijaCasovaSO(repository);
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
	void testPreduslovNullId() {
		EvidencijaCasova ec = new EvidencijaCasova();
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("EvidencijaCasova mora imati validan ID!", ex.getMessage());
	}

	@Test
	void testPreduslovIdNula() {
		EvidencijaCasova ec = new EvidencijaCasova();
		ec.setIdEvidencijaCasova(0L);
		Exception ex = assertThrows(Exception.class, () -> so.preduslov(ec));
		assertEquals("EvidencijaCasova mora imati validan ID!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() {
		EvidencijaCasova ec = new EvidencijaCasova();
		ec.setIdEvidencijaCasova(5L);
		assertDoesNotThrow(() -> so.preduslov(ec));
	}

	@Test
	void testIzvrsiOperacijuUspesno() throws Exception {
		EvidencijaCasova ulazna = new EvidencijaCasova();
		ulazna.setIdEvidencijaCasova(1L);

		EvidencijaCasova vracena = new EvidencijaCasova();
		vracena.setIdEvidencijaCasova(1L);
		vracena.setSkolskaGodina("2025/2026");

		List<StavkaEvidencijeCasova> stavke = new ArrayList<>();
		StavkaEvidencijeCasova s = new StavkaEvidencijeCasova();
		s.setCas(new Cas(1L, "Salsa", 60, "Latino"));
		s.setOcena(5);
		stavke.add(s);

		expect(repository.get(ulazna)).andReturn(vracena);
		expect(repository.getAll(anyObject(StavkaEvidencijeCasova.class))).andReturn((List) stavke);
		replay(repository);

		so.izvrsiOperaciju(ulazna);

		assertEquals(vracena, so.getEvidencija());
		assertEquals(stavke, so.getEvidencija().getStavke());
		verify(repository);
	}

	@Test
	void testIzvrsiOperacijuNeuspesno() throws Exception {
		EvidencijaCasova ulazna = new EvidencijaCasova();
		ulazna.setIdEvidencijaCasova(1L);

		expect(repository.get(ulazna)).andReturn(null);
		replay(repository);

		Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(ulazna));
		assertEquals("Sistem ne moze da nadje evidenciju casova!", ex.getMessage());
		verify(repository);
	}

}
