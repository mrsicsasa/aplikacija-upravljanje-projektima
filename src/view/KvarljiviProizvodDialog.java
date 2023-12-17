package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.proizvodi.KvarljiviProizvod;
import utils.Mapper;
import utils.Reader;
import utils.Writer;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class KvarljiviProizvodDialog extends JPanel {
    private static final String FILE_NAME = "kvarljiviProizvodi.txt";
    private static final long serialVersionUID = 1L;
    private JTextField nazivField, cenaField, zemljaPoreklaField, modelField, jedinicaMereField,
            rokTrajanjaField, temperaturaSkladistenjaField;
    private JTable kvarljiviProizvodTable;
    private ArrayList<KvarljiviProizvod> kvarljiviProizvodi = new ArrayList<>();
    private KvarljiviProizvod trenutniKvarljiviProizvod = new KvarljiviProizvod();

    public KvarljiviProizvodDialog() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        nazivField = new JTextField();
        cenaField = new JTextField();
        zemljaPoreklaField = new JTextField();
        modelField = new JTextField();
        jedinicaMereField = new JTextField();
        rokTrajanjaField = new JTextField();
        temperaturaSkladistenjaField = new JTextField();

        formPanel.add(new JLabel("Naziv:"));
        formPanel.add(nazivField);

        formPanel.add(new JLabel("Cena:"));
        formPanel.add(cenaField);

        formPanel.add(new JLabel("Zemlja porekla:"));
        formPanel.add(zemljaPoreklaField);

        formPanel.add(new JLabel("Model:"));
        formPanel.add(modelField);

        formPanel.add(new JLabel("Jedinica mere:"));
        formPanel.add(jedinicaMereField);

        formPanel.add(new JLabel("Rok trajanja:"));
        formPanel.add(rokTrajanjaField);

        formPanel.add(new JLabel("Temperatura skladištenja:"));
        formPanel.add(temperaturaSkladistenjaField);

        JButton sacuvajButton = new JButton("Sacuvaj");
        JButton editButton = new JButton("Izmeni");
        JButton deleteButton = new JButton("Obrisi");
        sacuvajButton.addActionListener(e -> sacuvajKvarljiviProizvod());
        editButton.addActionListener(e -> izmeniKvarljiviProizvod());
        deleteButton.addActionListener(e -> izbrisiKvarljiviProizvod());
        formPanel.add(sacuvajButton);
        formPanel.add(editButton);
        formPanel.add(deleteButton);

        JPanel tablePanel = new JPanel(new BorderLayout());
        kvarljiviProizvodTable = new JTable();
        kvarljiviProizvodTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = kvarljiviProizvodTable.getSelectedRow();
                if (selectedRow != -1) {
                    nazivField.setText(kvarljiviProizvodTable.getValueAt(selectedRow, 0).toString());
                    cenaField.setText(kvarljiviProizvodTable.getValueAt(selectedRow, 1).toString());
                    zemljaPoreklaField.setText(kvarljiviProizvodTable.getValueAt(selectedRow, 2).toString());
                    modelField.setText(kvarljiviProizvodTable.getValueAt(selectedRow, 3).toString());
                    jedinicaMereField.setText(kvarljiviProizvodTable.getValueAt(selectedRow, 4).toString());
                    rokTrajanjaField.setText(kvarljiviProizvodTable.getValueAt(selectedRow, 5).toString());
                    temperaturaSkladistenjaField.setText(kvarljiviProizvodTable.getValueAt(selectedRow, 6).toString());

                    trenutniKvarljiviProizvod.setNaziv(kvarljiviProizvodTable.getValueAt(selectedRow, 0).toString());
                    trenutniKvarljiviProizvod.setCena(Integer.parseInt(kvarljiviProizvodTable.getValueAt(selectedRow, 1).toString()));
                    trenutniKvarljiviProizvod.setZemljaPorekla(kvarljiviProizvodTable.getValueAt(selectedRow, 2).toString());
                    trenutniKvarljiviProizvod.setModel(kvarljiviProizvodTable.getValueAt(selectedRow, 3).toString());
                    trenutniKvarljiviProizvod.setJedinicaMere(kvarljiviProizvodTable.getValueAt(selectedRow, 4).toString());
                    trenutniKvarljiviProizvod.setRokTrajanja(LocalDate.parse(kvarljiviProizvodTable.getValueAt(selectedRow, 5).toString()));
                    trenutniKvarljiviProizvod.setTemperaturaSkladistenja(Integer.parseInt(kvarljiviProizvodTable.getValueAt(selectedRow, 6).toString()));
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(kvarljiviProizvodTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(formPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    private void izbrisiKvarljiviProizvod() {
        String naziv = nazivField.getText();
        Writer writer = new Writer();
        Reader reader = new Reader();
        List<ArrayList<String>> data = reader.ucitaj(FILE_NAME);

        Iterator<ArrayList<String>> iterator = data.iterator();
        while (iterator.hasNext()) {
            ArrayList<String> rez = iterator.next();
            if (rowMatchesCurrentKvarljiviProizvod(rez)) {
                iterator.remove();
                writer.upis(FILE_NAME, data);
            }
        }
        kvarljiviProizvodi.clear();
        osveziTabelu();
    }
    public void sacuvajKvarljiviProizvod() {
        String naziv = nazivField.getText();
        int cena = Integer.parseInt(cenaField.getText());
        String zemljaPorekla = zemljaPoreklaField.getText();
        String model = modelField.getText();
        String jedinicaMere = jedinicaMereField.getText();
        LocalDate rokTrajanja = LocalDate.parse(rokTrajanjaField.getText());
        int temperaturaSkladistenja = Integer.parseInt(temperaturaSkladistenjaField.getText());

        KvarljiviProizvod noviKvarljiviProizvod = new KvarljiviProizvod(naziv, cena, zemljaPorekla, model, jedinicaMere, rokTrajanja, temperaturaSkladistenja);

        Writer w = new Writer();
        Reader r = new Reader();
        Mapper m = new Mapper();
        ArrayList<String> noviKvarljiviProizvodPodaci = new ArrayList<>();
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getNaziv());
        noviKvarljiviProizvodPodaci.add(String.valueOf(noviKvarljiviProizvod.getCena()));
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getZemljaPorekla());
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getModel());
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getJedinicaMere());
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getRokTrajanja().toString());
        noviKvarljiviProizvodPodaci.add(String.valueOf(noviKvarljiviProizvod.getTemperaturaSkladistenja()));

        List<ArrayList<String>> rezultat = r.ucitaj(FILE_NAME);
        for (int i = 0; i < rezultat.size(); i++) {
            ArrayList<String> rez = rezultat.get(i);
            if (rowMatchesCurrentKvarljiviProizvod(rez)) {
                rezultat.set(i, noviKvarljiviProizvodPodaci);
            }
        }
        w.upis(FILE_NAME, rezultat);

        kvarljiviProizvodi.clear();
        osveziTabelu();

        JOptionPane.showMessageDialog(this, "Kvarljivi proizvod sacuvan!");
        clearFields();
    }
   
    private boolean rowMatchesCurrentKvarljiviProizvod(ArrayList<String> row) {
        return row.get(0).equals(trenutniKvarljiviProizvod.getNaziv()) &&
               row.get(1).equals(String.valueOf(trenutniKvarljiviProizvod.getCena())) &&
               row.get(2).equals(trenutniKvarljiviProizvod.getZemljaPorekla()) &&
               row.get(3).equals(trenutniKvarljiviProizvod.getModel()) &&
               row.get(4).equals(trenutniKvarljiviProizvod.getJedinicaMere()) &&
               row.get(5).equals(trenutniKvarljiviProizvod.getRokTrajanja().toString()) &&
               row.get(6).equals(String.valueOf(trenutniKvarljiviProizvod.getTemperaturaSkladistenja()));
    }
    public void osveziTabelu() {
        kvarljiviProizvodi = getKvarljiviProizvodi();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Naziv");
        model.addColumn("Cena");
        model.addColumn("Zemlja porekla");
        model.addColumn("Model");
        model.addColumn("Jedinica mere");
        model.addColumn("Rok trajanja");
        model.addColumn("Temperatura skladištenja");

        for (KvarljiviProizvod proizvod : kvarljiviProizvodi) {
            Vector<Object> row = new Vector<>();
            row.add(proizvod.getNaziv());
            row.add(proizvod.getCena());
            row.add(proizvod.getZemljaPorekla());
            row.add(proizvod.getModel());
            row.add(proizvod.getJedinicaMere());
            row.add(proizvod.getRokTrajanja());
            row.add(proizvod.getTemperaturaSkladistenja());

            model.addRow(row);
        }

        kvarljiviProizvodTable.setModel(model);
    }
    private ArrayList<KvarljiviProizvod> getKvarljiviProizvodi() {
        Reader r = new Reader();
        List<ArrayList<String>> rezultat = r.ucitaj("kvarljiviProizvodi.txt");  // Update the file name accordingly
        Mapper m = new Mapper();
        ArrayList<KvarljiviProizvod> instance = m.konvertujUKvarljiviProizvod(rezultat); // Update the method accordingly
        ArrayList<KvarljiviProizvod> sviKvarljiviProizvodi = new ArrayList<>();
        sviKvarljiviProizvodi.addAll(instance);
        sviKvarljiviProizvodi.addAll(kvarljiviProizvodi); // Assuming kvarljiviProizvodi is your existing list
        return sviKvarljiviProizvodi;
    }
    private void clearFields() {
        nazivField.setText("");
        cenaField.setText("");
        zemljaPoreklaField.setText("");
        modelField.setText("");
        jedinicaMereField.setText("");
        rokTrajanjaField.setText("");
        temperaturaSkladistenjaField.setText("");
    }
    private void izmeniKvarljiviProizvod() {
        String naziv = nazivField.getText();
        int cena = Integer.parseInt(cenaField.getText());
        String zemljaPorekla = zemljaPoreklaField.getText();
        String model = modelField.getText();
        String jedinicaMere = jedinicaMereField.getText();
        LocalDate rokTrajanja = LocalDate.parse(rokTrajanjaField.getText());
        int temperaturaSkladistenja = Integer.parseInt(temperaturaSkladistenjaField.getText());

        KvarljiviProizvod noviKvarljiviProizvod = new KvarljiviProizvod(naziv, cena, zemljaPorekla, model, jedinicaMere, rokTrajanja, temperaturaSkladistenja);

        Writer w = new Writer();
        Reader r = new Reader();
        Mapper m = new Mapper();
        ArrayList<String> noviKvarljiviProizvodPodaci = new ArrayList<>();
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getNaziv());
        noviKvarljiviProizvodPodaci.add(String.valueOf(noviKvarljiviProizvod.getCena()));
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getZemljaPorekla());
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getModel());
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getJedinicaMere());
        noviKvarljiviProizvodPodaci.add(noviKvarljiviProizvod.getRokTrajanja().toString());
        noviKvarljiviProizvodPodaci.add(String.valueOf(noviKvarljiviProizvod.getTemperaturaSkladistenja()));

        List<ArrayList<String>> rezultat = r.ucitaj("kvarljiviProizvodi.txt"); // Update the file name accordingly
        for (int i = 0; i < rezultat.size(); i++) {
            ArrayList<String> rez = rezultat.get(i);
            if (rowMatchesCurrentKvarljiviProizvod(rez)) {
                rezultat.set(i, noviKvarljiviProizvodPodaci);
            }
        }
        w.upis("kvarljiviProizvodi.txt", rezultat);

        kvarljiviProizvodi.clear();
        osveziTabelu();

        JOptionPane.showMessageDialog(this, "Kvarljivi proizvod izmenjen!");
        clearFields();
    }}