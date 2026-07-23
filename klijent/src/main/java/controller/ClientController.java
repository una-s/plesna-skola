/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Cas;
import domen.EvidencijaCasova;
import domen.PlesniNivo;
import domen.Profesor;
import domen.Sertifikat;
import domen.Ucenik;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import komunikacija.Komunikacija;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Zahtev;

/**
 *
 * @author Una
 */
//koordinira radom na klijentskoj strani, ne zna nista o bazi
//zna kako da pripremi zahtev koji se salje i reaguje na odgovor koji se dobije od servera
//koristi se singleton da bi se osigurali da u programu postoji samo jedna instanca ove klase
//kako ne bi doslo do nepredvidivog ponasanja programa

public class ClientController {

    private static ClientController instance;
    private Profesor ulogovani;

    private ClientController() {

    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Profesor login(String username, String pass) throws Exception {
        Profesor p = new Profesor();
        p.setKorisnickoIme(username);
        p.setSifra(pass);

        Zahtev zahtev = new Zahtev(Operacija.LOGIN, p);
        Odgovor odgovor = Komunikacija.getInstance().login(zahtev);

        if (odgovor.getEx() == null) {
            return (Profesor) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }

    public void setUlogovani(Profesor ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Profesor getUlogovani() {
        return ulogovani;
    }
    
    public List<Ucenik> vratiListuSviUcenik() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_UCENIK, null);
        Odgovor odgovor = Komunikacija.getInstance().vratiUcenike(zahtev);

        if (odgovor.getEx() == null) {
            return (List<Ucenik>) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }
    public List<PlesniNivo> vratiListuPlesniNivo() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_PLESNI_NIVO, null);
        Odgovor odgovor = Komunikacija.getInstance().vratiPlesneNivoe(zahtev);

        if (odgovor.getEx() == null) {
            return (List<PlesniNivo>) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }
//yes

    public void ubaciUcenik(Ucenik u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_UCENIK, u);
        Odgovor odgovor = Komunikacija.getInstance().kreirajUcenika(zahtev);

        if (odgovor.getEx() == null) {

        } else {
            throw odgovor.getEx();
        }
    }
//yes

    public void promeniUcenik(Ucenik u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_UCENIK, u);
        Odgovor odgovor = Komunikacija.getInstance().promeniUcenika(zahtev);

        if (odgovor.getEx() == null) {

        } else {
            throw odgovor.getEx();
        }
    }

    public void obrisiUcenik(Ucenik u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_UCENIK, u);
        Odgovor odgovor = Komunikacija.getInstance().obrisiUcenika(zahtev);

        if (odgovor.getEx() == null) {

        } else {
            throw odgovor.getEx();
        }
    }

    public List<Ucenik> pretraziUcenik(Ucenik u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_UCENIK, u);
        Odgovor odgovor = Komunikacija.getInstance().pretraziUcenike(zahtev);

        if (odgovor.getEx() == null) {
            return (List<Ucenik>) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }
//yes

    public List<EvidencijaCasova> vratiListuEvidencijaCasova() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_EVIDENCIJA_CASOVA, null);
        Odgovor odgovor = Komunikacija.getInstance().vratiEvidencijeCasova(zahtev);

        if (odgovor.getEx() == null) {
            return (List<EvidencijaCasova>) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }
//yes

    public List<Profesor> vratiListuSviProfesor() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_PROFESOR, null);
        Odgovor odgovor = Komunikacija.getInstance().vratiProfesore(zahtev);

        if (odgovor.getEx() == null) {
            return (List<Profesor>) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }
//yes

    public List<Cas> vratiListuSviCas() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_CAS, null);
        Odgovor odgovor = Komunikacija.getInstance().vratiCasove(zahtev);

        if (odgovor.getEx() == null) {
            return (List<Cas>) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }
//yes

    public void ubaciEvidencijaCasova(EvidencijaCasova e) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_EVIDENCIJA_CASOVA, e);
        Odgovor odgovor = Komunikacija.getInstance().ubaciEvidencijuCasova(zahtev);

        if (odgovor.getEx() == null) {

        } else {
            throw odgovor.getEx();
        }
    }
//yes

    public void promeniEvidencijaCasova(EvidencijaCasova e) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_EVIDENCIJA_CASOVA, e);
        Odgovor odgovor = Komunikacija.getInstance().promeniEvidencijuCasova(zahtev);

        if (odgovor.getEx() == null) {

        } else {
            throw odgovor.getEx();
        }
    }
//yes

    public List<EvidencijaCasova> pretraziEvidencijaCasova(EvidencijaCasova e) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_EVIDENCIJA_CASOVA, e);
        Odgovor odgovor = Komunikacija.getInstance().pretraziEvidencijeCasova(zahtev);

        if (odgovor.getEx() == null) {
            return (List<EvidencijaCasova>) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }
//yes

    public void ubaciSertifikat(Sertifikat s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_SERTIFIKAT, s);
        Odgovor odgovor = Komunikacija.getInstance().ubaciSertifikat(zahtev);

        if (odgovor.getEx() == null) {

        } else {
            throw odgovor.getEx();
        }
    }

    public EvidencijaCasova vratiEvidencijaCasova(EvidencijaCasova e) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_EVIDENCIJA_CASOVA, e);
        Odgovor odgovor = Komunikacija.getInstance().vratiEvidencijuCasova(zahtev);

        if (odgovor.getEx() == null) {
            return (EvidencijaCasova) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }

    public Ucenik vratiUcenik(Ucenik u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_UCENIK, u);
        Odgovor odgovor = Komunikacija.getInstance().vratiUcenika(zahtev);

        if (odgovor.getEx() == null) {
            return (Ucenik) odgovor.getOdgovor();
        } else {
            throw odgovor.getEx();
        }
    }
}
