package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import domen.Ucenik;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se brise ucenik iz baze podataka.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class ObrisiUcenikSO extends ApstraktnaSO {

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public ObrisiUcenikSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public ObrisiUcenikSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase Ucenik.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase Ucenik.
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Ucenik)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
		}
	}

	/**
	 * Brise ucenika iz baze podataka.
	 * 
	 * @param ado Domenski objekat (Ucenik) koji se brise.
	 * @throws java.lang.Exception ako dodje do greske prilikom brisanja
	 * ucenika.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Ucenik u = (Ucenik) ado;
		repository.delete(u);
	}
}