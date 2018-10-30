package fr.victorguegan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Controler {

    boolean en_route = true;

    private int nb_iteration = 0;

    double moyenne = -1;
    double max = -1;
    double min = -1;

    double last_ping = -1;

    double getPing(String adresse) throws IOException
    {
        List<String> commands = new ArrayList<>();

        commands.add("ping");
        if (System.getProperty("os.name").contains("Linux")) {
            commands.add("-c 1");
        } else {
            commands.add("-t 1");
        }
        commands.add(adresse);

        String s;

        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        System.out.println("Here is the standard output of the command:\n");
        while ((s = stdInput.readLine()) != null) {
            Pattern pattern = Pattern.compile("[tempsi]{4,5}=[0-9]+(\\.[0-9])?");
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                String set = matcher.group(0);
                return Double.parseDouble(set.split("=")[1]);
            }

        }

        return -1;
    }

    void majStat (double ping) {
        nb_iteration++;
        last_ping = ping;
        if (ping != -1) {
            if (moyenne == -1) {
                moyenne = min = max = ping;
            } else {
                if (min > ping) min = ping;
                if (max < ping) max = ping;
                moyenne -= moyenne / nb_iteration;
                moyenne += ping / nb_iteration;
            }
        }
    }

    @Override
    public String toString() {
        return "Ping : "+ last_ping + " Min :" + min + " Moy : "+ moyenne + " Max : "+ max;
    }
}
