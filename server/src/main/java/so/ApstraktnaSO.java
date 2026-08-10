package so;

import domen.ApstraktniDomenskiObjekat;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 * Predstavlja apstraktnu sistemsku operaciju koja definise zajednicki
 * tok izvrsavanja svih konkretnih sistemskih operacija u sistemu.
 * 
 * Koristi Template Method sablon - metoda izvrsi() definise fiksni
 * redosled koraka (provera preduslova, otvaranje transakcije, izvrsavanje
 * operacije, potvrda ili ponistavanje transakcije), dok konkretne
 * sistemske operacije implementiraju metode preduslov() i izvrsiOperaciju().
 * 
 * @author Una Stankovic
 * @version 1.0
 */
public abstract class ApstraktnaSO {

    /**
     * Repozitorijum preko koga sistemska operacija pristupa bazi podataka.
     */
    protected final Repository repository;

    /**
     * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom
     * (DbRepositoryGeneric).
     */
    public ApstraktnaSO() {
        this.repository = new DbRepositoryGeneric();
    }

    /**
     * Kreira objekat sistemske operacije sa unetim repozitorijumom.
     * 
     * Koristi se prvenstveno za testiranje, kada se umesto pravog
     * repozitorijuma prosledjuje lazni (mock) objekat.
     * 
     * @param repository Repozitorijum koji sistemska operacija koristi.
     */
    public ApstraktnaSO(Repository repository) {
        this.repository = repository;
    }

    /**
     * Izvrsava sistemsku operaciju kroz fiksni redosled koraka.
     * 
     * Redom se poziva: provera preduslova, otvaranje transakcije,
     * izvrsavanje konkretne operacije i potvrda transakcije. Ako u bilo
     * kom koraku dodje do greske, transakcija se ponistava i greska se
     * prosledjuje dalje. U svakom slucaju, na kraju se zatvara konekcija
     * sa bazom.
     * 
     * @param ado Domenski objekat nad kojim se izvrsava operacija.
     * @throws java.lang.Exception ako provera preduslova ne prodje ili
     * ako dodje do greske prilikom izvrsavanja operacije.
     */
    public void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            preduslov(ado);
            zapocniTransakciju();
            izvrsiOperaciju(ado);
            potvrdiTransakciju();
            System.out.println("Uspesno izvrsena operacija!");
        } catch (Exception ex) {
            System.out.println("Neuspesno izvrsena operacija!");
            ponistiTransakciju();
            throw ex;
        } finally {
            ((DbRepository) repository).disconnect();
        }
    }

    /**
     * Proverava da li su ispunjeni preduslovi za izvrsavanje operacije.
     * 
     * Konkretne sistemske operacije implementiraju ovu metodu tako da
     * proveravaju ispravnost prosledjenog domenskog objekta.
     * 
     * @param ado Domenski objekat ciji se preduslovi proveravaju.
     * @throws java.lang.Exception ako preduslovi nisu ispunjeni.
     */
    protected abstract void preduslov(ApstraktniDomenskiObjekat ado) throws Exception;

    /**
     * Izvrsava konkretnu operaciju nad prosledjenim domenskim objektom.
     * 
     * Konkretne sistemske operacije implementiraju ovu metodu sa svojom
     * specificnom logikom (dodavanje, izmena, brisanje, pretraga...).
     * 
     * @param ado Domenski objekat nad kojim se izvrsava operacija.
     * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja
     * operacije.
     */
    protected abstract void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DbRepository) repository).connect();
    }

    /**
     * Potvrdjuje (commit) tekucu transakciju nad bazom podataka.
     * 
     * @throws java.lang.Exception ako dodje do greske prilikom potvrde
     * transakcije.
     */
    protected void potvrdiTransakciju() throws Exception {
        ((DbRepository) repository).commit();
    }

    /**
     * Ponistava (rollback) tekucu transakciju nad bazom podataka.
     * 
     * @throws java.lang.Exception ako dodje do greske prilikom ponistavanja
     * transakcije.
     */
    protected void ponistiTransakciju() throws Exception {
        ((DbRepository) repository).rollback();
    }
}