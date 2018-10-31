package fr.victorguegan;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {
        double ping = 0;
        Controler controleur = new Controler();
        GUI fenetre = new GUI(controleur);
        while (controleur.en_route) {
            fenetre.updateGUI(ping);
            try {
                Thread.sleep(500);
                System.out.println(controleur);
                ping = controleur.getPing();
            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
