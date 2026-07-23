package controller;

import domen.Cas;
import domen.EvidencijaCasova;
import domen.PlesniNivo;
import domen.Profesor;
import domen.Sertifikat;
import domen.Ucenik;
import java.util.List;
import so.login.LoginSO;
import so.ucenik.VratiListuUcenikSO;
import so.ucenik.UbaciUcenikSO;
import so.ucenik.PromeniUcenikSO;
import so.ucenik.ObrisiUcenikSO;
import so.ucenik.PretraziUcenikSO;
import so.ucenik.VratiUcenikSO;
import so.plesninivo.VratiListuPlesniNivoSO;
import so.evidencija.VratiListuEvidencijaCasovaSO;
import so.evidencija.UbaciEvidencijaCasovaSO;
import so.evidencija.PromeniEvidencijaCasovaSO;
import so.evidencija.PretraziEvidencijaCasovaSO;
import so.evidencija.VratiEvidencijaCasovaSO;
import so.profesor.VratiListuProfesorSO;
import so.cas.VratiListuCasSO;
import so.sertifikat.UbaciSertifikatSO;

/**
 * 
 * @author Una
 */

public class ControllerServer {

    private static ControllerServer instance;

    private ControllerServer() {
    }

    public static ControllerServer getInstance() {
        if (instance == null) {
            instance = new ControllerServer();
        }
        return instance;
    }

    // LOGIN
    public Profesor login(Profesor profesor) throws Exception {
        LoginSO so = new LoginSO();
        so.izvrsi(profesor);
        return so.getUlogovani();
    }

    // UCENIK
    public List<Ucenik> vratiListuUcenik() throws Exception {
        VratiListuUcenikSO so = new VratiListuUcenikSO();
        Ucenik u = new Ucenik();
        so.izvrsi(u);
        return so.getUcenici();
    }

    public void ubaciUcenik(Ucenik u) throws Exception {
        UbaciUcenikSO so = new UbaciUcenikSO();
        so.izvrsi(u);
    }

    public void promeniUcenik(Ucenik u) throws Exception {
        PromeniUcenikSO so = new PromeniUcenikSO();
        so.izvrsi(u);
    }

    public void obrisiUcenik(Ucenik u) throws Exception {
        ObrisiUcenikSO so = new ObrisiUcenikSO();
        so.izvrsi(u);
    }

    public List<Ucenik> pretraziUcenik(Ucenik u) throws Exception {
        PretraziUcenikSO so = new PretraziUcenikSO();
        so.izvrsi(u);
        return so.getUcenici();
    }

    public Ucenik vratiUcenik(Ucenik u) throws Exception {
        VratiUcenikSO so = new VratiUcenikSO();
        so.izvrsi(u);
        return so.getUcenik();
    }

    // PLESNI NIVO
    public List<PlesniNivo> vratiListuPlesniNivo() throws Exception {
        VratiListuPlesniNivoSO so = new VratiListuPlesniNivoSO();
        PlesniNivo pn = new PlesniNivo();
        so.izvrsi(pn);
        return so.getPlesniNivoi();
    }

    // EVIDENCIJA CASOVA
    public List<EvidencijaCasova> vratiListuEvidencijaCasova() throws Exception {
        VratiListuEvidencijaCasovaSO so = new VratiListuEvidencijaCasovaSO();
        EvidencijaCasova ec = new EvidencijaCasova();
        so.izvrsi(ec);
        return so.getEvidencije();
    }

    public void ubaciEvidencijaCasova(EvidencijaCasova ec) throws Exception {
        UbaciEvidencijaCasovaSO so = new UbaciEvidencijaCasovaSO();
        so.izvrsi(ec);
    }

    public void promeniEvidencijaCasova(EvidencijaCasova ec) throws Exception {
        PromeniEvidencijaCasovaSO so = new PromeniEvidencijaCasovaSO();
        so.izvrsi(ec);
    }

    public List<EvidencijaCasova> pretraziEvidencijaCasova(EvidencijaCasova ec) throws Exception {
        PretraziEvidencijaCasovaSO so = new PretraziEvidencijaCasovaSO();
        so.izvrsi(ec);
        return so.getEvidencije();
    }

    public EvidencijaCasova vratiEvidencijaCasova(EvidencijaCasova ec) throws Exception {
        VratiEvidencijaCasovaSO so = new VratiEvidencijaCasovaSO();
        so.izvrsi(ec);
        return so.getEvidencija();
    }

    // PROFESOR
    public List<Profesor> vratiListuProfesor() throws Exception {
        VratiListuProfesorSO so = new VratiListuProfesorSO();
        Profesor p = new Profesor();
        so.izvrsi(p);
        return so.getProfesori();
    }

    // CAS
    public List<Cas> vratiListuCas() throws Exception {
        VratiListuCasSO so = new VratiListuCasSO();
        Cas c = new Cas();
        so.izvrsi(c);
        return so.getCasovi();
    }

    // SERTIFIKAT
    public void ubaciSertifikat(Sertifikat s) throws Exception {
        UbaciSertifikatSO so = new UbaciSertifikatSO();
        so.izvrsi(s);
    }
}