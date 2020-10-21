import java.io.File; 
import java.io.FileWriter;
import java.util.Scanner; 
import java.util.ArrayList;

/**
 * Checking password strength
 * 
 * Seeing if it is in a dictionary
 * Seeing if it is in a compromised passwords list
 * Certain number of charcters
 * Letters (upper and lower case), numbers, special characters
 * Keyboard distance
 * Entropy
 * Letters or numbers in a sequence
 * Common numbers that look like letters
 * Uniqueness of characters in password
 */

public class Main {
    public static void main(String args[]) {

        File file;
        Scanner scanner;
        try {
            file = new File("./apple-dict.txt");
            scanner = new Scanner(file);
        } catch (Exception e) {
            //TODO: handle exception
            return;
        }

        ArrayList<String> wordsToKeep = new ArrayList<String>();
        int ctr = 0;
        while(scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if (word.length() == 2) {
                wordsToKeep.add(word);
                System.out.println(word);
            } else {
                //System.out.println("Removing: " + word);
            }
            ctr++;
        }
        scanner.close();

        System.out.println("Read in: " + ctr);
        System.out.println("Keeping: " + wordsToKeep.size());

        /*try {
            FileWriter fileWriter = new FileWriter("dict.txt");

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
