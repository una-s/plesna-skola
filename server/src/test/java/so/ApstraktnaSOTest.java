/**
 * 
 */
package so;

import static org.junit.jupiter.api.Assertions.*;
import static org.easymock.EasyMock.*;

import domen.ApstraktniDomenskiObjekat;
import domen.Ucenik;
import repository.Repository;
import repository.db.DbRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class ApstraktnaSOTest {

	DbRepository repository;

	// Pomocna test implementacija ApstraktnaSO - prazan preduslov i
	// izvrsiOperaciju,
	// sluzi da testiramo samo tok metode izvrsi()
	private class TestSO extends ApstraktnaSO {
		private boolean bacaGresku = false;

		TestSO(Repository repository) {
			super(repository);
		}

		void postaviBacanjeGreske(boolean b) {
			this.bacaGresku = b;
		}

		@Override
		protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
			// prazno - izolujemo tok izvrsi()
		}

		@Override
		protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
			if (bacaGresku) {
				throw new Exception("Greska u operaciji!");
			}
			// inace ne radi nista
		}
	}

	TestSO so;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(DbRepository.class);
		so = new TestSO(repository);
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
	void testIzvrsiUspesno() throws Exception {
		Ucenik ado = new Ucenik();

		// uspesan tok: connect -> commit -> disconnect (rollback se NE poziva)
		repository.connect();
		expectLastCall();
		repository.commit();
		expectLastCall();
		repository.disconnect();
		expectLastCall();
		replay(repository);

		so.postaviBacanjeGreske(false);
		so.izvrsi(ado);

		verify(repository);
	}

	@Test
	void testIzvrsiNeuspesnoRollback() throws Exception {
		Ucenik ado = new Ucenik();

		// neuspesan tok: connect -> rollback -> disconnect (commit se NE poziva)
		repository.connect();
		expectLastCall();
		repository.rollback();
		expectLastCall();
		repository.disconnect();
		expectLastCall();
		replay(repository);

		so.postaviBacanjeGreske(true);

		Exception ex = assertThrows(Exception.class, () -> so.izvrsi(ado));
		assertEquals("Greska u operaciji!", ex.getMessage());

		verify(repository);
	}

}
