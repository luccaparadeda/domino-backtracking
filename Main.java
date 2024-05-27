import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            int numberOfDominos = scanner.nextInt();
            ArrayList<Domino> dominos = new ArrayList<>();
            for (int i = 0; i < numberOfDominos; i++) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                dominos.add(new Domino(left, right));
            }
            boolean[] used = new boolean[numberOfDominos];
            ArrayList<Domino> chain = new ArrayList<>();
            if (!backtrack(dominos, chain, used)) {
                System.out.println("Sem solução");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public static boolean backtrack(ArrayList<Domino> dominoes, ArrayList<Domino> chain, boolean[] used) {
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
}