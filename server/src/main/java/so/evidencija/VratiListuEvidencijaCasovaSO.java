package so.evidencija;

import domen.ApstraktniDomenskiObjekat;
import domen.EvidencijaCasova;
import java.util.List;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se vraca lista svih evidencija
 * casova iz sistema.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class VratiListuEvidencijaCasovaSO extends ApstraktnaSO {

	/**
	 * Lista evidencija casova vracena iz baze podataka.
	 */
	private List<EvidencijaCasova> evidencije;

	/**
	 * Vraca listu evidencija dobijenu izvrsavanjem operacije.
	 * 
	 * @return lista evidencija kao List&lt;EvidencijaCasova&gt;.
	 */
	public List<EvidencijaCasova> getEvidencije() {
		return evidencije;
	}

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public VratiListuEvidencijaCasovaSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public VratiListuEvidencijaCasovaSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase EvidencijaCasova.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase
	 * EvidencijaCasova.
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (ado == null || !(ado instanceof EvidencijaCasova)) {
			throw new Exception("Prosledjeni objekat nije instanca klase EvidencijeCasova!");
		}
	}

	/**
	 * Vraca listu svih evidencija casova iz baze i cuva je u internoj listi.
	 * 
	 * @param ado Domenski objekat (EvidencijaCasova) na osnovu koga se
	 * vraca lista.
	 * @throws java.lang.Exception ako dodje do greske prilikom vracanja
	 * evidencija iz baze.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		EvidencijaCasova ev = (EvidencijaCasova) ado;
		evidencije = repository.getAll(ev);
		if (evidencije.isEmpty()) {
			System.out.println("Nema evidencije casova!");
		}
	}
}