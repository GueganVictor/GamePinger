package fr.victorguegan;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    JLabel labelPing;
    JLabel labelMin;
    JLabel labelMax;
    JLabel labelMoy;

    public GUI() {
        setTitle("Connexion");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);

        setLayout(new GridLayout(5,0));

        JLabel labelTitre = new JLabel("Bienvenue dans Pinged !");
        labelTitre.setFont(labelTitre.getFont().deriveFont(23.0f));
        add(labelTitre);

        labelPing = new JLabel("0 ms");
        labelPing.setFont(labelPing.getFont().deriveFont(23.0f));
        add(labelPing);

        labelMin = new JLabel("Min : 0 ms");
        labelMin.setFont(labelMin.getFont().deriveFont(23.0f));
        add(labelMin);

        labelMoy = new JLabel("Max : 0 ms");
        labelMoy.setFont(labelMoy.getFont().deriveFont(23.0f));
        add(labelMoy);

        labelMax = new JLabel("Moyenne : 0 ms");
        labelMax.setFont(labelMax.getFont().deriveFont(23.0f));
        add(labelMax);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateGUI (double min, double moye, double max, double ping) {
        if (ping == -1) {
            labelPing.setText("Network error !");
        } else {
            labelPing.setText(ping+" ms");
        }
        labelMin.setText("Min : "+min+" ms");
        labelMoy.setText("Moyenne : "+String.format("%.2f",moye)+" ms");
        labelMax.setText("Max : "+max+" ms");
    }

}
