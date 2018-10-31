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

    String[] ips = { "104.160.141.3", "185.60.112.157", "146.66.152.250" };

    private int nb_iteration = 0;

    String ipActuelle = ips[0];

    double moyenne = -1;
    double max = -1;
    double min = -1;

    double last_ping = 0;



    double getPing() throws IOException
    {
        List<String> commands = new ArrayList<>();
        commands.add("ping");
        commands.add(ipActuelle);
        if (System.getProperty("os.name").contains("Linux")) {
            commands.add("-c 1");
        } else if (System.getProperty("os.name").contains("Windows")) {
            commands.add("-n");
            commands.add("1");
        }
        String s;

        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        while ((s = stdInput.readLine()) != null) {
            Pattern pattern = Pattern.compile("(temps|time)[<=>][0-9]+(\\.[0-9])?");
            Matcher matcher = pattern.matcher(s);
            if (stdError.readLine() != null) {
                return -2;
            }
            if (matcher.find()) {
                String set = matcher.group(0);
                process.destroy();
                return Double.parseDouble(set.split("[<=>]")[1]);
            }
        }
        process.destroy();
        return -1;
    }

    void resetStat() {
        this.moyenne = -1;
        this.max = -1;
        this.min = -1;
    }

    void majStat (double ping) {
        nb_iteration++;
        last_ping = ping;
        if (ping > 0) {
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
        return "Ping vers "+ipActuelle+" : "+ last_ping + " Min : " + min + " Moy : "+ String.format("%.2f",moyenne) + " Max : "+ max;
    }
}
