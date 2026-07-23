package so.evidencija;

import domen.ApstraktniDomenskiObjekat;
import domen.EvidencijaCasova;
import domen.StavkaEvidencijeCasova;
import java.util.List;
import so.ApstraktnaSO;

public class VratiEvidencijaCasovaSO extends ApstraktnaSO {

    private EvidencijaCasova evidencija;

    public EvidencijaCasova getEvidencija() {
        return evidencija;
    }

    @Override
    protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof EvidencijaCasova)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaCasova!");
        }
        EvidencijaCasova ev = (EvidencijaCasova) ado;
        if (ev.getIdEvidencijaCasova() == null || ev.getIdEvidencijaCasova() <= 0) {
            throw new Exception("EvidencijaCasova mora imati validan ID!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        evidencija = (EvidencijaCasova) repository.get(ado);
        if (evidencija == null) {
            throw new Exception("Sistem ne moze da nadje evidenciju casova!");
        }
        StavkaEvidencijeCasova pomocna = new StavkaEvidencijeCasova();
        pomocna.setEvidencijaCasova(evidencija);
        List<StavkaEvidencijeCasova> stavke = (List<StavkaEvidencijeCasova>) repository.getAll(pomocna);
        evidencija.setStavke(stavke);
    }
}
