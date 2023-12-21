package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.osobe.Menadzer;
import models.osobe.Odmor;
import models.osobe.Odmori;
import models.osobe.Promoter;
import utils.Mapper;
import utils.Reader;
import utils.Writer;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PromoterDialog extends JPanel {
	private static final long serialVersionUID = 2083067193350613291L;
	private static final String FILE_NAME = "promoteri.txt";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private JTextField imeField, prezimeField, plataField, pocetakAngazovanjaField, krajAngazovanjaField,
			pocetakOdmoraField, krajOdmoraField, imeNadrednjenogField;
	private JTable promoterTable;
	private JComboBox<Odmor> odmoriDropdown;
	private ArrayList<Promoter> promoteri = getPromoteri();
	private Promoter trenutniPromoter = new Promoter();

	public PromoterDialog() {
		setLayout(new BorderLayout());

		JPanel formPanel = new JPanel(new GridLayout(13, 2));
		imeField = new JTextField();
		prezimeField = new JTextField();
		plataField = new JTextField();
		pocetakAngazovanjaField = new JTextField();
		krajAngazovanjaField = new JTextField();
		pocetakOdmoraField = new JTextField();
		krajOdmoraField = new JTextField();
		imeNadrednjenogField = new JTextField();

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

		formPanel.add(new JLabel("Ime nadredjenog:"));
		formPanel.add(imeNadrednjenogField);

		formPanel.add(new JLabel("Pocetak odmora:"));
		formPanel.add(pocetakOdmoraField);

		formPanel.add(new JLabel("Kraj odmora:"));
		formPanel.add(krajOdmoraField);

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
					imeNadrednjenogField.setText(promoterTable.getValueAt(selectedRow, 5).toString());
					trenutniPromoter.setIme(promoterTable.getValueAt(selectedRow, 0).toString());
					trenutniPromoter.setPrezime(promoterTable.getValueAt(selectedRow, 1).toString());
					trenutniPromoter.setPlata(Integer.parseInt(promoterTable.getValueAt(selectedRow, 2).toString()));
					trenutniPromoter
							.setPocetakAngazovanja(parsirajDatum(promoterTable.getValueAt(selectedRow, 3).toString()));
					trenutniPromoter.setZavrsetakAngazovanja(
							parsirajDatum(promoterTable.getValueAt(selectedRow, 4).toString()));
					Menadzer menadzer = new Menadzer();
					menadzer.setIme(promoterTable.getValueAt(selectedRow, 5).toString());
					osveziDropdownOdmora();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(promoterTable);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		add(formPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
	}

	private void osveziDropdownOdmora() {
		if (trenutniPromoter != null) {
			// Pronađi promotera u listi na osnovu selektovanog reda u tabeli
			int selectedRow = promoterTable.getSelectedRow();
			if (selectedRow != -1) {
				String imePromotera = promoterTable.getValueAt(selectedRow, 0).toString();
				String prezimePromotera = promoterTable.getValueAt(selectedRow, 1).toString();

				// Pronađi promotera u listi na osnovu imena i prezimena
				Promoter pronadjeniPromoter = null;
				for (Promoter promoter : promoteri) {
					if (promoter.getIme().equals(imePromotera) && promoter.getPrezime().equals(prezimePromotera)) {
						pronadjeniPromoter = promoter;
						break;
					}
				}

				if (pronadjeniPromoter != null) {
					// Sada možete preuzeti odmore od pronadjenog promotera
					Odmori odmoriModel = pronadjeniPromoter.getOdmori();
					DefaultComboBoxModel<Odmor> odmoriComboBoxModel = new DefaultComboBoxModel<>(
							odmoriModel.getOdmori().toArray(new Odmor[0]));
					odmoriDropdown.setModel(odmoriComboBoxModel);
				}
			}
		}
	}

	private void dodajOdmor() {
		if (trenutniPromoter != null) {
			// Provera da li su polja za unos odmora pravilno popunjena
			if (pocetakOdmoraField.getText().isEmpty() || krajOdmoraField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Unesite pocetak i kraj odmora!");
				return;
			}

			int selectedRow = promoterTable.getSelectedRow();
			if (selectedRow != -1) {
				String imePromotera = promoterTable.getValueAt(selectedRow, 0).toString();
				String prezimePromotera = promoterTable.getValueAt(selectedRow, 1).toString();

				Promoter pronadjeniPromoter = null;
				for (Promoter promoter : promoteri) {
					if (promoter.getIme().equals(imePromotera) && promoter.getPrezime().equals(prezimePromotera)) {
						pronadjeniPromoter = promoter;
						break;
					}
				}

				if (pronadjeniPromoter != null) {
					// Parsiranje datuma iz polja za unos odmora
					LocalDate pocetakOdmora = parsirajDatum(pocetakOdmoraField.getText());
					LocalDate krajOdmora = parsirajDatum(krajOdmoraField.getText());

					// Kreiranje nove instance Odmor i dodavanje u listu odmora promotera
					Odmor noviOdmor = new Odmor(pocetakOdmora, krajOdmora);
					pronadjeniPromoter.getOdmori().dodajOdmor(noviOdmor);
					for (Promoter p : promoteri) {
						if (p.equals(pronadjeniPromoter)) {
							p.setOdmori(pronadjeniPromoter.getOdmori()); // Postavite nove odmore
							break;
						}
					}
					sacuvajPromotereUFajl(promoteri);
					osveziTabelu();
					osveziDropdownOdmora();
				}
			}
		}
	}

	private void obrisiOdmor() {
		if (trenutniPromoter != null) {
			int selectedRow = promoterTable.getSelectedRow();
			if (selectedRow != -1) {
				String imePromotera = promoterTable.getValueAt(selectedRow, 0).toString();
				String prezimePromotera = promoterTable.getValueAt(selectedRow, 1).toString();

				Promoter pronadjeniPromoter = null;
				for (Promoter promoter : promoteri) {
					if (promoter.getIme().equals(imePromotera) && promoter.getPrezime().equals(prezimePromotera)) {
						pronadjeniPromoter = promoter;
						break;
					}
				}

				if (pronadjeniPromoter != null) {
					Odmor selectedOdmor = (Odmor) odmoriDropdown.getSelectedItem();
					if (selectedOdmor != null) {
						pronadjeniPromoter.getOdmori().obrisiOdmor(selectedOdmor);
						osveziTabelu();
						sacuvajPromotereUFajl(promoteri);
					}
				}
			}
		}
	}

	private void izbrisiPromotera() {
		int selectedRow = promoterTable.getSelectedRow();
		if (selectedRow != -1) {
			String imePromotera = promoterTable.getValueAt(selectedRow, 0).toString();
			String prezimePromotera = promoterTable.getValueAt(selectedRow, 1).toString();

			Promoter pronadjeniPromoter = null;
			for (Promoter promoter : promoteri) {
				if (promoter.getIme().equals(imePromotera) && promoter.getPrezime().equals(prezimePromotera)) {
					pronadjeniPromoter = promoter;
					break;
				}
			}

			if (pronadjeniPromoter != null) {
				promoteri.remove(pronadjeniPromoter);
				osveziTabelu();
				sacuvajPromotereUFajl(promoteri);
			}
		}
	}

	private void izmeniPromotera() {
		int selectedRow = promoterTable.getSelectedRow();
		if (selectedRow != -1) {

			String imePromotera = promoterTable.getValueAt(selectedRow, 0).toString();
			String prezimePromotera = promoterTable.getValueAt(selectedRow, 1).toString();

			Promoter pronadjeniPromoter = null;
			for (Promoter promoter : promoteri) {
				if (promoter.getIme().equals(imePromotera) && promoter.getPrezime().equals(prezimePromotera)) {
					pronadjeniPromoter = promoter;
					break;
				}
			}

			if (pronadjeniPromoter != null) {
				String novoIme = imeField.getText();
				String novoPrezime = prezimeField.getText();
				String novaPlata = plataField.getText();
				String noviPocetakAngazovanja = pocetakAngazovanjaField.getText();
				String noviKrajAngazovanja = krajAngazovanjaField.getText();
				pronadjeniPromoter.setIme(novoIme);
				pronadjeniPromoter.setPrezime(novoPrezime);
				pronadjeniPromoter.setPlata(Integer.parseInt(novaPlata));
				pronadjeniPromoter.setPocetakAngazovanja(parsirajDatum(noviPocetakAngazovanja));
				pronadjeniPromoter.setZavrsetakAngazovanja(parsirajDatum(noviKrajAngazovanja));

				for (int i = 0; i < promoteri.size(); i++) {
					if (rowMatchesCurrentPromoter(promoteri.get(i))) {
						promoteri.set(i, pronadjeniPromoter);
						break;
					}
				}

				osveziTabelu();
				sacuvajPromotereUFajl(promoteri);
			}
		}
	}

	public void sacuvajPromotera() {

		 try {
	            
	            if (imeField.getText().isEmpty() || prezimeField.getText().isEmpty() || plataField.getText().isEmpty()
	                    || pocetakAngazovanjaField.getText().isEmpty() || krajAngazovanjaField.getText().isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greška", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            String ime = imeField.getText();
	            String prezime = prezimeField.getText();
	            int plata = Integer.parseInt(plataField.getText());
	            LocalDate pocetakAngazovanja = parsirajDatum(pocetakAngazovanjaField.getText());
	            LocalDate krajAngazovanja = parsirajDatum(krajAngazovanjaField.getText());
	            String imeNadredjenog = imeNadrednjenogField.getText();
	            Menadzer m = new Menadzer();
	            m.setIme(imeNadredjenog);
	            Odmori odmori = new Odmori();

	            Promoter noviPromoter = new Promoter(ime, prezime, plata, pocetakAngazovanja, krajAngazovanja, m, odmori);

	      

	            for (int i = 0; i < odmoriDropdown.getItemCount(); i++) {
	                noviPromoter.getOdmori().dodajOdmor(odmoriDropdown.getItemAt(i));
	            }

	            promoteri.add(noviPromoter);

	            sacuvajPromotereUFajl(promoteri);

	            
	            osveziTabelu();

	            JOptionPane.showMessageDialog(this, "Novi promoter dodat!");
	            clearFields();

	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "Nevalidan unos za polje 'Plata'.", "Greška", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Došlo je do greške prilikom čuvanja promotera.", "Greška", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace(); 
	        }
	    }

	

	public void osveziTabelu() {

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Ime");
		model.addColumn("Prezime");
		model.addColumn("Plata");
		model.addColumn("Pocetak angozavanje");
		model.addColumn("Kraj angazovanja");
		model.addColumn("Ime nadredjenog");

		for (Promoter promoter : promoteri) {
			Vector<Object> row = new Vector<>();
			row.add(promoter.getIme());
			row.add(promoter.getPrezime());
			row.add(promoter.getPlata());
			row.add(promoter.getPocetakAngazovanja());
			row.add(promoter.getZavrsetakAngazovanja());
			row.add(promoter.getNaredjeni().getIme());

			model.addRow(row);
		}

		promoterTable.setModel(model);
	}

	private ArrayList<Promoter> getPromoteri() {
		Reader r = new Reader();
		List<ArrayList<String>> rezultat = r.ucitaj("promoteri.txt");
		Mapper m = new Mapper();
		ArrayList<Promoter> instance = m.konvertujUPromotera(rezultat);
		return instance;
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

	private void sacuvajPromotereUFajl(ArrayList<Promoter> promoteri) {
		Writer writer = new Writer();
		List<ArrayList<String>> podaci = new ArrayList<>();

		for (Promoter promoter : promoteri) {

			ArrayList<String> instanca = new ArrayList<>();
			instanca.add(promoter.getIme());
			instanca.add(promoter.getPrezime());
			instanca.add(String.valueOf(promoter.getPlata()));
			instanca.add(promoter.getPocetakAngazovanja().toString());
			instanca.add(promoter.getZavrsetakAngazovanja().toString());
			instanca.add(promoter.getNaredjeni().getIme());
			for (Odmor odmor : promoter.getOdmori().getOdmori()) {
				instanca.add(odmor.getPocetakOdmora().toString());
				instanca.add(odmor.getKrajOdmora().toString());
			}

			podaci.add(instanca);
		}

		writer.upis(FILE_NAME, podaci);

	}

	private boolean rowMatchesCurrentPromoter(Promoter promoter) {
		return promoter.getIme().equals(trenutniPromoter.getIme())
				&& promoter.getPrezime().equals(trenutniPromoter.getPrezime())
				&& promoter.getPlata() == trenutniPromoter.getPlata()
				&& promoter.getPocetakAngazovanja().equals(trenutniPromoter.getPocetakAngazovanja())
				&& promoter.getZavrsetakAngazovanja().equals(trenutniPromoter.getZavrsetakAngazovanja());
	}

}
