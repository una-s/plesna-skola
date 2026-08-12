package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import repository.Repository;
import domen.Ucenik;
import java.util.List;
import so.ApstraktnaSO;

/**
 * Predstavlja sistemsku operaciju kojom se vraca jedan ucenik iz baze
 * podataka na osnovu identifikatora.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class VratiUcenikSO extends ApstraktnaSO {

	/**
	 * Ucenik vracen iz baze podataka.
	 */
	private Ucenik ucenik;

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public VratiUcenikSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public VratiUcenikSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase Ucenik
	 * i da li ima postavljen identifikator.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase Ucenik
	 * ili ako nema postavljen identifikator (null ili nula).
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Ucenik)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
		}
		Ucenik u = (Ucenik) ado;
		if (u.getIdUcenik() == null || u.getIdUcenik() == 0) {
			throw new Exception("Ucenik mora imati ID!");
		}
	}

	/**
	 * Vraca ucenika iz baze na osnovu identifikatora.
	 * 
	 * @param ado Domenski objekat (Ucenik) sa identifikatorom.
	 * @throws java.lang.Exception ako ucenik sa zadatim identifikatorom
	 * ne postoji.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Ucenik u = (Ucenik) ado;
		ucenik = (Ucenik) repository.get(u);
		if (ucenik == null) {
			throw new Exception("Sistem ne moze da nadje ucenika!");
		}
	}

	/**
	 * Vraca ucenika dobijenog izvrsavanjem operacije.
	 * 
	 * @return ucenik kao objekat klase Ucenik.
	 */
	public Ucenik getUcenik() {
		return ucenik;
	}
}