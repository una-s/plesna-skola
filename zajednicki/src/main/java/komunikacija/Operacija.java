/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Una
 */
public enum Operacija implements Serializable {
    LOGIN,
    
    VRATI_LISTU_UCENIK,
    VRATI_LISTU_PLESNI_NIVO,
    UBACI_UCENIK,
    PROMENI_UCENIK,
    OBRISI_UCENIK,
    PRETRAZI_UCENIK,
    VRATI_UCENIK,
    
    VRATI_LISTU_EVIDENCIJA_CASOVA,
    UBACI_EVIDENCIJA_CASOVA,
    PROMENI_EVIDENCIJA_CASOVA,
    PRETRAZI_EVIDENCIJA_CASOVA,
    VRATI_EVIDENCIJA_CASOVA,
    
    VRATI_LISTU_PROFESOR,
    VRATI_LISTU_CAS,
    
    UBACI_SERTIFIKAT
}

