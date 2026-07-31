/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.sertifikat;

import domen.ApstraktniDomenskiObjekat;
import domen.Sertifikat;
import so.ApstraktnaSO;
import repository.Repository;

/**
 *
 * @author Una
 */
public class UbaciSertifikatSO extends ApstraktnaSO {
	public UbaciSertifikatSO() {
		super();
	}

	public UbaciSertifikatSO(Repository repository) {
		super(repository);
	}

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

	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		repository.add(ado);
	}
}