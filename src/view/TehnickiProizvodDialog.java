package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.proizvodi.Dimenzije;
import models.proizvodi.TehnickiProizvod;
import utils.Mapper;
import utils.Reader;
import utils.Writer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class TehnickiProizvodDialog extends JPanel {
    private static final String FILE_NAME = "tehnickiProizvodi.txt";
    private JTextField nazivField, cenaField, zemljaPoreklaField, modelField, jedinicaMereField, dimenzijeDuzinaField,
            nominalnaSnagaField, radniNaponField,dimenzijeSirinaField;
    private JTable tehnickiProizvodTable;
    private ArrayList<TehnickiProizvod> tehnickiProizvodi = new ArrayList<>();
    private TehnickiProizvod trenutniTehnickiProizvod = new TehnickiProizvod();

    public TehnickiProizvodDialog() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        nazivField = new JTextField();
        cenaField = new JTextField();
        zemljaPoreklaField = new JTextField();
        modelField = new JTextField();
        jedinicaMereField = new JTextField();
        dimenzijeDuzinaField = new JTextField();
        dimenzijeSirinaField = new JTextField();
        nominalnaSnagaField = new JTextField();
        radniNaponField = new JTextField();

        formPanel.add(new JLabel("Naziv:"));
        formPanel.add(nazivField);

        formPanel.add(new JLabel("Cena:"));
        formPanel.add(cenaField);

        formPanel.add(new JLabel("Zemlja Porekla:"));
        formPanel.add(zemljaPoreklaField);

        formPanel.add(new JLabel("Model:"));
        formPanel.add(modelField);

        formPanel.add(new JLabel("Jedinica Mere:"));
        formPanel.add(jedinicaMereField);

        formPanel.add(new JLabel("Duzina:"));
        formPanel.add(dimenzijeDuzinaField);
        formPanel.add(new JLabel("Sirina:"));
        formPanel.add(dimenzijeSirinaField);

        formPanel.add(new JLabel("Nominalna Snaga:"));
        formPanel.add(nominalnaSnagaField);

        formPanel.add(new JLabel("Radni Napon:"));
        formPanel.add(radniNaponField);

        JButton sacuvajButton = new JButton("Sacuvaj");
        JButton editButton = new JButton("Izmeni");
        JButton deleteButton = new JButton("Obrisi");
        sacuvajButton.addActionListener(e -> sacuvajTehnickiProizvod());
        editButton.addActionListener(e -> izmeniTehnickiProizvod());
        deleteButton.addActionListener(e -> izbrisiTehnickiProizvod());
        formPanel.add(sacuvajButton);
        formPanel.add(editButton);
        formPanel.add(deleteButton);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tehnickiProizvodTable = new JTable();
        tehnickiProizvodTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tehnickiProizvodTable.getSelectedRow();
                if (selectedRow != -1) {
                    nazivField.setText(tehnickiProizvodTable.getValueAt(selectedRow, 0).toString());
                    cenaField.setText(tehnickiProizvodTable.getValueAt(selectedRow, 1).toString());
                    zemljaPoreklaField.setText(tehnickiProizvodTable.getValueAt(selectedRow, 2).toString());
                    modelField.setText(tehnickiProizvodTable.getValueAt(selectedRow, 3).toString());
                    jedinicaMereField.setText(tehnickiProizvodTable.getValueAt(selectedRow, 4).toString());
                    dimenzijeDuzinaField.setText(tehnickiProizvodTable.getValueAt(selectedRow, 5).toString());
                    dimenzijeSirinaField.setText(tehnickiProizvodTable.getValueAt(selectedRow, 6).toString());
                    nominalnaSnagaField.setText(tehnickiProizvodTable.getValueAt(selectedRow, 7).toString());
                    radniNaponField.setText(tehnickiProizvodTable.getValueAt(selectedRow, 8).toString());
                    trenutniTehnickiProizvod.setNaziv(tehnickiProizvodTable.getValueAt(selectedRow, 0).toString());
                    trenutniTehnickiProizvod.setCena(Integer.parseInt(tehnickiProizvodTable.getValueAt(selectedRow, 1).toString()));
                    trenutniTehnickiProizvod.setZemljaPorekla(tehnickiProizvodTable.getValueAt(selectedRow, 2).toString());
                    trenutniTehnickiProizvod.setModel(tehnickiProizvodTable.getValueAt(selectedRow, 3).toString());
                    trenutniTehnickiProizvod.setJedinicaMere(tehnickiProizvodTable.getValueAt(selectedRow, 4).toString());
                    trenutniTehnickiProizvod.setDimenzije(new Dimenzije(Double.parseDouble(tehnickiProizvodTable.getValueAt(selectedRow, 5).toString()),Double.parseDouble(tehnickiProizvodTable.getValueAt(selectedRow, 6).toString())));
                    trenutniTehnickiProizvod.setNominalnaSnaga(Double.parseDouble(tehnickiProizvodTable.getValueAt(selectedRow, 7).toString()));
                    trenutniTehnickiProizvod.setRadniNapon(Double.parseDouble(tehnickiProizvodTable.getValueAt(selectedRow, 8).toString()));
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(tehnickiProizvodTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(formPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    private void izbrisiTehnickiProizvod() {
        Writer writer = new Writer();
        Reader reader = new Reader();
        List<ArrayList<String>> data = reader.ucitaj(FILE_NAME);

        Iterator<ArrayList<String>> iterator = data.iterator();
        while (iterator.hasNext()) {
            ArrayList<String> rez = iterator.next();
            if (rowMatchesCurrentTehnickiProizvod(rez)) {
                iterator.remove();
                writer.upis(FILE_NAME, data);
            }
        }
        tehnickiProizvodi.clear();
        osveziTabelu();
    }

    private void izmeniTehnickiProizvod() {
        String naziv = nazivField.getText();
        int cena = Integer.parseInt(cenaField.getText());
        String zemljaPorekla = zemljaPoreklaField.getText();
        String model = modelField.getText();
        String jedinicaMere = jedinicaMereField.getText();
        double dimenzijeDuzina =Double.parseDouble( dimenzijeDuzinaField.getText());
        double dimenzijeSirina =Double.parseDouble( dimenzijeSirinaField.getText());
        double nominalnaSnaga = Double.parseDouble(nominalnaSnagaField.getText());
        double radniNapon = Double.parseDouble(radniNaponField.getText());

        TehnickiProizvod noviTehnickiProizvod = new TehnickiProizvod(naziv, cena, zemljaPorekla, model, jedinicaMere, new Dimenzije(dimenzijeDuzina,dimenzijeSirina), nominalnaSnaga, radniNapon);

        Writer w = new Writer();
        Reader r = new Reader();
        ArrayList<String> noviTehnickiProizvodPodaci = new ArrayList<>();
        noviTehnickiProizvodPodaci.add(noviTehnickiProizvod.getNaziv());
        noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getCena()));
        noviTehnickiProizvodPodaci.add(noviTehnickiProizvod.getZemljaPorekla());
        noviTehnickiProizvodPodaci.add(noviTehnickiProizvod.getModel());
        noviTehnickiProizvodPodaci.add(noviTehnickiProizvod.getJedinicaMere());
        noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getDimenzije().getDuzina()));
        noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getDimenzije().getSirina()));
        noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getNominalnaSnaga()));
        noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getRadniNapon()));
        List<ArrayList<String>> rezultat = r.ucitaj(FILE_NAME);
        for (int i = 0; i < rezultat.size(); i++) {
            ArrayList<String> rez = rezultat.get(i);
            if (rowMatchesCurrentTehnickiProizvod(rez)) {
                rezultat.set(i, noviTehnickiProizvodPodaci);
            }
        }
        w.upis(FILE_NAME, rezultat);

        tehnickiProizvodi.clear();
        osveziTabelu();

        JOptionPane.showMessageDialog(this, "Tehnicki Proizvod izmenjen!");
        clearFields();
    }

    public void sacuvajTehnickiProizvod() {
        try {
            String naziv = nazivField.getText();
            int cena = Integer.parseInt(cenaField.getText());
            String zemljaPorekla = zemljaPoreklaField.getText();
            String model = modelField.getText();
            String jedinicaMere = jedinicaMereField.getText();
            double dimenzijeDuzina = Double.parseDouble(dimenzijeDuzinaField.getText());
            double dimenzijeSirina = Double.parseDouble(dimenzijeSirinaField.getText());
            double nominalnaSnaga = Double.parseDouble(nominalnaSnagaField.getText());
            double radniNapon = Double.parseDouble(radniNaponField.getText());

           
            if (naziv.isEmpty() || zemljaPorekla.isEmpty() || model.isEmpty() || jedinicaMere.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Polja 'Naziv', 'Zemlja Porekla', 'Model' i 'Jedinica Mere' ne smeju biti prazna.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TehnickiProizvod noviTehnickiProizvod = new TehnickiProizvod(naziv, cena, zemljaPorekla, model, jedinicaMere, new Dimenzije(dimenzijeDuzina, dimenzijeSirina), nominalnaSnaga, radniNapon);

            Writer w = new Writer();
            Reader r = new Reader();
            ArrayList<String> noviTehnickiProizvodPodaci = new ArrayList<>();
            noviTehnickiProizvodPodaci.add(noviTehnickiProizvod.getNaziv());
            noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getCena()));
            noviTehnickiProizvodPodaci.add(noviTehnickiProizvod.getZemljaPorekla());
            noviTehnickiProizvodPodaci.add(noviTehnickiProizvod.getModel());
            noviTehnickiProizvodPodaci.add(noviTehnickiProizvod.getJedinicaMere());
            noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getDimenzije().getDuzina()));
            noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getDimenzije().getSirina()));
            noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getNominalnaSnaga()));
            noviTehnickiProizvodPodaci.add(String.valueOf(noviTehnickiProizvod.getRadniNapon()));

            List<ArrayList<String>> rezultat = r.ucitaj(FILE_NAME);
            rezultat.add(noviTehnickiProizvodPodaci);
            w.upis(FILE_NAME, rezultat);
            tehnickiProizvodi.clear();
            osveziTabelu();

            JOptionPane.showMessageDialog(this, "Tehnicki Proizvod sacuvan!");
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Polja 'Cena', 'Dimenzije Duzina', 'Dimenzije Sirina', 'Nominalna Snaga' i 'Radni Napon' moraju sadržavati numeričku vrednost.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void osveziTabelu() {
        tehnickiProizvodi = getTehnickiProizvodi();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Naziv");
        model.addColumn("Cena");
        model.addColumn("Zemlja Porekla");
        model.addColumn("Model");
        model.addColumn("Jedinica Mere");
        model.addColumn("Duzina");
        model.addColumn("Sirina");
        model.addColumn("Nominalna Snaga");
        model.addColumn("Radni Napon");

        for (TehnickiProizvod tehnickiProizvod : tehnickiProizvodi) {
            Vector<Object> row = new Vector<>();
            row.add(tehnickiProizvod.getNaziv());
            row.add(tehnickiProizvod.getCena());
            row.add(tehnickiProizvod.getZemljaPorekla());
            row.add(tehnickiProizvod.getModel());
            row.add(tehnickiProizvod.getJedinicaMere());
            row.add(tehnickiProizvod.getDimenzije().getDuzina());
            row.add(tehnickiProizvod.getDimenzije().getSirina());
            row.add(tehnickiProizvod.getNominalnaSnaga());
            row.add(tehnickiProizvod.getRadniNapon());

            model.addRow(row);
        }

        tehnickiProizvodTable.setModel(model);
    }

    private ArrayList<TehnickiProizvod> getTehnickiProizvodi() {
        Reader r = new Reader();
        List<ArrayList<String>> rezultat = r.ucitaj(FILE_NAME);
        Mapper m = new Mapper();
        ArrayList<TehnickiProizvod> instance = m.konvertujUTehnickiProizvod(rezultat);
        return instance;
        }

        private void clearFields() {
            nazivField.setText("");
            cenaField.setText("");
            zemljaPoreklaField.setText("");
            modelField.setText("");
            jedinicaMereField.setText("");
            dimenzijeDuzinaField.setText("");
            dimenzijeSirinaField.setText("");
            nominalnaSnagaField.setText("");
            radniNaponField.setText("");
        }

        private boolean rowMatchesCurrentTehnickiProizvod(ArrayList<String> row) {
            return row.get(0).trim().equals(trenutniTehnickiProizvod.getNaziv().trim()) &&
                    row.get(1).trim().equals(String.valueOf(trenutniTehnickiProizvod.getCena()).trim()) &&
                    row.get(2).trim().equals(trenutniTehnickiProizvod.getZemljaPorekla().trim()) &&
                    row.get(3).trim().equals(trenutniTehnickiProizvod.getModel().trim()) &&
                    row.get(4).trim().equals(trenutniTehnickiProizvod.getJedinicaMere().trim()) &&
                    row.get(5).trim().equals(String.valueOf(trenutniTehnickiProizvod.getDimenzije().getDuzina()).trim()) &&
                    row.get(6).trim().equals(String.valueOf(trenutniTehnickiProizvod.getDimenzije().getSirina()).trim()) &&
                    row.get(7).trim().equals(String.valueOf(trenutniTehnickiProizvod.getNominalnaSnaga()).trim()) &&
                    row.get(8).trim().equals(String.valueOf(trenutniTehnickiProizvod.getRadniNapon()).trim());
        }


        }

