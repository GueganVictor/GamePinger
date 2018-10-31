package fr.victorguegan;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GUI extends JFrame implements ActionListener {

    private Controler c;

    private JButton bLeague;
    private JButton bOverwatch;
    private JButton bCounterStrike;

    private JTextField textIP;

    private JLabel labelPing;
    private JLabel labelMin;
    private JLabel labelMax;
    private JLabel labelMoy;

    GUI(Controler c){
        this.c = c;
        setTitle("Game Pinger");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new MigLayout("", "[grow, center][grow, center][grow, center]", ""));

        JLabel labelTitre = new JLabel("Bienvenue dans Pinger !");
        labelTitre.setFont(labelTitre.getFont().deriveFont(23.0f));
        add(labelTitre, "span");

        labelPing = new JLabel("0 ms");
        labelPing.setFont(labelPing.getFont().deriveFont(23.0f));
        add(labelPing, "span");

        labelMin = new JLabel("Min : 0 ");
        labelMin.setFont(labelMin.getFont().deriveFont(12.0f));
        add(labelMin,"span");

        labelMax = new JLabel("Max : 0 ");
        labelMax.setFont(labelMax.getFont().deriveFont(12.0f));
        add(labelMax, "span");

        labelMoy = new JLabel("Moy : 0 ");
        labelMoy.setFont(labelMoy.getFont().deriveFont(12.0f));
        add(labelMoy, "span, wrap 10");

        bLeague = makeButton("LoL");
        bLeague.setBackground(Color.LIGHT_GRAY.darker());
        add(bLeague, "");

        bOverwatch = makeButton("OW");
        add(bOverwatch, "");

        bCounterStrike = makeButton("CS");
        add(bCounterStrike, "wrap 8");

        JLabel labelMoy = new JLabel("Entrez une adresse de votre choix :");
        labelMoy.setFont(labelMoy.getFont().deriveFont(12.0f));
        add(labelMoy, "span");

        JLabel labelHelp = new JLabel("<html><body>Appuyez sur <i>ENTREE</i> pour valider</body></html>");
        labelHelp.setFont(labelHelp.getFont().deriveFont(10.0f));
        add(labelHelp, "span, wrap 10");

        textIP = new JTextField(c.ips[0],15);
        textIP.setHorizontalAlignment(JTextField.CENTER);
        textIP.addActionListener(this);
        textIP.setFont(labelMoy.getFont().deriveFont(18.0f));
        add(textIP, "span");



        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton makeButton (String name) {
        JButton button = new JButton(name);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorderPainted(false);
        button.setBackground(Color.GRAY.brighter());
        button.setFocusPainted(false);
        button.setMinimumSize(new Dimension(40,40));
        button.setMaximumSize(new Dimension(40,40));
        button.setContentAreaFilled(false);
        button.addActionListener(this);
        button.setOpaque(true);
        return button;
    }

    public void updateGUI (double ping) {
        c.majStat(ping);
        if (ping == -1) {
            labelPing.setForeground(Color.RED.darker());
            labelPing.setText("Erreur réseau !");
        } else {
            if (ping < 75) labelPing.setForeground(Color.GREEN.darker());
            else if (ping > 100) labelPing.setForeground(Color.RED.darker());
            else labelPing.setForeground(Color.ORANGE.darker());
            labelPing.setText(ping+" ms");
        }
        labelMin.setText("Min : "+c.min+" ms");
        labelMoy.setText("Moyenne : "+String.format("%.2f",c.moyenne)+" ms");
        labelMax.setText("Max : "+c.max+" ms");
        if (!textIP.getText().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            textIP.setForeground(Color.RED.darker());
        } else {
            textIP.setForeground(Color.BLACK);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bCounterStrike)) {
            bCounterStrike.setBackground(Color.LIGHT_GRAY.darker());
            bLeague.setBackground(Color.GRAY.brighter());
            bOverwatch.setBackground(Color.GRAY.brighter());
            c.ipActuelle = c.ips[2];
            c.resetStat();
        }
        if (e.getSource().equals(bLeague)) {
            bLeague.setBackground(Color.LIGHT_GRAY.darker());
            bCounterStrike.setBackground(Color.GRAY.brighter());
            bOverwatch.setBackground(Color.GRAY.brighter());
            c.ipActuelle = c.ips[0];
            c.resetStat();
        }
        if (e.getSource().equals(bOverwatch)) {
            bOverwatch.setBackground(Color.LIGHT_GRAY.darker());
            bCounterStrike.setBackground(Color.GRAY.brighter());
            bLeague.setBackground(Color.GRAY.brighter());
            c.ipActuelle = c.ips[1];
            c.resetStat();
        }
        if (e.getSource().equals(textIP) && textIP.isFocusOwner()) {
            bOverwatch.setBackground(Color.GRAY.brighter());
            bCounterStrike.setBackground(Color.GRAY.brighter());
            bLeague.setBackground(Color.GRAY.brighter());
            c.ipActuelle = textIP.getText();
            c.resetStat();
        }
        textIP.setText(c.ipActuelle);
    }
}
