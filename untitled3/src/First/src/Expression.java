package First.src;

public abstract class Expression {
    public abstract boolean evaluate (boolean[] x);

    public boolean evaluate (int x) {
        boolean[] i = new boolean[16];
        for (int j = 0; j < 16; j++) {
            if ((x & (1 << j)) != 0) {
                i[j] = true;
            }
        }
        return evaluate(i);
    }



}
