package First.src;

public class And extends BinaryOperation {
    public And(Expression left, Expression right) {
        super(left, right, Boolean::logicalAnd);
    }
}
