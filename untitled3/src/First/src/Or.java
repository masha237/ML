package First.src;

public class Or extends BinaryOperation {
    public Or(Expression left, Expression right) {
        super(left, right, Boolean::logicalOr);
    }
}
