package Second.src;

public class Variable extends Expression {

    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) {
            return false;
        }
        return ((Variable) obj).name.equals(name);
    }

    public String toString() {
        return name;
    }
}
