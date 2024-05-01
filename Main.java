import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String fileName = "./test.txt";
        ArrayList<int[]> linesArray = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstLine = true;
            int numberOfDominos = 0;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    numberOfDominos = Integer.parseInt(line);
                    continue;
                }
                String[] words = line.split(" ");
                int[] domino = new int[2];
                domino[0] = Integer.parseInt(words[0]);
                domino[1] = Integer.parseInt(words[1]);
                linesArray.add(domino);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Dominos: ");
        for (int[] words : linesArray) {
            System.out.println(Arrays.toString(words));
        }
        ArrayList<Integer> currentSolution = new ArrayList<>();
        System.out.println(solveDomino(linesArray, currentSolution));
    }

    public static String solveDomino(ArrayList<int[]> dominos, ArrayList<Integer> currentSolution) {
        if (dominos.size() == 0) {
            return currentSolution.toString();
        }

        for (int i = 0; i < dominos.size(); i++) {
            int[] domino = dominos.get(i);
            if (currentSolution.size() == 0) {
                currentSolution.add(0, domino[0]);
                currentSolution.add(1, domino[1]);
                dominos.remove(i);
                solveDomino(dominos, currentSolution);
            } else {
                int lastNumber = currentSolution.get(currentSolution.size() - 1);
                int firstNumber = currentSolution.get(0);
                if (domino[0] == firstNumber) {
                    currentSolution.add(0, domino[0]);
                    currentSolution.add(0, domino[1]);
                    dominos.remove(i);
                    solveDomino(dominos, currentSolution);
                } else if (domino[1] == firstNumber) {
                    currentSolution.add(0, domino[1]);
                    currentSolution.add(0, domino[0]);
                    dominos.remove(i);
                    solveDomino(dominos, currentSolution);
                } else if (domino[0] == lastNumber) {
                    currentSolution.add(currentSolution.size(), domino[0]);
                    currentSolution.add(currentSolution.size(), domino[1]);
                    dominos.remove(i);
                    solveDomino(dominos, currentSolution);
                } else if (domino[1] == lastNumber) {
                    currentSolution.add(currentSolution.size(), domino[1]);
                    currentSolution.add(currentSolution.size(), domino[0]);
                    dominos.remove(i);
                    solveDomino(dominos, currentSolution);
                }
            }
        }

        return currentSolution.toString();
    }
}
