/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import domen.ApstraktniDomenskiObjekat;
import domen.Profesor;
import repository.Repository;
import so.ApstraktnaSO;

/**
 * 
 * @author Una
 */

public class LoginSO extends ApstraktnaSO {

	private Profesor ulogovani;

	public LoginSO() {
		super();
	}

	public LoginSO(Repository repository) {
		super(repository);
	}

	public Profesor getUlogovani() {
		return ulogovani;
	}

	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (ado == null || !(ado instanceof Profesor)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Profesor!");
		}

		Profesor pr = (Profesor) ado;
		if (pr.getKorisnickoIme() == null || pr.getKorisnickoIme().isEmpty()) {
			throw new Exception("Korisnicko ime ne moze biti prazno!");
		}
		if (pr.getSifra() == null || pr.getSifra().isEmpty()) {
			throw new Exception("Sifra ne moze biti prazna!");
		}
	}

	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Profesor pr = (Profesor) ado;
		ulogovani = (Profesor) repository.get(pr);

		if (ulogovani == null) {
			throw new Exception("Korisnicko ime i sifra nisu ispravni!");
		}
	}
}