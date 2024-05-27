public class Domino {
    int left, right;

    Domino(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "{" + left + " - " + right + "}";
    }
}
