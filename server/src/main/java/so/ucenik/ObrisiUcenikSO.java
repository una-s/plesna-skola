/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import domen.Ucenik;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * 
 * @author Una
 */

public class ObrisiUcenikSO extends ApstraktnaSO {

	public ObrisiUcenikSO() {
		super();
	}

	public ObrisiUcenikSO(Repository repository) {
		super(repository);
	}

	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Ucenik)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
		}
	}

	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Ucenik u = (Ucenik) ado;
		repository.delete(u);
	}
}