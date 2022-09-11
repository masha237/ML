package Second.src;

public class Implication extends BinaryOperation {
    public Implication(Expression left, Expression right) {
        super(left, right, "->");
    }
}
