/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import repository.Repository;
import domen.Ucenik;
import java.util.List;
import so.ApstraktnaSO;

/**
 * 
 * @author Una
 */

public class VratiUcenikSO extends ApstraktnaSO {

	private Ucenik ucenik;

	public VratiUcenikSO() {
		super();
	}

	public VratiUcenikSO(Repository repository) {
		super(repository);
	}

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

	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Ucenik u = (Ucenik) ado;
		ucenik = (Ucenik) repository.get(u);

		if (ucenik == null) {
			throw new Exception("Sistem ne moze da nadje ucenika!");
		}
	}

	public Ucenik getUcenik() {
		return ucenik;
	}
}
