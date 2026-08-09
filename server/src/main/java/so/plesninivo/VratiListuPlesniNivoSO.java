/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.plesninivo;

import domen.ApstraktniDomenskiObjekat;
import domen.PlesniNivo;
import java.util.List;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * 
 * @author Una
 */
public class VratiListuPlesniNivoSO extends ApstraktnaSO {

	private List<PlesniNivo> plesniNivoi;

	public List<PlesniNivo> getPlesniNivoi() {
		return plesniNivoi;
	}

	public VratiListuPlesniNivoSO() {
		super();
	}

	public VratiListuPlesniNivoSO(Repository repository) {
		super(repository);
	}

	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (ado == null || !(ado instanceof PlesniNivo)) {
			throw new Exception("Prosledjeni objekat nije instanca klase PlesniNivo!");
		}
	}

	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		PlesniNivo pn = (PlesniNivo) ado;
		plesniNivoi = repository.getAll(pn);

		if (plesniNivoi.isEmpty()) {
			throw new Exception("PlesniNivo-i nisu vraceni!");
		}
	}
}