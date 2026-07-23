package so.evidencija;

import domen.ApstraktniDomenskiObjekat;
import domen.EvidencijaCasova;
import domen.StavkaEvidencijeCasova;
import java.util.Calendar;
import java.util.List;
import so.ApstraktnaSO;

/**
 *
 * @author Una
 */
public class PromeniEvidencijaCasovaSO extends ApstraktnaSO {

    @Override
    protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof EvidencijaCasova)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaCasova!");
        }
        EvidencijaCasova ev = (EvidencijaCasova) ado;
        if (ev.getProfesor() == null) {
            throw new Exception("Profesor mora biti izabran!");
        }
        if (ev.getUcenik() == null) {
            throw new Exception("Ucenik mora biti izabran!");
        }
        if (ev.getSkolskaGodina() == null || ev.getSkolskaGodina().isEmpty()) {
            throw new Exception("Skolska godina ne sme biti prazna!");
        }
        if (!ev.getSkolskaGodina().matches(".*/.+")) {
            throw new Exception("Skolska godina mora biti u formatu 'xxxx/xxxx'!");
        }
        if (ev.getDatumPocetka() == null) {
            throw new Exception("Datum pocetka ne sme biti null!");
        }
        if (ev.getDatumZavrsetka() == null) {
            throw new Exception("Datum zavrsetka ne sme biti null!");
        }
        if (ev.getDatumZavrsetka().before(ev.getDatumPocetka())) {
            throw new Exception("Datum zavrsetka mora biti nakon datuma pocetka!");
        }
        if (ev.getStavke() == null || ev.getStavke().isEmpty()) {
            throw new Exception("Evidencija casova mora imati bar jednu stavku!");
        }

        // provera skolske godine i datuma
        String[] godine = ev.getSkolskaGodina().split("/");
        int godinaOd = Integer.parseInt(godine[0]);
        int godinaDo = Integer.parseInt(godine[1]);

        Calendar cal = Calendar.getInstance();

        cal.setTime(ev.getDatumPocetka());
        if (cal.get(Calendar.YEAR) != godinaOd) {
            throw new Exception("Godina datuma pocetka mora biti jednaka prvoj godini skolske godine!");
        }

        cal.setTime(ev.getDatumZavrsetka());
        if (cal.get(Calendar.YEAR) != godinaDo) {
            throw new Exception("Godina datuma zavrsetka mora biti jednaka drugoj godini skolske godine!");
        }

        // provera stavki
        for (StavkaEvidencijeCasova stavka : ev.getStavke()) {
            if (stavka.getCas() == null) {
                throw new Exception("Cas u stavci ne sme biti null!");
            }
            if (stavka.getOcena() < 1 || stavka.getOcena() > 5) {
                throw new Exception("Ocena mora biti izmedju 1 i 5!");
            }
            if (stavka.getDatumPrisustva() == null) {
                throw new Exception("Datum prisustva ne sme biti null!");
            }
            if (stavka.getDatumPrisustva().before(ev.getDatumPocetka()) ||
                stavka.getDatumPrisustva().after(ev.getDatumZavrsetka())) {
                throw new Exception("Datum prisustva mora biti izmedju datuma pocetka i zavrsetka evidencije!");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        EvidencijaCasova ec = (EvidencijaCasova) ado;
        repository.edit(ec);
        StavkaEvidencijeCasova pomocna = new StavkaEvidencijeCasova();
        pomocna.setEvidencijaCasova(ec);
        List<StavkaEvidencijeCasova> stareStavke = (List<StavkaEvidencijeCasova>) repository.getAll(pomocna);
        for (StavkaEvidencijeCasova stara : stareStavke) {
            if (!ec.getStavke().contains(stara)) {
                repository.delete(stara);
            }
        }
        for (StavkaEvidencijeCasova nova : ec.getStavke()) {
            nova.setEvidencijaCasova(ec);
            if (stareStavke.contains(nova)) {
                repository.edit(nova);
            } else {
                repository.add(nova);
            }
        }
    }
}