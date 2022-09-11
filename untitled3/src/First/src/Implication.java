package First.src;

public class Implication extends BinaryOperation {
    public Implication(Expression left, Expression right) {
        super(left, right, (x, y) ->
                Boolean.logicalOr(!x, y));
    }
}
