package so.cas;

import static org.easymock.EasyMock.mock;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Cas;
import domen.Ucenik;
import repository.db.DbRepository;

class UveziCasoveIzJsonSOTest {

	UveziCasoveIzJsonSO so;
	DbRepository repository;
	final String putanja = "test_uvoz_casovi.json";

	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new UveziCasoveIzJsonSO(repository, putanja);

		// napravi test JSON fajl sa dva casa
		String jsonSadrzaj = "[\n"
				+ "  {\n"
				+ "    \"idCas\": 1,\n"
				+ "    \"naziv\": \"Salsa\",\n"
				+ "    \"trajanje\": 60,\n"
				+ "    \"stilPlesa\": \"Latino\"\n"
				+ "  },\n"
				+ "  {\n"
				+ "    \"idCas\": 2,\n"
				+ "    \"naziv\": \"Valcer\",\n"
				+ "    \"trajanje\": 45,\n"
				+ "    \"stilPlesa\": \"Standardni\"\n"
				+ "  }\n"
				+ "]";
		try (FileWriter writer = new FileWriter(putanja)) {
			writer.write(jsonSadrzaj);
		}
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
		UveziCasoveIzJsonSO soBezPutanje = new UveziCasoveIzJsonSO(repository, "");
		Exception ex = assertThrows(Exception.class, () -> soBezPutanje.preduslov(new Cas()));
		assertEquals("Putanja do JSON fajla ne sme biti prazna!", ex.getMessage());
	}

	@Test
	void testPreduslovIspravno() throws Exception {
		assertDoesNotThrow(() -> so.preduslov(new Cas()));
	}

	@Test
	void testIzvrsiOperacijuUcitavaCasoveIzFajla() throws Exception {
		so.izvrsiOperaciju(new Cas());

		assertEquals(2, so.getCasovi().size(), "Treba da ucita dva casa");

		Cas prvi = so.getCasovi().get(0);
		assertEquals("Salsa", prvi.getNaziv());
		assertEquals(60, prvi.getTrajanje());
		assertEquals("Latino", prvi.getStilPlesa());

		Cas drugi = so.getCasovi().get(1);
		assertEquals("Valcer", drugi.getNaziv());
		assertEquals(45, drugi.getTrajanje());
		assertEquals("Standardni", drugi.getStilPlesa());
	}
}