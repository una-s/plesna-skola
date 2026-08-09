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

public class PretraziUcenikSO extends ApstraktnaSO {

	private List<Ucenik> ucenici;

	public List<Ucenik> getUcenici() {
		return ucenici;
	}

	public PretraziUcenikSO() {
		super();
	}

	public PretraziUcenikSO(Repository repository) {
		super(repository);
	}

	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Ucenik)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
		}

		Ucenik u = (Ucenik) ado;

		boolean imePrazno = u.getIme() == null || u.getIme().isEmpty();
		boolean prezimePrazno = u.getPrezime() == null || u.getPrezime().isEmpty();
		boolean nivoPrazan = u.getPlesniNivo() == null;

		if (imePrazno && prezimePrazno && nivoPrazan) {
			throw new Exception("Barem jedan kriterijum za pretragu mora biti popunjen!");
		}
	}

	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Ucenik u = (Ucenik) ado;
		ucenici = repository.getAll(u);

		if (ucenici.isEmpty()) {
			throw new Exception("Sistem ne moze da nadje ucenike po zadatim kriterijumima!");
		}
	}
}