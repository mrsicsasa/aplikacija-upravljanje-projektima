package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.osobe.Menadzer;
import models.osobe.Uloga;
import utils.Mapper;
import utils.Reader;
import utils.Writer;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class MenadzerDialog extends JPanel {
	private static final String FILE_NAME = "korisnik.txt";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final long serialVersionUID = 1L;
	private JTextField imeField, prezimeField, plataField, imeUlogaField,
	pocetakAngazovanjaField,krajAngazovanjaField,opisUlogeField,odgovornostiUlogeField;
	private JTable menadzerTable;
	private ArrayList<Menadzer> menadzeri = new ArrayList<Menadzer>();
	private Menadzer trenutniMenadzer=new Menadzer();
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
	             	pocetakAngazovanjaField.setText(menadzerTable.getValueAt(selectedRow, 3).toString());
	        		krajAngazovanjaField.setText(menadzerTable.getValueAt(selectedRow, 4).toString());
	        		imeUlogaField.setText( menadzerTable.getValueAt(selectedRow, 5).toString());
	        		opisUlogeField.setText( menadzerTable.getValueAt(selectedRow, 6).toString());
	        		odgovornostiUlogeField.setText( menadzerTable.getValueAt(selectedRow, 7).toString());
	        		trenutniMenadzer.setIme(menadzerTable.getValueAt(selectedRow, 0).toString());
	        		trenutniMenadzer.setPrezime(menadzerTable.getValueAt(selectedRow, 1).toString());
	        		trenutniMenadzer.setPlata(Integer.parseInt(menadzerTable.getValueAt(selectedRow, 2).toString()));
	        		trenutniMenadzer.setPocetakAngazovanja(parsirajDatum(menadzerTable.getValueAt(selectedRow, 3).toString()));
	        		trenutniMenadzer.setZavrsetakAngazovanja(parsirajDatum(menadzerTable.getValueAt(selectedRow, 4).toString()));
	        		Uloga trenutnaUloga=new Uloga(
	        				menadzerTable.getValueAt(selectedRow, 5).toString(),
	        				menadzerTable.getValueAt(selectedRow, 6).toString(),
	        				menadzerTable.getValueAt(selectedRow, 7).toString()
	        				);
	        		trenutniMenadzer.setUloga(trenutnaUloga);
	        		System.out.println(trenutniMenadzer.getIme());
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
	        Writer writer = new Writer();
	        Reader reader = new Reader();
	        List<ArrayList<String>> data = reader.ucitaj(FILE_NAME);

	        Iterator<ArrayList<String>> iterator = data.iterator();
	        while (iterator.hasNext()) {
	            ArrayList<String> rez = iterator.next();
	            if (rowMatchesCurrentMenadzer(rez)) {
	                iterator.remove();
	                writer.upis(FILE_NAME, data);
	            }
	        }
	        menadzeri.clear();
	        clearFields();
	        osveziTabelu();
	}

	private void izmeniMenadzera() {
		String ime = imeField.getText();
		String prezime = prezimeField.getText();
		int plata = Integer.parseInt(plataField.getText());
		String ulogaNaziv = imeUlogaField.getText();
		LocalDate pocetakAngazovanja=parsirajDatum(pocetakAngazovanjaField.getText());
		LocalDate krajAngazovanja=parsirajDatum(krajAngazovanjaField.getText());
		String opisUloge=opisUlogeField.getText();
		String odgovornostiUloge=odgovornostiUlogeField.getText();
		Uloga uloga = new Uloga(ulogaNaziv, opisUloge, odgovornostiUloge); 
		System.out.println(trenutniMenadzer.getIme());
		Menadzer noviMenadzer = new Menadzer(ime, prezime, plata, pocetakAngazovanja, krajAngazovanja, uloga);
		Writer w = new Writer();
		Reader r = new Reader();
		Mapper m=new Mapper();
		ArrayList<String> noviMenadzerPodaci=new ArrayList<String>();
		noviMenadzerPodaci.add(ime);
		noviMenadzerPodaci.add(prezime);
		noviMenadzerPodaci.add(String.valueOf(plata));
		noviMenadzerPodaci.add(parsirajDatumuString(pocetakAngazovanja));
		noviMenadzerPodaci.add(parsirajDatumuString(krajAngazovanja));
		noviMenadzerPodaci.add(ulogaNaziv);
		noviMenadzerPodaci.add(opisUloge);
		noviMenadzerPodaci.add(odgovornostiUloge);
		List<ArrayList<String>> rezultat = r.ucitaj("korisnik.txt");
		for(int i = 0; i < rezultat.size(); i++) {
			ArrayList<String> rez = rezultat.get(i);
			if(rowMatchesCurrentMenadzer(rez)) {
				System.out.println("isti su");
				rezultat.set(i, noviMenadzerPodaci);
				
			}else {
				System.out.println("nisu isti");
			}
		}
		w.upis("korisnik.txt", rezultat);
		
		menadzeri.clear();
		osveziTabelu();

		JOptionPane.showMessageDialog(this, "Menadzer izmenjen!");
		clearFields();
	}

	public void sacuvajMenadzera() {
	    try {
	        String ime = imeField.getText();
	        String prezime = prezimeField.getText();
	        int plata = Integer.parseInt(plataField.getText());

	    
	        if (ime.isEmpty() || prezime.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Polja 'Ime' i 'Prezime' ne smeju biti prazna.", "Greška", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        String ulogaNaziv = imeUlogaField.getText();
	        LocalDate pocetakAngazovanja = parsirajDatum(pocetakAngazovanjaField.getText());
	        LocalDate krajAngazovanja = parsirajDatum(krajAngazovanjaField.getText());
	        String opisUloge = opisUlogeField.getText();
	        String odgovornostiUloge = odgovornostiUlogeField.getText();

	       
	        if (pocetakAngazovanja.isAfter(krajAngazovanja)) {
	            JOptionPane.showMessageDialog(this, "Datum 'Pocetak angazovanja' ne sme biti posle datuma 'Kraj angazovanja'.", "Greška", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        Uloga uloga = new Uloga(ulogaNaziv, opisUloge, odgovornostiUloge);

	        Menadzer noviMenadzer = new Menadzer(ime, prezime, plata, pocetakAngazovanja, krajAngazovanja, uloga);

	        Writer w = new Writer();
	        Reader r = new Reader();
	        List<ArrayList<String>> rezultat = r.ucitaj("korisnik.txt");
	        ArrayList<String> noviMenadzerPodaci = new ArrayList<>();
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
	        menadzeri.clear();
	        osveziTabelu();

	        JOptionPane.showMessageDialog(this, "Menadzer sacuvan!");
	        clearFields();
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Polje 'Plata' mora sadržavati numeričku vrednost.", "Greška", JOptionPane.ERROR_MESSAGE);
	    } catch (DateTimeParseException e) {
	        JOptionPane.showMessageDialog(this, "Neispravan format datuma. Molimo koristite format yyyy-MM-dd.", "Greška", JOptionPane.ERROR_MESSAGE);
	    }
	}


	public void osveziTabelu() {
		
		menadzeri = getMenadzeri();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Ime");
		model.addColumn("Prezime");
		model.addColumn("Plata");
		model.addColumn("Pocetak angozavanje");
		model.addColumn("Kraj angazovanja");
		model.addColumn("Uloga naziv");
		model.addColumn("Uloga opis");
		model.addColumn("Uloga odgovornosti");

		for (Menadzer menadzer : menadzeri) {
			Vector<Object> row = new Vector<>();
			row.add(menadzer.getIme());
			row.add(menadzer.getPrezime());
			row.add(menadzer.getPlata());
			row.add(menadzer.getPocetakAngazovanja());
			row.add(menadzer.getZavrsetakAngazovanja());
			row.add(menadzer.getUloga().getNaziv());
			row.add(menadzer.getUloga().getOpis());
			row.add(menadzer.getUloga().getOdgovornosti());

			model.addRow(row);
		}

		menadzerTable.setModel(model);
	}

	private ArrayList<Menadzer> getMenadzeri() {
		Reader r = new Reader();
		List<ArrayList<String>> rezultat = r.ucitaj("korisnik.txt");
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
		pocetakAngazovanjaField.setText("");
		krajAngazovanjaField.setText("");
		imeUlogaField.setText("");
		opisUlogeField.setText("");
		odgovornostiUlogeField.setText("");
	}
	
	private LocalDate parsirajDatum(String datum) {
		return LocalDate.parse(datum, FORMATTER);
	}
	private String parsirajDatumuString(LocalDate datum) {
		String  rez = String.format("%04d-%02d-%02d", 
		        datum.getYear(), datum.getMonthValue(), datum.getDayOfMonth());
		    return rez;
	
	}
	  private boolean rowMatchesCurrentMenadzer(ArrayList<String> row) {
	        return row.get(0).equals(trenutniMenadzer.getIme()) &&
	               row.get(1).equals(trenutniMenadzer.getPrezime()) &&
	               row.get(2).equals(String.valueOf(trenutniMenadzer.getPlata())) &&
	               row.get(3).equals(parsirajDatumuString(trenutniMenadzer.getPocetakAngazovanja())) &&
	               row.get(4).equals(parsirajDatumuString(trenutniMenadzer.getZavrsetakAngazovanja())) &&
	               row.get(5).equals(trenutniMenadzer.getUloga().getNaziv()) &&
	               row.get(6).equals(trenutniMenadzer.getUloga().getOpis()) &&
	               row.get(7).equals(trenutniMenadzer.getUloga().getOdgovornosti());
	    }
}
