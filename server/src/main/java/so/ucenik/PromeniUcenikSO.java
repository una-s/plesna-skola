/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import domen.Ucenik;
import so.ApstraktnaSO;

/**
 *
 * @author Una
 */
public class PromeniUcenikSO extends ApstraktnaSO {

    @Override
    protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null) {
            throw new Exception("Ucenik ne sme biti null!");
        }
        if (!(ado instanceof Ucenik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
        }
        Ucenik u = (Ucenik) ado;
        if (u.getIme() == null || u.getIme().isEmpty()) {
            throw new Exception("Ime ne sme biti prazno!");
        }
        if (u.getPrezime() == null || u.getPrezime().isEmpty()) {
            throw new Exception("Prezime ne sme biti prazno!");
        }
        if (u.getBrojTelefona() == null || u.getBrojTelefona().isEmpty()) {
            throw new Exception("Broj telefona ne sme biti prazan!");
        }
        if (u.getPlesniNivo()== null) {
            throw new Exception("Plesni nivo ne sme biti prazan!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        Ucenik u = (Ucenik) ado;
        repository.edit(u);
    }
}
