package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.osobe.Odmor;
import models.osobe.Odmori;
import models.osobe.Promoter;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

public class PromoterDialog extends JPanel {
    private static final String FILE_NAME = "promoter.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private JTextField imeField, prezimeField, plataField, pocetakAngazovanjaField, krajAngazovanjaField;
    private JTable promoterTable;
    private JComboBox<Odmor> odmoriDropdown;
    private ArrayList<Promoter> promoteri = new ArrayList<>();
    private Promoter trenutniPromoter = new Promoter();

    public PromoterDialog() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(10,2));
        imeField = new JTextField();
        prezimeField = new JTextField();
        plataField = new JTextField();
        pocetakAngazovanjaField = new JTextField();
        krajAngazovanjaField = new JTextField();

        formPanel.add(new JLabel("Ime:"));
        formPanel.add(imeField);

        formPanel.add(new JLabel("Prezime:"));
        formPanel.add(prezimeField);

        formPanel.add(new JLabel("Plata:"));
        formPanel.add(plataField);

        formPanel.add(new JLabel("Pocetak angazovanja:"));
        formPanel.add(pocetakAngazovanjaField);

        formPanel.add(new JLabel("Kraj angazovanja:"));
        formPanel.add(krajAngazovanjaField);

        formPanel.add(new JLabel("Odmor:"));
        odmoriDropdown = new JComboBox<>();
        formPanel.add(odmoriDropdown);

        JButton sacuvajButton = new JButton("Sacuvaj");
        JButton editButton = new JButton("Izmeni");
        JButton deleteButton = new JButton("Obrisi");
        JButton dodajOdmorButton = new JButton("Dodaj Odmor");
        JButton obrisiOdmorButton = new JButton("Obrisi Odmor");

        sacuvajButton.addActionListener(e -> sacuvajPromotera());
        editButton.addActionListener(e -> izmeniPromotera());
        deleteButton.addActionListener(e -> izbrisiPromotera());
        dodajOdmorButton.addActionListener(e -> dodajOdmor());
        obrisiOdmorButton.addActionListener(e -> obrisiOdmor());

        formPanel.add(sacuvajButton);
        formPanel.add(editButton);
        formPanel.add(deleteButton);
        formPanel.add(dodajOdmorButton);
        formPanel.add(obrisiOdmorButton);

        JPanel tablePanel = new JPanel(new BorderLayout());
        promoterTable = new JTable();
        promoterTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = promoterTable.getSelectedRow();
                if (selectedRow != -1) {
                    imeField.setText(promoterTable.getValueAt(selectedRow, 0).toString());
                    prezimeField.setText(promoterTable.getValueAt(selectedRow, 1).toString());
                    plataField.setText(promoterTable.getValueAt(selectedRow, 2).toString());
                    pocetakAngazovanjaField.setText(promoterTable.getValueAt(selectedRow, 3).toString());
                    krajAngazovanjaField.setText(promoterTable.getValueAt(selectedRow, 4).toString());

                    trenutniPromoter.setIme(promoterTable.getValueAt(selectedRow, 0).toString());
                    trenutniPromoter.setPrezime(promoterTable.getValueAt(selectedRow, 1).toString());
                    trenutniPromoter.setPlata(Integer.parseInt(promoterTable.getValueAt(selectedRow, 2).toString()));
                    trenutniPromoter.setPocetakAngazovanja(parsirajDatum(promoterTable.getValueAt(selectedRow, 3).toString()));
                    trenutniPromoter.setZavrsetakAngazovanja(parsirajDatum(promoterTable.getValueAt(selectedRow, 4).toString()));

                    osveziDropdownOdmora();
                    System.out.println(trenutniPromoter.getIme());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(promoterTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(formPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    private void osveziDropdownOdmora() {
        Odmori odmoriModel = new Odmori(); // Zamislite da imate instancu Odmori koja sadrži odmore
        DefaultComboBoxModel<Odmor> odmoriComboBoxModel = new DefaultComboBoxModel<>(odmoriModel.getOdmori().toArray(new Odmor[0]));
        odmoriDropdown.setModel(odmoriComboBoxModel);
    }

    private void dodajOdmor() {
        Odmor selectedOdmor = (Odmor) odmoriDropdown.getSelectedItem();
        if (selectedOdmor != null) {
            trenutniPromoter.getOdmori().dodajOdmor(selectedOdmor);
            osveziTabelu();
        }
    }

    private void obrisiOdmor() {
        Odmor selectedOdmor = (Odmor) odmoriDropdown.getSelectedItem();
        if (selectedOdmor != null) {
            trenutniPromoter.getOdmori().obrisiOdmor(selectedOdmor);
            osveziTabelu();
        }
    }

    private void izbrisiPromotera() {
        // Implementirajte funkcionalnost za brisanje promotera
        // ...

        promoteri.clear();
        osveziTabelu();
    }

    private void izmeniPromotera() {
        // Implementirajte funkcionalnost za izmenu promotera
        // ...

        promoteri.clear();
        osveziTabelu();

        JOptionPane.showMessageDialog(this, "Promoter izmenjen!");
        clearFields();
    }

    private void sacuvajPromotera() {
        // Implementirajte funkcionalnost za cuvanje promotera
        // ...

        promoteri.clear();
        osveziTabelu();

        JOptionPane.showMessageDialog(this, "Promoter sacuvan!");
        clearFields();
    }

    private void osveziTabelu() {
        promoteri = getPromoteri();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Ime");
        model.addColumn("Prezime");
        model.addColumn("Plata");
        model.addColumn("Pocetak angozavanje");
        model.addColumn("Kraj angazovanja");

        for (Promoter promoter : promoteri) {
            Vector<Object> row = new Vector<>();
            row.add(promoter.getIme());
            row.add(promoter.getPrezime());
            row.add(promoter.getPlata());
            row.add(promoter.getPocetakAngazovanja());
            row.add(promoter.getZavrsetakAngazovanja());

            model.addRow(row);
        }

        promoterTable.setModel(model);
    }

    private ArrayList<Promoter> getPromoteri() {
        // Implementirajte funkcionalnost za ucitavanje promotera iz fajla ili baze podataka
        // ...

        return promoteri;
    }

    private void clearFields() {
        imeField.setText("");
        prezimeField.setText("");
        plataField.setText("");
        pocetakAngazovanjaField.setText("");
        krajAngazovanjaField.setText("");
    }

    private LocalDate parsirajDatum(String datum) {
        return LocalDate.parse(datum, FORMATTER);
    }
}
