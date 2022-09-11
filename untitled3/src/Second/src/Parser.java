package Second.src;

public class Parser {
    private final String s;
    private int index;

    private String nextToken;
    public Parser(String s) {
        this.s = s;
        index = 0;
        nextToken = next();
    }

    public Expression parse() {
        return parseImplication();
    }


    private Expression parseUnary() {
        String token = getNextToken();
        switch (token) {
            case "!":
                return new Not(parseUnary());
            case "(":
                Expression expression = parseImplication();
                test(")");
                return expression;
            default:
                return new Variable(token);
        }

    }

    private Expression parseAnd() {
        Expression left = parseUnary();
        while (test("&")) {
            left = new And(left, parseUnary());
        }
        return left;
    }

    private Expression parseOr() {
        Expression left = parseAnd();
        while (test("|")) {
            left = new Or(left, parseAnd());
        }
        return left;
    }
    private Expression parseImplication() {
        Expression left = parseOr();
        while (test("->")) {
            left = new Implication(left, parseImplication());
        }
        return left;
    }

    private boolean test(String s) {
        if (s.equals(nextToken)) {
            nextToken = next();
            return true;
        }
        return false;
    }

    private String getNextToken() {
        String buf = nextToken;
        nextToken = next();
        return buf;
    }

    private String next() {
        StringBuilder token = new StringBuilder();
        char ch = getChar();
        if (ch == 0) {
            return null;
        }
        if (ch == '&' || ch == '|' || ch == '!' || ch == '(' || ch == ')') {
            return String.valueOf(ch);
        }

        if (ch == '-') {
            getChar();
            return "->";
        }
        token.append(ch);
        while (nextChar() != '&' && nextChar() != '|' && nextChar() != '!'
                && nextChar() != '-' && nextChar() != '(' && nextChar() != ')' && nextChar() != 0) {
            token.append(getChar());
        }
        return token.toString();
    }
    private char getChar() {
        char ch = nextChar();
        index++;
        return ch;
    }

    private char nextChar() {
        return index == s.length() ? 0 : s.charAt(index);
    }
}
