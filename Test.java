import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static boolean backtrack(List<Domino> dominoes, List<Domino> chain) {
        if (dominoes.isEmpty()) {
            System.out.println(chain);
            return true;
        }
        for (Domino domino : new ArrayList<>(dominoes)) {
            if (chain.isEmpty() || domino.left == chain.get(chain.size() - 1).right) {
                dominoes.remove(domino);
                chain.add(domino);
                if (backtrack(dominoes, chain)) {
                    return true;
                }
                chain.remove(chain.size() - 1);
                dominoes.add(domino);
            } else if (domino.right == chain.get(chain.size() - 1).right) {
                dominoes.remove(domino);
                chain.add(new Domino(domino.right, domino.left));
                if (backtrack(dominoes, chain)) {
                    return true;
                }
                chain.remove(chain.size() - 1);
                dominoes.add(domino);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            File file = new File("test.txt");
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            List<Domino> dominoes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                dominoes.add(new Domino(left, right));
            }
            for (Domino domino : new ArrayList<>(dominoes)) {
                dominoes.remove(domino);
                if (backtrack(dominoes, new ArrayList<>(Arrays.asList(domino)))) {
                    return;
                }
                dominoes.add(domino);
            }
            System.out.println("No solution");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}