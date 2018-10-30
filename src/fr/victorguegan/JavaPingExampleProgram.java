package fr.victorguegan;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaPingExampleProgram
{

    public static void main(String args[]) throws IOException
    {
        JavaPingExampleProgram ping = new JavaPingExampleProgram();
        ping.getPing("104.160.141.3");
    }

    public void getPing(String adresse) throws IOException
    {
        List<String> commands = new ArrayList<>();
        commands.add("ping");
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
                System.out.println(matcher.group(0));
            } else {
                System.out.println("Found ? "+matcher.find()+" \n"+s);
            }

        }

        if (stdError.readLine() != null) {
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        }
    }
}
