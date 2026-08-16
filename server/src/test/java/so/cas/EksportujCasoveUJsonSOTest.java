package so.cas;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.ApstraktniDomenskiObjekat;
import domen.Cas;
import domen.Ucenik;
import repository.db.DbRepository;

class EksportujCasoveUJsonSOTest {

	EksportujCasoveUJsonSO so;
	DbRepository repository;
	final String putanja = "test_casovi.json";

	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new EksportujCasoveUJsonSO(repository, putanja);
	}

	@AfterEach
	void tearDown() throws Exception {
		File f = new File(putanja);
		if (f.exists()) {
			f.delete();
		}
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
	void testPreduslovPraznaPutanja() {
		EksportujCasoveUJsonSO soBezPutanje = new EksportujCasoveUJsonSO(repository, "");
		Exception ex = assertThrows(Exception.class, () -> soBezPutanje.preduslov(new Cas()));
		assertEquals("Putanja do JSON fajla ne sme biti prazna!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() throws Exception {
		assertDoesNotThrow(() -> so.preduslov(new Cas()));
	}

	@Test
	void testIzvrsiOperacijuUpisujeCasoveUFajl() throws Exception {
		List<ApstraktniDomenskiObjekat> casovi = new ArrayList<>();
		casovi.add(new Cas(1L, "Salsa", 60, "Latino"));
		casovi.add(new Cas(2L, "Valcer", 45, "Standardni"));

		expect(repository.getAll(anyObject(ApstraktniDomenskiObjekat.class))).andReturn(casovi);
		replay(repository);

		so.izvrsiOperaciju(new Cas());

		verify(repository);

		String sadrzaj = new String(Files.readAllBytes(new File(putanja).toPath()));

		assertTrue(sadrzaj.contains("Salsa"), "Fajl treba da sadrzi prvi cas");
		assertTrue(sadrzaj.contains("Latino"), "Fajl treba da sadrzi stil prvog casa");
		assertTrue(sadrzaj.contains("Valcer"), "Fajl treba da sadrzi drugi cas");
		assertTrue(sadrzaj.contains("Standardni"), "Fajl treba da sadrzi stil drugog casa");
		assertTrue(sadrzaj.contains("naziv"), "Fajl treba da sadrzi naziv atributa");
	}
}