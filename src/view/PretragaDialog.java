package view;

import javax.swing.*;
import utils.Reader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PretragaDialog extends JPanel {
    private JTextField pretragaField;
    private JButton pretraziButton;
    private JList<String> rezultatiList;
    private Reader r = new Reader();
    private List<ArrayList<String>> menadzeri = r.ucitaj("korisnik.txt");
    private List<ArrayList<String>> promoteri = r.ucitaj("promoteri.txt");
    private List<ArrayList<String>> tehnickiProizvodi = r.ucitaj("tehnickiProizvodi.txt");
    private List<ArrayList<String>> kvarljiviProizvodi = r.ucitaj("kvarljiviProizvodi.txt");

    public PretragaDialog() {
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(3, 2));

        pretragaField = new JTextField();
        formPanel.add(new JLabel("Atribut za pretragu:"));
        formPanel.add(pretragaField);

        pretraziButton = new JButton("Pretraga");
        pretraziButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pretragaText = pretragaField.getText();
                List<String> matchingResults = searchInLists(pretragaText);
                updateResultsList(matchingResults.toArray(new String[0]));
            }
        });
        formPanel.add(pretraziButton);

        rezultatiList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(rezultatiList);
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private List<String> searchInLists(String pretragaText) {
        List<String> matchingResults = new ArrayList<>();

        searchAndAddResults("Menadzer", menadzeri, pretragaText, matchingResults);
        searchAndAddResults("Promoter", promoteri, pretragaText, matchingResults);
        searchAndAddResults("Tehnicki Proizvod", tehnickiProizvodi, pretragaText, matchingResults);
        searchAndAddResults("Kvarljivi Proizvod", kvarljiviProizvodi, pretragaText, matchingResults);

        return matchingResults;
    }

    private void searchAndAddResults(String category, List<ArrayList<String>> dataList,
                                     String pretragaText, List<String> matchingResults) {
        for (List<String> data : dataList) {
            if (containsAttribute(data, pretragaText)) {
                matchingResults.add(formatResult(category, data));
            }
        }
    }

    private boolean containsAttribute(List<String> list, String attribute) {
        for (String value : list) {
            if (value.contains(attribute)) {
                return true;
            }
        }
        return false;
    }

    private String formatResult(String category, List<String> result) {
        return category + ": " + String.join(", ", result);
    }

    private void updateResultsList(String[] results) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(Arrays.asList(results));
        rezultatiList.setModel(listModel);
    }
}
