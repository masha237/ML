package Second.src;

public class Not extends Expression {
    private final Expression expression;

    public Not(Expression expression) {
        this.expression = expression;
    }

    public String toString() {
        return "(!" + expression.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Not)) {
            return false;
        }
        return ((Not) obj).expression.equals(expression);
    }
}
