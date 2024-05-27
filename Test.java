import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static boolean backtrack(List<Domino> dominoes, List<Domino> chain, boolean[] used) {
        if (chain.size() == dominoes.size()) {
            System.out.println(chain);
            return true;
        }
        for (int i = 0; i < dominoes.size(); i++) {
            Domino domino = dominoes.get(i);
            if (!used[i] && (chain.isEmpty() || domino.left == chain.get(chain.size() - 1).right)) {
                used[i] = true;
                chain.add(domino);
                if (backtrack(dominoes, chain, used)) {
                    return true;
                }
                chain.remove(chain.size() - 1);
                used[i] = false;
            } else if (!used[i] && domino.right == chain.get(chain.size() - 1).right) {
                used[i] = true;
                chain.add(new Domino(domino.right, domino.left));
                if (backtrack(dominoes, chain, used)) {
                    return true;
                }
                chain.remove(chain.size() - 1);
                used[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            File file = new File("caso24.txt");
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            List<Domino> dominoes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                dominoes.add(new Domino(left, right));
            }
            boolean[] used = new boolean[n];
            if (!backtrack(dominoes, new ArrayList<>(), used)) {
                System.out.println("No solution");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}