package fr.victorguegan;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI() {
        setTitle("Connexion");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        JLabel labelTitre = new JLabel("Bienvenue dans Pinged !");
        labelTitre.setFont(labelTitre.getFont().deriveFont(23.0f));
        labelTitre.setBorder(BorderFactory.createCompoundBorder(labelTitre.getBorder(), BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        add(labelTitre);




        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
