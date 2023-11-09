import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCodeConverter {
    private static MorseCodeTree morseCodeTree = new MorseCodeTree();

    public static String convertToEnglish(String morseCode) {
        StringBuilder englishMessage = new StringBuilder();
        String[] words = morseCode.trim().split(" / ");

        for (String word : words) {
            String[] characters = word.split(" ");
            for (String character : characters) {
                englishMessage.append(morseCodeTree.convertToEnglish(character));
            }
            englishMessage.append(" "); // Space between words
        }

        return englishMessage.toString().trim().toLowerCase();
    }

    // Method to print the data in the Morse code tree
    public static String printTree() {
        return morseCodeTree.printTree();
    }

    // Method to convert Morse code from a file to English
    public static String convertToEnglish(File file) throws FileNotFoundException {
        StringBuilder englishMessage = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                englishMessage.append(convertToEnglish(line)).append(" ");
            }
        }
        return englishMessage.toString().trim().toLowerCase();
    }
}
