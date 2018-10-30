package fr.victorguegan;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        double ping = 0;
        Controler controleur = new Controler();
        GUI fenetre = new GUI();
        while (controleur.en_route) {
            System.out.println(controleur);
            ping = controleur.getPing("104.160.141.3");
            controleur.majStat(ping);
            fenetre.updateGUI(controleur.min, controleur.moyenne, controleur.max, controleur.last_ping);

            Thread.sleep(500);
        }
    }

}
