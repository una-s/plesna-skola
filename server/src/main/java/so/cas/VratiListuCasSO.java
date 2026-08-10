package so.cas;

import domen.ApstraktniDomenskiObjekat;
import domen.Cas;
import java.util.List;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se vraca lista svih casova
 * iz sistema.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class VratiListuCasSO extends ApstraktnaSO {

	/**
	 * Lista casova vracena iz baze podataka.
	 */
	private List<Cas> casovi;

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public VratiListuCasSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public VratiListuCasSO(Repository repository) {
		super(repository);
	}

	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Cas)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Cas!");
		}
	}

	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Cas c = (Cas) ado;
		casovi = (List<Cas>) repository.getAll(c);
		if (casovi.isEmpty()) {
			throw new Exception("Casovi nisu vraceni!");
		}
	}

	/**
	 * Vraca listu casova dobijenu izvrsavanjem operacije.
	 * 
	 * @return lista casova kao List&lt;Cas&gt;.
	 */
	public List<Cas> getCasovi() {
		return casovi;
	}
}