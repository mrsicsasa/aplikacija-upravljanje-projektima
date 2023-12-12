package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView {
    private JFrame frame;
    private JButton[] buttons;

    public MainView() {
        frame = new JFrame("MVC SpringLayout Primjer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            buttons[i] = new JButton("Button " + (i + 1));
        }

        setupLayout();
        setupButtonListeners();

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); // Postavljanje prozora na sredinu ekrana
        frame.setVisible(true);
    }

    public void addButtonListener(int index, ActionListener listener) {
        buttons[index].addActionListener(listener);
    }

    private void setupLayout() {
        SpringLayout layout = new SpringLayout();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(layout);

        for (int i = 0; i < 5; i++) {
            contentPane.add(buttons[i]);
        }

        // Postavljanje konkretnih ograničenja za SpringLayout
        for (int i = 0; i < 5; i++) {
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttons[i], 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, buttons[i], i * 30, SpringLayout.VERTICAL_CENTER, contentPane);
        }
    }

    private void setupButtonListeners() {
        // Metoda se sada ne koristi jer smo logiku preneli u MainController
    }
}
