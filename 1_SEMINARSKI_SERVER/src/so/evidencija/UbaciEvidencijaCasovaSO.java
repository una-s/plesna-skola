package so.evidencija;

import domen.ApstraktniDomenskiObjekat;
import domen.EvidencijaCasova;
import domen.StavkaEvidencijeCasova;
import java.util.Calendar;
import so.ApstraktnaSO;

/**
 *
 * @author Una
 */
public class UbaciEvidencijaCasovaSO extends ApstraktnaSO {

    @Override
    protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null) {
            throw new Exception("Evidencija ne sme biti null!");
        }
        if (!(ado instanceof EvidencijaCasova)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaCasova!");
        }
        EvidencijaCasova ec = (EvidencijaCasova) ado;
        if (ec.getSkolskaGodina() == null || ec.getSkolskaGodina().isEmpty()) {
            throw new Exception("Skolska godina ne sme biti prazna!");
        }
        if (ec.getDatumPocetka() == null) {
            throw new Exception("Datum pocetka ne sme biti null!");
        }
        if (ec.getDatumZavrsetka() == null) {
            throw new Exception("Datum zavrsetka ne sme biti null!");
        }
        if (ec.getDatumZavrsetka().before(ec.getDatumPocetka())) {
            throw new Exception("Datum zavrsetka mora biti nakon datuma pocetka!");
        }
        // skolskaGodina LIKE '%/%'
        if (!ec.getSkolskaGodina().matches(".*/.+")) {
            throw new Exception("Skolska godina mora biti u formatu 'xxxx/xxxx'!");
        }
        if (ec.getStavke() == null || ec.getStavke().isEmpty()) {
            throw new Exception("Evidencija mora imati bar jednu stavku!");
        }
        if (ec.getProfesor() == null) {
            throw new Exception("Profesor ne sme biti null!");
        }
        if (ec.getUcenik() == null) {
            throw new Exception("Ucenik ne sme biti null!");
        }

        // provera skolske godine i datuma
        String[] godine = ec.getSkolskaGodina().split("/");
        int godinaOd = Integer.parseInt(godine[0]);
        int godinaDo = Integer.parseInt(godine[1]);

        Calendar cal = Calendar.getInstance();

        cal.setTime(ec.getDatumPocetka());
        if (cal.get(Calendar.YEAR) != godinaOd) {
            throw new Exception("Godina datuma pocetka mora biti jednaka prvoj godini skolske godine!");
        }

        cal.setTime(ec.getDatumZavrsetka());
        if (cal.get(Calendar.YEAR) != godinaDo) {
            throw new Exception("Godina datuma zavrsetka mora biti jednaka drugoj godini skolske godine!");
        }

        // provera stavki
        for (StavkaEvidencijeCasova stavka : ec.getStavke()) {
            if (stavka.getCas() == null) {
                throw new Exception("Cas u stavci ne sme biti null!");
            }
            if (stavka.getOcena() < 1 || stavka.getOcena() > 5) {
                throw new Exception("Ocena mora biti izmedju 1 i 5!");
            }
            if (stavka.getDatumPrisustva() == null) {
                throw new Exception("Datum prisustva ne sme biti null!");
            }
            if (stavka.getDatumPrisustva().before(ec.getDatumPocetka()) ||
                stavka.getDatumPrisustva().after(ec.getDatumZavrsetka())) {
                throw new Exception("Datum prisustva mora biti izmedju datuma pocetka i zavrsetka evidencije!");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        EvidencijaCasova evidencija = (EvidencijaCasova) ado;
        repository.add(evidencija);
        for (StavkaEvidencijeCasova s : evidencija.getStavke()) {
            s.setEvidencijaCasova(evidencija);
            repository.add(s);
        }
    }
}