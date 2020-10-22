import java.io.File; 
import java.util.Scanner; 

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
 * GOOD SET OF CRITERIA: http://www.passwordmeter.com
 */

public class Main {

    /**
     * Iterates through a file checking for words in the file that 
     * are substrings of the password.
     * If a word in the file is a substring of the password count the occurance
     * If a word in the file matches exactly the password return -1
     * @param scanner the scanner used to iterate through the file
     * @param password the password
     * @return a measurement of how many words appear as substrings of the password
     */
    private static int inDict(Scanner scanner, String password) {
        int hits = 0;

        while(scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();

            if(password.contains(word)) {
                hits += 1;
            }

            if(password.equals(word)) {
                return -1;
            }
        }

        return hits;
    }

    /**
     * Calculates the entropy of the password
     * Entropy = log_2(alphabet-size^password-length)
     * @param passwordLength the length of the password
     * @return the entropy of the password
     */
    private static double entropy(int passwordLength) {
        // Inlcluding (a-z), (A-Z), (0-9), (symbols/special characters)
        int totalCharacter = 94;

        return passwordLength * (Math.log10(totalCharacter) / Math.log10(2));
    }

    public static void main(String args[]) {
        System.out.print("Please Enter Password: ");
        Scanner keyboard = new Scanner(System.in);
        String password = keyboard.nextLine().trim();

        try {
            File file = new File("./../text-files/dict.txt");
            Scanner scanner = new Scanner(file);
            int hits = Main.inDict(scanner, password.toLowerCase());
            System.out.println("Dictionary: " + hits);
        } catch (Exception e) {
            System.out.println("Failed to open file!");
            return;
        }

        try {
            File file = new File("./../text-files/common-passwords.txt");
            Scanner scanner = new Scanner(file);
            int hits = Main.inDict(scanner, password.toLowerCase());
            System.out.println("Common: " + hits);
        } catch (Exception e) {
            System.out.println("Failed to open file!");
            return;
        }

        double entropy = entropy(password.length());

        System.out.println("Entropy: " + entropy);

        System.out.println("Password Strength: " + "");


    }

}


