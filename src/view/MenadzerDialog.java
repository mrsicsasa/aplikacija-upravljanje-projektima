package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.osobe.Menadzer;
import models.osobe.Uloga;
import utils.Mapper;
import utils.Reader;
import utils.Writer;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class MenadzerDialog extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField imeField, prezimeField, plataField, imeUlogaField,
	pocetakAngazovanjaField,krajAngazovanjaField,opisUlogeField,odgovornostiUlogeField;
	private JTable menadzerTable;
	private ArrayList<Menadzer> menadzeri = new ArrayList<Menadzer>();

	public MenadzerDialog() {
		setLayout(new BorderLayout());

		
		JPanel formPanel = new JPanel(new GridLayout(5, 2));
		imeField = new JTextField();
		prezimeField = new JTextField();
		plataField = new JTextField();
		pocetakAngazovanjaField=new JTextField();
		krajAngazovanjaField=new JTextField();
		imeUlogaField = new JTextField();
		opisUlogeField=new JTextField();
		odgovornostiUlogeField=new JTextField();
		

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

		formPanel.add(new JLabel("Uloga:"));
		formPanel.add(imeUlogaField);

		formPanel.add(new JLabel("Opis uloge:"));
		formPanel.add(opisUlogeField);

		formPanel.add(new JLabel("Odgovornosti uloge:"));
		formPanel.add(odgovornostiUlogeField);
		

		JButton sacuvajButton = new JButton("Sacuvaj");
		JButton editButton = new JButton("Izmeni");
		JButton deleteButton = new JButton("Obrisi");
		sacuvajButton.addActionListener(e -> sacuvajMenadzera());
		editButton.addActionListener(e -> izmeniMenadzera());
		deleteButton.addActionListener(e -> izbrisiMenadzera());
		formPanel.add(sacuvajButton);
		formPanel.add(editButton);
		formPanel.add(deleteButton);

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
	                 imeUlogaField.setText( menadzerTable.getValueAt(selectedRow, 3).toString());
	             	//pocetakAngazovanjaField=new JTextField();
	        		//krajAngazovanjaField=new JTextField();
	        		//imeUlogaField = new JTextField();
	        		//opisUlogeField=new JTextField();
	        		//odgovornostiUlogeField=new JTextField();
	            
	            }
	        }
	    });
		JScrollPane scrollPane = new JScrollPane(menadzerTable);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		// Dodaj oba panela u glavni panel
		add(formPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
	}

	private void izbrisiMenadzera() {
		String ime = imeField.getText();
		Writer w = new Writer();
		Reader r = new Reader();
		List<ArrayList<String>> rezultat = r.ucitaj("korisnik.txt");
	    Iterator<ArrayList<String>> iterator = rezultat.iterator();
	    while (iterator.hasNext()) {
	        ArrayList<String> rez = iterator.next();
	        if (rez.get(0).equals(ime)) {
	            iterator.remove();
	            w.upis("korisnik.txt", rezultat);
	        }
	    }
	    menadzeri.clear();
	    osveziTabelu();
	}

	private void izmeniMenadzera() {
		String ime = imeField.getText();
		String prezime = prezimeField.getText();
		int plata = Integer.parseInt(plataField.getText());
		String ulogaNaziv = imeUlogaField.getText();
		LocalDateTime pocetakAngazovanja=parsirajDatum(pocetakAngazovanjaField.getText());
		LocalDateTime krajAngazovanja=parsirajDatum(krajAngazovanjaField.getText());
		String opisUloge=opisUlogeField.getText();
		String odgovornostiUloge=odgovornostiUlogeField.getText();
		Uloga uloga = new Uloga(ulogaNaziv, opisUloge, odgovornostiUloge); // Neki default opis i odgovornosti

		Menadzer noviMenadzer = new Menadzer(ime, prezime, plata, pocetakAngazovanja, krajAngazovanja, uloga);
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

	public void sacuvajMenadzera() {
		String ime = imeField.getText();
		String prezime = prezimeField.getText();
		int plata = Integer.parseInt(plataField.getText());
		String ulogaNaziv = imeUlogaField.getText();
		LocalDateTime pocetakAngazovanja=parsirajDatum(pocetakAngazovanjaField.getText());
		LocalDateTime krajAngazovanja=parsirajDatum(krajAngazovanjaField.getText());
		String opisUloge=opisUlogeField.getText();
		String odgovornostiUloge=odgovornostiUlogeField.getText();

		Uloga uloga = new Uloga(ulogaNaziv, opisUloge, odgovornostiUloge); // Neki default opis i odgovornosti

		Menadzer noviMenadzer = new Menadzer(ime, prezime, plata, pocetakAngazovanja, krajAngazovanja, uloga);
		// Ovde možete dalje raditi sa novim menadžerom, npr. dodati ga u neku listu ili
		// bazu podataka
		System.out.println(noviMenadzer.toString());
		Writer w = new Writer();
		Reader r = new Reader();
		List<ArrayList<String>> rezultat = r.ucitaj("korisnik.txt");
		ArrayList<String> noviMenadzerPodaci=new ArrayList<String>();
		noviMenadzerPodaci.add(noviMenadzer.getIme());
		noviMenadzerPodaci.add(noviMenadzer.getPrezime());
		noviMenadzerPodaci.add(String.valueOf(noviMenadzer.getPlata()));
		noviMenadzerPodaci.add(parsirajDatumuString(noviMenadzer.getPocetakAngazovanja()));
		noviMenadzerPodaci.add(parsirajDatumuString(noviMenadzer.getZavrsetakAngazovanja()));
		noviMenadzerPodaci.add(noviMenadzer.getUloga().getNaziv());
		noviMenadzerPodaci.add(noviMenadzer.getUloga().getOpis());
		noviMenadzerPodaci.add(noviMenadzer.getUloga().getOdgovornosti());
		rezultat.add(noviMenadzerPodaci);
		w.upis("korisnik.txt", rezultat);
		// Osveži tabelu sa novim podacima
		menadzeri.clear();
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
		imeUlogaField.setText("");
	}
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	private LocalDateTime parsirajDatum(String datum) {
		return LocalDateTime.parse(datum, FORMATTER);
	}
	private String parsirajDatumuString(LocalDateTime datum) {
		String  rez = String.format("%04d-%02d-%02d %02d:%02d", 
		        datum.getYear(), datum.getMonthValue(), datum.getDayOfMonth(),
		        datum.getHour(), datum.getMinute());
		    return rez;
	
	}
}
