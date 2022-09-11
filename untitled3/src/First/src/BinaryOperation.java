package First.src;

import java.util.function.BiFunction;

public class BinaryOperation extends Expression {
    private final Expression left;
    private final Expression right;
    private final BiFunction<Boolean, Boolean, Boolean> operation;


    public BinaryOperation(Expression left, Expression right, BiFunction<Boolean, Boolean, Boolean> operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public boolean evaluate(boolean[] x) {
        return operation.apply(left.evaluate(x), right.evaluate(x));
    }
}

