package so.cas;

import domen.ApstraktniDomenskiObjekat;
import domen.Cas;
import java.util.List;
import so.ApstraktnaSO;
import repository.Repository;

public class VratiListuCasSO extends ApstraktnaSO {

	private List<Cas> casovi;

	public VratiListuCasSO() {
		super();
	}

	public VratiListuCasSO(Repository repository) {
		super(repository);
	}

	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Cas)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Cas!");
		}
	}

	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Cas c = (Cas) ado;
		casovi = (List<Cas>) repository.getAll(c);

		if (casovi.isEmpty()) {
			throw new Exception("Casovi nisu vraceni!");
		}
	}

	public List<Cas> getCasovi() {
		return casovi;
	}
}