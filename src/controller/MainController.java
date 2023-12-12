package controller;

import view.MainView;
import view.MenadzerForm;

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
            MenadzerForm menadzerForm = new MenadzerForm();
            Object[] options = {"Sacuvaj", "Otkazi"};
            menadzerForm.osveziTabelu();
            int result = JOptionPane.showOptionDialog(null, menadzerForm, "Izmena Menadzera",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
         
            if (result == JOptionPane.YES_OPTION) {
                // Ako korisnik pritisne "Sacuvaj"
                menadzerForm.sacuvajMenadzera();
            }
        } else {
            // Dodajte sličan kod za ostala dugmad prema potrebama vaše aplikacije
            JOptionPane.showMessageDialog(null, "Forma za Button " + (index + 1));
        }
    }
}
