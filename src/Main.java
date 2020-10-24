import java.io.File; 
import java.util.Scanner; 
import java.util.*;  
import java.util.stream.*;

/**
 * Checking password strength
 * 
 * Seeing if it is in a dictionary
 * Seeing if it is in a compromised passwords list
 * Entropy
 * Uniqueness of characters in password
 * Certain number of charcters
 * Letters (upper and lower case), numbers, special characters
 * Keyboard distance
 * Letters or numbers in a sequence
 * Common numbers that look like letters
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

    /**
     * Calculates the uniqueness of a word
     * The first time a letter is seen it gets a score of 1
     * The next time that letter is seen the score is multiplied by 2
     * Multiply the score for every letter in the word and divide by the length of the word
     * @param password the password
     * @return the uniqueness score of the password
     */
    private static double uniqueness(String password) {
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();

        for (char character : password.toCharArray()) {
            
            if (map.containsKey(character)) {
                map.replace(character, map.get(character) * 2);
            } else {
                map.put(character, 1);
            }

        }

        Stream<Integer> values = map.values().stream();
        double penalty = values.reduce(1, (x, y) -> x * y);

        return (penalty - 1) / (double) password.length();
    }

    /**
     * 
     * @param password
     */
    private static double keyboardDistance(String password) {
        HashMap<Character,Pair<Integer,Integer>> map = new HashMap<Character,Pair<Integer,Integer>>();

        // Characters on the first row of keyboard
        map.put('`', Pair(0,0));
        map.put('~', Pair(0,0));
        map.put('1', Pair(1,0));
        map.put('2', Pair(2,0));
        map.put('3', Pair(3,0));
        map.put('4', Pair(4,0));
        map.put('5', Pair(5,0));
        map.put('6', Pair(6,0));
        map.put('7', Pair(7,0));
        map.put('8', Pair(8,0));
        map.put('9', Pair(9,0));
        map.put('0', Pair(10,0));
        map.put('-', Pair(11,0));
        map.put('-', Pair(11,0));
        map.put('+', Pair(12,0));
        map.put('=', Pair(12,0));

        // Characters on the second row of keyboard
        map.put('q', Pair(0,1));
        map.put('w', Pair(1,1));
        map.put('e', Pair(2,1));
        map.put('r', Pair(3,1));
        map.put('t', Pair(4,1));
        map.put('y', Pair(5,1));
        map.put('u', Pair(6,1));
        map.put('i', Pair(7,1));
        map.put('o', Pair(8,1));
        map.put('p', Pair(9,1));
        map.put('{', Pair(10,1));
        map.put('[', Pair(10,1));
        map.put('}', Pair(11,1));
        map.put(']', Pair(11,1));
        map.put('\\', Pair(12,1));
        map.put('|', Pair(12,1));

        // Characters on the second row of keyboard
        map.put('a', Pair(0,2));
        map.put('s', Pair(1,2));
        map.put('d', Pair(2,2));
        map.put('f', Pair(3,2));
        map.put('g', Pair(4,2));
        map.put('h', Pair(5,2));
        map.put('j', Pair(6,2));
        map.put('k', Pair(7,2));
        map.put('l', Pair(8,2));
        map.put(';', Pair(9,2));
        map.put(':', Pair(9,2));
        map.put('\"', Pair(10,2));
        map.put('\'', Pair(10,2));

        // Characters on the third row of keyboard
        map.put('z', Pair(0,3));
        map.put('x', Pair(1,3));
        map.put('c', Pair(2,3));
        map.put('v', Pair(3,3));
        map.put('b', Pair(4,3));
        map.put('n', Pair(5,3));
        map.put('m', Pair(6,3));
        map.put(',', Pair(7,3));
        map.put('<', Pair(7,3));
        map.put('.', Pair(8,3));
        map.put('>', Pair(8,3));
        map.put('/', Pair(9,3));
        map.put('?', Pair(9,3));

    }

    public static void main(String args[]) {
        System.out.print("Please Enter Password: ");
        Scanner keyboard = new Scanner(System.in);
        String password = keyboard.nextLine().trim();
        keyboard.close();

        /*try {
            File file = new File("./../text-files/dict.txt");
            Scanner scanner = new Scanner(file);
            int hits = Main.inDict(scanner, password.toLowerCase());
            System.out.println("Dictionary: " + hits);
        } catch (Exception e) {
            System.out.println("Failed to open file!");
            return;
        }*/

        /*try {
            File file = new File("./../text-files/common-passwords.txt");
            Scanner scanner = new Scanner(file);
            int hits = Main.inDict(scanner, password.toLowerCase());
            System.out.println("Common: " + hits);
        } catch (Exception e) {
            System.out.println("Failed to open file!");
            return;
        }*/

        /* double entropy = entropy(password.length());
        System.out.println("Entropy: " + entropy);*/

        double uniquenessScore = Main.uniqueness(password);
        System.out.println(uniquenessScore);

        System.out.println("Password Strength: " + "");


    }

}


