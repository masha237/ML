package First.src;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private final String s;
    private int index;

    private final Map<String, Integer> map = new HashMap<>();
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
                if (!map.containsKey(token)) {
                    map.put(token, map.size());
                }
                return new Variable(map.getOrDefault(token, null));
        }

    }

    private Expression parseAnd() {
        Expression left = parseUnary();
        while (test("&")) {
            left = new And(left, parseAnd());
        }
        return left;
    }

    private Expression parseOr() {
        Expression left = parseAnd();
        while (test("|")) {
            left = new Or(left, parseOr());
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

    public int getVariables() {
        return map.size();
    }
}
