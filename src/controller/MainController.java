package controller;

import view.KvarljiviProizvodDialog;
import view.MainView;
import view.MenadzerDialog;
import view.PretragaDialog;
import view.PromoterDialog;
import view.TehnickiProizvodDialog;

import javax.swing.*;

import models.osobe.Promoter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
	private MainView view;

	public MainController(MainView view) {
		this.view = view;
		setupButtonListeners();
	}

	private void setupButtonListeners() {
		for (int i = 0; i < 5; i++) {
			final int index = i;
			view.addButtonListener(i, e -> prikaziFormu(index));
		}
	}

	private void prikaziFormu(int index) {
		if (index == 0) {
			MenadzerDialog menadzerDialog = new MenadzerDialog();
			Object[] options = { "Sacuvaj", "Otkazi" };
			menadzerDialog.osveziTabelu();
			int result = JOptionPane.showOptionDialog(null, menadzerDialog, "Izmena menadzera",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			if (result == JOptionPane.YES_OPTION) {

				menadzerDialog.sacuvajMenadzera();
			}
		} else if (index == 1) {

			PromoterDialog promoterDialog = new PromoterDialog();
			Object[] options4 = { "Sacuvaj", "Otkazi" };
			promoterDialog.osveziTabelu();
			int result4 = JOptionPane.showOptionDialog(null, promoterDialog, "Izmena promotera",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options4, options4[0]);

			if (result4 == JOptionPane.YES_OPTION) {

				promoterDialog.sacuvajPromotera();
			}
		} else if (index == 2) {

			KvarljiviProizvodDialog kvarljiviProizvodDialog = new KvarljiviProizvodDialog();
			Object[] options2 = { "Sacuvaj", "Otkazi" };
			kvarljiviProizvodDialog.osveziTabelu();
			int result2 = JOptionPane.showOptionDialog(null, kvarljiviProizvodDialog, "Izmena kvarljivih proizvoda",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);

			if (result2 == JOptionPane.YES_OPTION) {

				kvarljiviProizvodDialog.sacuvajKvarljiviProizvod();
			}
		} else if (index == 3) {

			TehnickiProizvodDialog tehnickiProizvodDialog = new TehnickiProizvodDialog();
			Object[] options3 = { "Sacuvaj", "Otkazi" };
			tehnickiProizvodDialog.osveziTabelu();
			int result3 = JOptionPane.showOptionDialog(null, tehnickiProizvodDialog, "Izmena tehnickih proizvoda",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options3, options3[0]);

			if (result3 == JOptionPane.YES_OPTION) {

				tehnickiProizvodDialog.sacuvajTehnickiProizvod();
			}
		} else if (index == 4) {
			PretragaDialog pretragaDialog = new PretragaDialog();
			Object[] options5 = { "Pretrazi", "Otkazi" };
			int result5 = JOptionPane.showOptionDialog(null, pretragaDialog, "Pretraga", JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options5, options5[0]);

		}

	}
}
