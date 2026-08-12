package so.plesninivo;

import domen.ApstraktniDomenskiObjekat;
import domen.PlesniNivo;
import java.util.List;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se vraca lista svih plesnih nivoa
 * iz sistema.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class VratiListuPlesniNivoSO extends ApstraktnaSO {

	/**
	 * Lista plesnih nivoa vracena iz baze podataka.
	 */
	private List<PlesniNivo> plesniNivoi;

	/**
	 * Vraca listu plesnih nivoa dobijenu izvrsavanjem operacije.
	 * 
	 * @return lista plesnih nivoa kao List&lt;PlesniNivo&gt;.
	 */
	public List<PlesniNivo> getPlesniNivoi() {
		return plesniNivoi;
	}

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public VratiListuPlesniNivoSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public VratiListuPlesniNivoSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase PlesniNivo.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase PlesniNivo.
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (ado == null || !(ado instanceof PlesniNivo)) {
			throw new Exception("Prosledjeni objekat nije instanca klase PlesniNivo!");
		}
	}

	/**
	 * Vraca listu svih plesnih nivoa iz baze i cuva je u internoj listi.
	 * 
	 * @param ado Domenski objekat (PlesniNivo) na osnovu koga se vraca lista.
	 * @throws java.lang.Exception ako nijedan plesni nivo nije vracen iz baze.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		PlesniNivo pn = (PlesniNivo) ado;
		plesniNivoi = repository.getAll(pn);
		if (plesniNivoi.isEmpty()) {
			throw new Exception("PlesniNivo-i nisu vraceni!");
		}
	}
}