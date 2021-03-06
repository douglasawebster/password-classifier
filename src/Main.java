import java.io.File; 
import java.io.FileWriter;
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
 * Alphabet coverage
 * Keyboard distance
 * Certain number of charcters
 * Letters (upper and lower case), numbers, special characters
 * Letters or numbers in a sequence
 * Common numbers that look like letters
 * GOOD SET OF CRITERIA: http://www.passwordmeter.com
 * Password generator: https://passwordsgenerator.net
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
    private static double inDict(Scanner scanner, String password) {
        double hits = 0.0;

        while(scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();

            if(password.contains(word)) {
                double percentageOfPassword = (double)word.length() / (double)password.length();
                hits += Math.pow(4.0, 1.0 + percentageOfPassword);
            }

            if(password.equals(word)) {
                return 100.0;
            }
        }

        return hits;
    }

    /**
     * Alphabet coverage is defined as the number of unique characters divided by the total characters in the alphabet
     * @param password the password
     * @return the pecentage of the total characters in the alphabet covered by the password
     */
    private static double alphabetCoverage(String password) {
        ArrayList<Character> characters = new ArrayList<Character>();

        for(char c: password.toCharArray()) {
            if(!characters.contains(c)) {
                characters.add(c);
            }
        }

        return ((double)characters.size()) / 94.0;
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
     * The distance between strings in the password
     * @param password the password
     * @return the distance between each letter and the letter to the left and right
     */
    private static double keyboardDistance(String password) {
        HashMap<Character,Integer[]> map = new HashMap<Character,Integer[]>();

        // Characters on the first row of keyboard
        map.put('`', new Integer[] {0,0});
        map.put('~', new Integer[] {0,0});
        map.put('1', new Integer[] {1,0});
        map.put('!', new Integer[] {1,0});
        map.put('2', new Integer[] {2,0});
        map.put('@', new Integer[] {2,0});
        map.put('3', new Integer[] {3,0});
        map.put('#', new Integer[] {3,0});
        map.put('4', new Integer[] {4,0});
        map.put('$', new Integer[] {4,0});
        map.put('5', new Integer[] {5,0});
        map.put('%', new Integer[] {5,0});
        map.put('6', new Integer[] {6,0});
        map.put('^', new Integer[] {6,0});
        map.put('7', new Integer[] {7,0});
        map.put('&', new Integer[] {7,0});
        map.put('8', new Integer[] {8,0});
        map.put('*', new Integer[] {8,0});
        map.put('9', new Integer[] {9,0});
        map.put('(', new Integer[] {9,0});
        map.put('0', new Integer[] {10,0});
        map.put(')', new Integer[] {10,0});
        map.put('-', new Integer[] {11,0});
        map.put('_', new Integer[] {11,0});
        map.put('+', new Integer[] {12,0});
        map.put('=', new Integer[] {12,0});

        // Characters on the second row of keyboard
        map.put('q', new Integer[] {0,1});
        map.put('w', new Integer[] {1,1});
        map.put('e', new Integer[] {2,1});
        map.put('r', new Integer[] {3,1});
        map.put('t', new Integer[] {4,1});
        map.put('y', new Integer[] {5,1});
        map.put('u', new Integer[] {6,1});
        map.put('i', new Integer[] {7,1});
        map.put('o', new Integer[] {8,1});
        map.put('p', new Integer[] {9,1});
        map.put('{', new Integer[] {10,1});
        map.put('[', new Integer[] {10,1});
        map.put('}', new Integer[] {11,1});
        map.put(']', new Integer[] {11,1});
        map.put('\\', new Integer[] {12,1});
        map.put('|', new Integer[] {12,1});

        // Characters on the second row of keyboard
        map.put('a', new Integer[] {0,2});
        map.put('s', new Integer[] {1,2});
        map.put('d', new Integer[] {2,2});
        map.put('f', new Integer[] {3,2});
        map.put('g', new Integer[] {4,2});
        map.put('h', new Integer[] {5,2});
        map.put('j', new Integer[] {6,2});
        map.put('k', new Integer[] {7,2});
        map.put('l', new Integer[] {8,2});
        map.put(';', new Integer[] {9,2});
        map.put(':', new Integer[] {9,2});
        map.put('\"', new Integer[] {10,2});
        map.put('\'', new Integer[] {10,2});

        // Characters on the third row of keyboard
        map.put('z', new Integer[] {0,3});
        map.put('x', new Integer[] {1,3});
        map.put('c', new Integer[] {2,3});
        map.put('v', new Integer[] {3,3});
        map.put('b', new Integer[] {4,3});
        map.put('n', new Integer[] {5,3});
        map.put('m', new Integer[] {6,3});
        map.put(',', new Integer[] {7,3});
        map.put('<', new Integer[] {7,3});
        map.put('.', new Integer[] {8,3});
        map.put('>', new Integer[] {8,3});
        map.put('/', new Integer[] {9,3});
        map.put('?', new Integer[] {9,3});

        char[] lowerPassword = password.toLowerCase().toCharArray();

        int manhattanDistance = 0;
        for(int i = 0; i < lowerPassword.length; i++) {

            Integer[] currCoordinate = map.get(lowerPassword[i]);

            if((i - 1) >= 0) {
                Integer[] leftCoordinate = map.get(lowerPassword[i-1]);
                
                int xDist = 0; 
                int yDist = 0;
                try {
                    xDist = Math.abs(currCoordinate[0] - leftCoordinate[0]);
                    yDist = Math.abs(currCoordinate[1] - leftCoordinate[1]);
                } catch(Exception e) {
                    System.out.println(lowerPassword);
                }

                manhattanDistance += xDist + yDist;
            }

            if((i + 1) < lowerPassword.length) {
                Integer[] rightCoordinate = map.get(lowerPassword[i+1]);

                int xDist = 0; 
                int yDist = 0;
                try {
                    xDist = Math.abs(currCoordinate[0] - rightCoordinate[0]);
                    yDist = Math.abs(currCoordinate[1] - rightCoordinate[1]);
                } catch(Exception e) {
                    System.out.println(lowerPassword);
                }
                
                manhattanDistance += xDist + yDist;
            }

        }

        return (double)manhattanDistance;

    }

    public static void main(String args[]) {

        System.out.print("Please Enter Password: ");
        Scanner keyboard = new Scanner(System.in);
        String password = keyboard.nextLine().trim();
        keyboard.close();

        boolean blacklistFlag = false;
        try {
            File file = new File("./../text-files/common-passwords.txt");
            Scanner scanner = new Scanner(file);
            blacklistFlag = Main.inDict(scanner, password.toLowerCase()) == 100 ? true : false;

            if(blacklistFlag) {
                System.out.println("weak");
                return;
            }

        } catch (Exception e) {
            System.out.println("Failed to open file!");
            return;
        }

        double dictHits = 0;
        try {
            File file = new File("./../text-files/dict.txt");
            Scanner scanner = new Scanner(file);
            dictHits = Main.inDict(scanner, password.toLowerCase());
        } catch (Exception e) {
            System.out.println("Failed to open file!");
            return;
        }

        double reverseDictHits = 0;
        try {
            File file = new File("./../text-files/dict.txt");
            Scanner scanner = new Scanner(file);
            String reversedPassword = new StringBuilder(password).reverse().toString();
            reverseDictHits = Main.inDict(scanner, reversedPassword.toLowerCase());
        } catch (Exception e) {
            System.out.println("Failed to open file!");
            return;
        }

        double entropy = entropy(password.length());

        double uniquenessScore = Main.uniqueness(password);

        double keyboardDistance = Main.keyboardDistance(password);

        double alphabetCoverage = alphabetCoverage(password);

        String output = dictHits + " ";
        output += reverseDictHits + " ";
        output += entropy + " ";
        output += alphabetCoverage + " ";
        output += uniquenessScore + " ";
        output += keyboardDistance;

        System.out.println(output);

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./temp.txt");
            fileWriter.write(output);
            fileWriter.close();
        } catch(Exception e) {
            return;
        }

        ProcessBuilder pb = new ProcessBuilder("logistic-regression.py");
		pb.directory(new File(cwd));
		pb.redirectOutput(new File (cwd + "/results.txt"));
		Process process = pb.start();
		try {
			System.out.println("Reached");
			process.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");

    }

}


