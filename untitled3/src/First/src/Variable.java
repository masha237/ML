package First.src;

public class Variable extends Expression {
    private final Integer number;

    public Variable(Integer number) {
        this.number = number;
    }

    @Override
    public boolean evaluate(boolean[] x) {
        return x[number];
    }
}
