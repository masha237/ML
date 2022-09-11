package First.src;

public class Not extends Expression {
    private final Expression expression;

    public Not(Expression expression) {
        this.expression = expression;
    }

    @Override
    public boolean evaluate(boolean[] x) {
        return !expression.evaluate(x);
    }
}
