import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.SourceDataLine;

public class MiltonIO {

    public static void main(String[] args) {
        
        System.out.printf("Processing %s...\n", args[0]);

    // Open and read the file
    // Read the first 100 lines and print out each line
    // Close the file when done
    // If the file is less than 100 lines, close when done

    try {
        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);

        String line;
        int counter = 0;
        int totalWords = 0;
        int uniqueWords = 0;
        int wordsWithOneCount = 0;
        Map<String, Integer> wordCount = new HashMap<>();
        // Use the interface type Map instead of HashMap (use the abstract type)
        // When declaring variables, don't use the concrete type (i.e. HashMap)
        
        // Read file and add to Map
        while(null != (line = br.readLine())) {
            
            String[] splitString = line.toLowerCase().replace("-", "").replace(",", " ").trim().split(" ");
            for(int i = 0; i < splitString.length; i++) {

                // int v = wordCount.getOrDefault(splitString[i], 0);
                // v++;
                // wordCount.put(splitString[i], v);

                splitString[i] = splitString[i].strip().replace(",", "");
                if(wordCount.containsKey(splitString[i]) && splitString[i] != "" && splitString[i] != "\t") {
                    wordCount.put(splitString[i], wordCount.get(splitString[i]) + 1);
                    continue;
                    
                }
                wordCount.put(splitString[i], 1);

            }

            uniqueWords = wordCount.size();
            
            totalWords += splitString.length;
            counter++;

            if(counter == 100) {
                break;
            }

        }

        wordCount.remove("");

        // Write to file
        FileWriter fw = new FileWriter(args[1], false);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write("word,count\n");

        for(String item : wordCount.keySet()) {

            if(wordCount.get(item) == 1) {
                wordsWithOneCount++;
            }

            String outputLine = String.format("%s,%d\n", item, wordCount.get(item));
            try {

                bfw.write(outputLine);
                bfw.flush();
    
            } catch (IOException e) {
                System.out.println("Unable to write: " + e.getLocalizedMessage());
                
        }

        
            //System.out.println(item + ": " + wordCount.get(item));
        }
        uniqueWords = wordCount.size();
        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Words with one count: " + wordsWithOneCount);
        System.out.println("Total words: " + totalWords);
        br.close();
        fw.close();

        fr.close();
        bfw.close();


    } catch (IOException e) {
        e.printStackTrace();
    }

    
    }




    
}
