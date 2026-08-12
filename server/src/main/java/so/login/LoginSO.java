package so.login;

import domen.ApstraktniDomenskiObjekat;
import domen.Profesor;
import repository.Repository;
import so.ApstraktnaSO;

/**
 * Predstavlja sistemsku operaciju kojom se profesor prijavljuje na sistem
 * na osnovu korisnickog imena i sifre.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class LoginSO extends ApstraktnaSO {

	/**
	 * Profesor koji je uspesno prijavljen na sistem.
	 */
	private Profesor ulogovani;

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public LoginSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public LoginSO(Repository repository) {
		super(repository);
	}

	/**
	 * Vraca prijavljenog profesora dobijenog izvrsavanjem operacije.
	 * 
	 * @return ulogovani profesor kao objekat klase Profesor.
	 */
	public Profesor getUlogovani() {
		return ulogovani;
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase Profesor
	 * i da li su uneti korisnicko ime i sifra.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase Profesor,
	 * ako je korisnicko ime prazno ili ako je sifra prazna.
	 */
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

	/**
	 * Pronalazi profesora sa unetim korisnickim imenom i sifrom i postavlja
	 * ga kao prijavljenog korisnika.
	 * 
	 * @param ado Domenski objekat (Profesor) sa korisnickim imenom i sifrom.
	 * @throws java.lang.Exception ako profesor sa unetim korisnickim imenom
	 * i sifrom ne postoji.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Profesor pr = (Profesor) ado;
		ulogovani = (Profesor) repository.get(pr);
		if (ulogovani == null) {
			throw new Exception("Korisnicko ime i sifra nisu ispravni!");
		}
	}
}