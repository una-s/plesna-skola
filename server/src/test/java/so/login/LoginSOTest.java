package so.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.easymock.EasyMock.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Profesor;
import domen.Ucenik;
import repository.db.DbRepository;

class LoginSOTest {

    LoginSO so;
    DbRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        repository = mock(DbRepository.class);
        so = new LoginSO(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
        repository = null;
    }

    @Test
    void testPreduslovNull() {
        Exception ex = assertThrows(Exception.class, () -> so.preduslov(null));
        assertEquals("Prosledjeni objekat nije instanca klase Profesor!", ex.getMessage());
    }

    @Test
    void testPreduslovNijeInstancaProfesor() {
        Ucenik u = new Ucenik();
        Exception ex = assertThrows(Exception.class, () -> so.preduslov(u));
        assertEquals("Prosledjeni objekat nije instanca klase Profesor!", ex.getMessage());
    }

    @Test
    void testPreduslovPraznoKorisnickoIme() {
        Profesor p = new Profesor();
        p.setKorisnickoIme("");
        p.setSifra("sifra123");
        Exception ex = assertThrows(Exception.class, () -> so.preduslov(p));
        assertEquals("Korisnicko ime ne moze biti prazno!", ex.getMessage());
    }

    @Test
    void testPreduslovNullKorisnickoIme() {
        Profesor p = new Profesor();
        p.setSifra("sifra123");
        Exception ex = assertThrows(Exception.class, () -> so.preduslov(p));
        assertEquals("Korisnicko ime ne moze biti prazno!", ex.getMessage());
    }

    @Test
    void testPreduslovPraznaSifra() {
        Profesor p = new Profesor();
        p.setKorisnickoIme("aanic");
        p.setSifra("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslov(p));
        assertEquals("Sifra ne moze biti prazna!", ex.getMessage());
    }

    @Test
    void testPreduslovIspravno() throws Exception {
        Profesor p = new Profesor();
        p.setKorisnickoIme("aanic");
        p.setSifra("sifra123");
        assertDoesNotThrow(() -> so.preduslov(p));
    }

    @Test
    void testIzvrsiOperacijuUspesno() throws Exception {
        Profesor ulazni = new Profesor();
        ulazni.setKorisnickoIme("aanic");
        ulazni.setSifra("sifra123");

        Profesor vraceni = new Profesor(1L, "Ana", "Anic", "064", "aanic", "sifra123");

        expect(repository.get(ulazni)).andReturn(vraceni);
        replay(repository);

        so.izvrsiOperaciju(ulazni);

        assertEquals(vraceni, so.getUlogovani());
        verify(repository);
    }

    @Test
    void testIzvrsiOperacijuNeuspesno() throws Exception {
        Profesor ulazni = new Profesor();
        ulazni.setKorisnickoIme("aanic");
        ulazni.setSifra("pogresna");

        expect(repository.get(ulazni)).andReturn(null);
        replay(repository);

        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(ulazni));
        assertEquals("Korisnicko ime i sifra nisu ispravni!", ex.getMessage());
        verify(repository);
    }
}