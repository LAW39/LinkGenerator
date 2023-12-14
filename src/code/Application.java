package code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> URLsAsStrings = new ArrayList<>();
        int i;

        try {
            URLsAsStrings = Files.readAllLines(Paths.get("input.txt"));
        }catch (IOException exception){
            System.err.println("Error loading in from file input.txt");
            System.err.println(exception.getMessage());
        }
        List<URLAndLinksClass> urlandLinks = new ArrayList<>();


        i=0;
        System.out.println("Generating URL Classes");
        for (String url:URLsAsStrings) {
            i++;
            urlandLinks.add(new URLAndLinksClass(url));
            System.out.println(i + "/" + URLsAsStrings.size());
        }

        i=0;
        System.out.println("Downloading HTML");
        for (URLAndLinksClass urlAndLink:urlandLinks) {
            i++;
            try {
                urlAndLink.downloadHTML();
                System.out.println(i + "/" + urlandLinks.size());
            }catch (IOException exception){
                System.err.println("Error with downloading URL");
                System.err.println(urlAndLink.getUrl());
                System.err.println(exception.getMessage());
                System.err.println(i + "/" + urlandLinks.size());
            }
        }

        i=0;
        System.out.println("Generating Matches");
        for (URLAndLinksClass urlAndLink:urlandLinks) {
            i++;
            urlAndLink.generateMatches();
            System.out.println(i + "/" + urlandLinks.size());
        }

        i=0;
        System.out.println("Generating output");
        String output;

        StringBuilder sb = new StringBuilder();
        sb.append("url,links\n");
        for (URLAndLinksClass urlAndLink:urlandLinks) {
            i++;
            sb.append(urlAndLink);
            System.out.println(i + "/" + urlandLinks.size());
        }
        output = sb.toString();

        System.out.println("Writing output");
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"));
            writer.write(output);

            writer.close();
        } catch (IOException exception){
            System.err.println("There was an error writing out to file");
            System.err.println(exception.getMessage());
        }

        System.out.println("All done :)");

    }
}
