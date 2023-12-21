package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView {
    private JFrame frame;
    private JButton[] buttons;

    public MainView() {
        frame = new JFrame("Upravljanje projektima");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[5];
        for (int i = 0; i < 5; i++) {
           if(i==0) {
        	   buttons[i] = new JButton("Mendazer");
           }else if(i==1) {
        	   buttons[i] = new JButton("Promoter");
           }
           else if(i==2) {
        	   buttons[i] = new JButton("Kvarljivi proizvod");
           }
           else if(i==3) {
        	   buttons[i] = new JButton("Tehnicki proizvod");
           }
           else if(i==4) {
        	   buttons[i] = new JButton("Pretraga");
           }
        }

        setupLayout();
        setupButtonListeners();

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); 
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

        
        for (int i = 0; i < 5; i++) {
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttons[i], 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, buttons[i], i * 30, SpringLayout.VERTICAL_CENTER, contentPane);
        }
    }

    private void setupButtonListeners() {
    }
}
