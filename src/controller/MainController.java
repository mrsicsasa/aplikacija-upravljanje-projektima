package controller;

import view.KvarljiviProizvodDialog;
import view.MainView;
import view.MenadzerDialog;
import view.TehnickiProizvodDialog;

import javax.swing.*;
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
        if (index == 0) { // Ako je pritisnuto prvo dugme
            MenadzerDialog menadzerDialog = new MenadzerDialog();
            Object[] options = {"Sacuvaj", "Otkazi"};
            menadzerDialog.osveziTabelu();
            int result = JOptionPane.showOptionDialog(null, menadzerDialog, "Izmena Menadzera",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (result == JOptionPane.YES_OPTION) {
                // Ako korisnik pritisne "Sacuvaj"
                menadzerDialog.sacuvajMenadzera();
            }
        } else if (index == 2) {
            // Ako je pritisnuto treće dugme
            KvarljiviProizvodDialog kvarljiviProizvodDialog = new KvarljiviProizvodDialog();
            Object[] options2 = {"Sacuvaj", "Otkazi"};
            kvarljiviProizvodDialog.osveziTabelu();
            int result2 = JOptionPane.showOptionDialog(null, kvarljiviProizvodDialog, "Izmena kvarljivih proizvoda",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);

            if (result2 == JOptionPane.YES_OPTION) {
                // Ako korisnik pritisne "Sacuvaj"
                kvarljiviProizvodDialog.sacuvajKvarljiviProizvod();
            }
        }else if (index == 3) {
            // Ako je pritisnuto treće dugme
            TehnickiProizvodDialog tehnickiProizvodDialog=new TehnickiProizvodDialog();
            Object[] options3 = {"Sacuvaj", "Otkazi"};
            tehnickiProizvodDialog.osveziTabelu();
            int result3 = JOptionPane.showOptionDialog(null, tehnickiProizvodDialog, "Izmena kvarljivih proizvoda",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options3, options3[0]);

            if (result3 == JOptionPane.YES_OPTION) {
                // Ako korisnik pritisne "Sacuvaj"
                tehnickiProizvodDialog.sacuvajTehnickiProizvod();
            }
        } 
        else {
            // Dodajte sličan kod za ostala dugmad prema potrebama vaše aplikacije
            JOptionPane.showMessageDialog(null, "Forma za Button " + (index + 1));
        }
    }

    }
