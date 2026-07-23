/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.EvidencijaCasova;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Una
 */
public class ModelTabeleEvidencijaCasova extends AbstractTableModel {

    private List<EvidencijaCasova> evidencije;
    private String[] kolone = {"Skolska godina", "Datum pocetka", "Datum zavrsetka",
        "Trajanje", "Prosecna ocena", "Broj prisustva", "Profesor", "Ucenik"};

    public ModelTabeleEvidencijaCasova(List<EvidencijaCasova> evidencije) {
        this.evidencije = evidencije;
    }

    @Override
    public int getRowCount() {
        return evidencije.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return evidencije.get(rowIndex).getSkolskaGodina();
            case 1:
                return sdf.format(evidencije.get(rowIndex).getDatumPocetka());
            case 2:
                return sdf.format(evidencije.get(rowIndex).getDatumZavrsetka());
            case 3:
                return evidencije.get(rowIndex).getTrajanjeEvidencije();
            case 4:
                return evidencije.get(rowIndex).getProsecnaOcena();
            case 5:
                return evidencije.get(rowIndex).getBrojPrisustva();
            case 6:
                return evidencije.get(rowIndex).getProfesor().getIme() + " "
                        + evidencije.get(rowIndex).getProfesor().getPrezime();
            case 7:
                return evidencije.get(rowIndex).getUcenik().getIme() + " "
                        + evidencije.get(rowIndex).getUcenik().getPrezime();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<EvidencijaCasova> getEvidencije() {
        return evidencije;
    }
}