package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.osobe.Menadzer;
import models.osobe.Uloga;
import utils.Mapper;
import utils.Reader;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MenadzerForm extends JPanel {
	private JTextField imeField, prezimeField, plataField, ulogaField;
	private JTable menadzerTable;
	private ArrayList<Menadzer> menadzeri = new ArrayList<Menadzer>();

	public MenadzerForm() {
		setLayout(new BorderLayout());

		// Panel za formu
		JPanel formPanel = new JPanel(new GridLayout(5, 2));
		imeField = new JTextField();
		prezimeField = new JTextField();
		plataField = new JTextField();
		ulogaField = new JTextField();

		formPanel.add(new JLabel("Ime:"));
		formPanel.add(imeField);

		formPanel.add(new JLabel("Prezime:"));
		formPanel.add(prezimeField);

		formPanel.add(new JLabel("Plata:"));
		formPanel.add(plataField);

		formPanel.add(new JLabel("Uloga:"));
		formPanel.add(ulogaField);

		JButton sacuvajButton = new JButton("Sacuvaj");
		sacuvajButton.addActionListener(e -> sacuvajMenadzera());
		formPanel.add(sacuvajButton);

		// Panel za tabelu
		JPanel tablePanel = new JPanel(new BorderLayout());
		menadzerTable = new JTable();
		menadzerTable.getSelectionModel().addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting()) {
	            // Poziva se kada se završi selektovanje
	            int selectedRow = menadzerTable.getSelectedRow();
	            if (selectedRow != -1) {
	                // Uzmite podatke iz selektovanog reda
	                 imeField.setText( menadzerTable.getValueAt(selectedRow, 0).toString());
	                prezimeField.setText( menadzerTable.getValueAt(selectedRow, 1).toString());
	                 plataField.setText( menadzerTable.getValueAt(selectedRow, 2).toString());
	                 ulogaField.setText( menadzerTable.getValueAt(selectedRow, 3).toString());

	            
	            }
	        }
	    });
		JScrollPane scrollPane = new JScrollPane(menadzerTable);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		// Dodaj oba panela u glavni panel
		add(formPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
	}

	public void sacuvajMenadzera() {
		String ime = imeField.getText();
		String prezime = prezimeField.getText();
		int plata = Integer.parseInt(plataField.getText());
		String ulogaNaziv = ulogaField.getText();

		Uloga uloga = new Uloga(ulogaNaziv, "", ""); // Neki default opis i odgovornosti

		Menadzer noviMenadzer = new Menadzer(ime, prezime, plata, LocalDateTime.now(), null, uloga);
		// Ovde možete dalje raditi sa novim menadžerom, npr. dodati ga u neku listu ili
		// bazu podataka
		System.out.println(noviMenadzer.toString());
		menadzeri.clear();
		menadzeri.add(noviMenadzer);
		// Osveži tabelu sa novim podacima
		osveziTabelu();

		JOptionPane.showMessageDialog(this, "Menadzer sacuvan!");
		clearFields();
	}

	public void osveziTabelu() {
		// Dobavi podatke iz ArrayList<Menadzer> i osveži tabelu
		// Pretpostavljamo da imate ArrayList<Menadzer> instancu dostupnu
		menadzeri = getMenadzeri();

		// Postavi model tabele sa podacima
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Ime");
		model.addColumn("Prezime");
		model.addColumn("Plata");
		model.addColumn("Uloga");

		for (Menadzer menadzer : menadzeri) {
			Vector<Object> row = new Vector<>();
			row.add(menadzer.getIme());
			row.add(menadzer.getPrezime());
			row.add(menadzer.getPlata());
			row.add(menadzer.getUloga().getNaziv());

			model.addRow(row);
		}

		menadzerTable.setModel(model);
	}

	private ArrayList<Menadzer> getMenadzeri() {
		Reader r = new Reader();
		List<ArrayList<String>> rezultat = r.ucitaj("korisnik.txt");
		// rezultat.forEach(System.out::println);
		Mapper m = new Mapper();
		ArrayList<Menadzer> instance = m.konvertujUMenadzer(rezultat);
		ArrayList<Menadzer> sveMenadzeri = new ArrayList<>();
		sveMenadzeri.addAll(instance);
		sveMenadzeri.addAll(menadzeri);
		return sveMenadzeri;
	}

	private void clearFields() {
		imeField.setText("");
		prezimeField.setText("");
		plataField.setText("");
		ulogaField.setText("");
	}
}
