import java.io.File; 
import java.io.FileWriter;
import java.util.Scanner; 
import java.util.ArrayList;

/**
 * This class is to be used to prune apple-dict.txt
 * apple-dict is a text file which contains a list of English words
 * This list of words comes pre-installed on all Unix systems 
 */
public class DictParser {
    public static void main(String args[]) {

        File file;
        Scanner scanner;
        try {
            file = new File("./../text-files/apple-dict.txt");
            scanner = new Scanner(file);
        } catch (Exception e) {
            //TODO: handle exception
            return;
        }

        ArrayList<String> wordsToKeep = new ArrayList<String>();
        Scanner keyboard = new Scanner(System.in);
        int ctr = 0;
        while(scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if (word.length() > 3) {
                wordsToKeep.add(word.toLowerCase());
            } 
            ctr++;
        }
        keyboard.close();
        scanner.close();

        System.out.println("Read in: " + ctr);
        System.out.println("Keeping: " + wordsToKeep.size());

        // Uncomment if you want to write results to file
        /*try {
            FileWriter fileWriter = new FileWriter("./../text-files/dict.txt");

            for(String word: wordsToKeep) {
                fileWriter.write(word + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            //TODO: handle exception
            return;
        }*/

        

    }
}
