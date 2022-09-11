package Second.src;

public abstract class Expression {
    @Override
    public abstract boolean equals(Object obj);
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public abstract String toString();
}
