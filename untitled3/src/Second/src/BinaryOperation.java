package Second.src;

public class BinaryOperation extends Expression {
    private final Expression left;
    private final Expression right;

    private final String oper;


    public BinaryOperation(Expression left, Expression right, String oper) {
        this.left = left;
        this.right = right;
        this.oper = oper;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }


    public String toString() {
        return "(" + left.toString() + oper + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryOperation)) {
            return false;
        }
        return ((BinaryOperation) obj).left.equals(left) &&
                ((BinaryOperation) obj).right.equals(right) &&
                ((BinaryOperation) obj).oper.equals(oper);
    }
}

