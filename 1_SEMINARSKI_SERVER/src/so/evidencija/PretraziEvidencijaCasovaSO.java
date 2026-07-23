/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencija;

import domen.ApstraktniDomenskiObjekat;
import domen.EvidencijaCasova;
import java.util.List;
import so.ApstraktnaSO;
/**
 * 
 * @author Una
 */

public class PretraziEvidencijaCasovaSO extends ApstraktnaSO {

    private List<EvidencijaCasova> evidencije;

    public List<EvidencijaCasova> getEvidencije() {
        return evidencije;
    }

    @Override
    protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
         if (!(ado instanceof EvidencijaCasova)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaCasova!");
        }

        EvidencijaCasova ev = (EvidencijaCasova) ado;

        if (ev.getProfesor()== null && ev.getUcenik()== null && (ev.getSkolskaGodina() == null || ev.getSkolskaGodina().isEmpty())) {
            throw new Exception("Morate uneti bar jedan kriterijum pretrage!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        EvidencijaCasova ev = (EvidencijaCasova) ado;
        evidencije = repository.getAll(ev);
        
        if (evidencije.isEmpty()) {
            throw new Exception("Sistem ne moze da nadje evidencije casova po zadatim kriterijumima!");
        }
    }
}