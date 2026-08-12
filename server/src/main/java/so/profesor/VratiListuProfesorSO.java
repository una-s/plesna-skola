package so.profesor;

import domen.ApstraktniDomenskiObjekat;
import repository.Repository;
import domen.Profesor;
import java.util.List;
import so.ApstraktnaSO;

/**
 * Predstavlja sistemsku operaciju kojom se vraca lista svih profesora
 * iz sistema.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class VratiListuProfesorSO extends ApstraktnaSO {

	/**
	 * Lista profesora vracena iz baze podataka.
	 */
	private List<Profesor> profesori;

	/**
	 * Vraca listu profesora dobijenu izvrsavanjem operacije.
	 * 
	 * @return lista profesora kao List&lt;Profesor&gt;.
	 */
	public List<Profesor> getProfesori() {
		return profesori;
	}

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public VratiListuProfesorSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public VratiListuProfesorSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase Profesor.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase Profesor.
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Profesor)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Profesor!");
		}
	}

	/**
	 * Vraca listu svih profesora iz baze i cuva je u internoj listi.
	 * 
	 * @param ado Domenski objekat (Profesor) na osnovu koga se vraca lista.
	 * @throws java.lang.Exception ako lista profesora nije vracena iz baze.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Profesor pr = (Profesor) ado;
		profesori = (List<Profesor>) repository.getAll(pr);
		if (profesori == null) {
			throw new Exception("Zaposleni nisu vraceni!");
		}
	}
}