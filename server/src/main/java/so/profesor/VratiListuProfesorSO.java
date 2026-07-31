/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.profesor;

import domen.ApstraktniDomenskiObjekat;
import repository.Repository;
import domen.Profesor;
import java.util.List;
import so.ApstraktnaSO;

/**
 *
 * @author Una
 */

public class VratiListuProfesorSO extends ApstraktnaSO {

	private List<Profesor> profesori;

	public List<Profesor> getProfesori() {
		return profesori;
	}

	public VratiListuProfesorSO() {
		super();
	}

	public VratiListuProfesorSO(Repository repository) {
		super(repository);
	}

	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Profesor)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Profesor!");
		}
	}

	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Profesor pr = (Profesor) ado;
		profesori = (List<Profesor>) repository.getAll(pr);

		if (profesori == null) {
			throw new Exception("Zaposleni nisu vraceni!");
		}
	}
}
