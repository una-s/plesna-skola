package so.sertifikat;

import domen.ApstraktniDomenskiObjekat;
import domen.Sertifikat;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se dodaje novi sertifikat
 * u bazu podataka.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class UbaciSertifikatSO extends ApstraktnaSO {

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public UbaciSertifikatSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public UbaciSertifikatSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase Sertifikat
	 * i da li su uneti institucija i naziv sertifikata.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako je objekat null, ako nije instanca
	 * klase Sertifikat, ako je institucija prazna ili ako je naziv
	 * sertifikata prazan.
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (ado == null)
			throw new Exception("Sertifikat ne sme biti null!");
		if (!(ado instanceof Sertifikat))
			throw new Exception("Prosledjeni objekat nije instanca klase Sertifikat!");
		Sertifikat s = (Sertifikat) ado;
		if (s.getInstitucija() == null || s.getInstitucija().isEmpty())
			throw new Exception("Institucija ne sme biti prazna!");
		if (s.getNaziv() == null || s.getNaziv().isEmpty())
			throw new Exception("Naziv sertifikata ne sme biti prazan!");
	}

	/**
	 * Dodaje sertifikat u bazu podataka.
	 * 
	 * @param ado Domenski objekat (Sertifikat) koji se dodaje.
	 * @throws java.lang.Exception ako dodje do greske prilikom dodavanja
	 * sertifikata.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		repository.add(ado);
	}
}