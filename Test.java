import java.io.*;
import java.util.*;

public class Test {
    static ArrayList<int[]> dominos = new ArrayList<>();
    static boolean[] isDominoUsed;
    static int[] dominoSequence;

    public static void main(String[] args) {
        String inputFileName = "./test.txt";

        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName))) {
            String currentLine;
            boolean isFirstLine = true;

            while ((currentLine = fileReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] dominoStrings = currentLine.split(" ");
                int[] domino = new int[2];
                domino[0] = Integer.parseInt(dominoStrings[0]);
                domino[1] = Integer.parseInt(dominoStrings[1]);
                dominos.add(domino);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Dominos: ");
        for (int[] domino : dominos) {
            System.out.println(Arrays.toString(domino));
        }

        validateDominoSequence();
    }

    static boolean isSequenceComplete(int sequenceIndex) {
        if (sequenceIndex == dominoSequence.length) {
            return true;
        }

        for (int i = 0; i < dominos.size(); i++) {
            if (isDominoUsed[i])
                continue;
            if (sequenceIndex == 0 || dominoSequence[sequenceIndex - 1] == dominos.get(i)[0]
                    || dominoSequence[sequenceIndex - 1] == dominos.get(i)[1]) {
                isDominoUsed[i] = true;
                if (sequenceIndex == 0) {
                    dominoSequence[sequenceIndex] = dominos.get(i)[0];
                } else {
                    if (dominos.get(i)[0] == dominoSequence[sequenceIndex - 1]) {
                        dominoSequence[sequenceIndex] = dominos.get(i)[1];
                    } else {
                        dominoSequence[sequenceIndex] = dominos.get(i)[0];
                    }
                }
                if (isSequenceComplete(sequenceIndex + 1))
                    return true;
                isDominoUsed[i] = false;
            }
        }

        return false;
    }

    static void validateDominoSequence() {
        isDominoUsed = new boolean[dominos.size()];
        dominoSequence = new int[dominos.size()];

        if (isSequenceComplete(0)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}