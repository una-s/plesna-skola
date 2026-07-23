/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.StavkaEvidencijeCasova;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Una
 */
public class ModelTabeleStavkaEvidencijeCasova extends AbstractTableModel {

    private List<StavkaEvidencijeCasova> stavke;
    private String[] kolone = {"Redni broj", "Naziv casa", "Datum prisustva", "Ocena", "Napomena"};

    public ModelTabeleStavkaEvidencijeCasova(List<StavkaEvidencijeCasova> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        return stavke.size();
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
                return stavke.get(rowIndex).getRb();
            case 1:
                return stavke.get(rowIndex).getCas().getNaziv();
            case 2:
                return sdf.format(stavke.get(rowIndex).getDatumPrisustva());
            case 3:
                return stavke.get(rowIndex).getOcena();
            case 4:
                return stavke.get(rowIndex).getNapomena();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<StavkaEvidencijeCasova> getStavke() {
        return stavke;
    }
}