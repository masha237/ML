package Second.src;

import java.io.*;
import java.util.*;

public class Second {
    private final List<String> hypothesis = new ArrayList<>();
    private final Set<Expression> process = new HashSet<>();
    private String statement = "", question = "";
    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            solve();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void solve() throws IOException {
        String begin = nextLine();

        String s;
        parse(begin);
        List<String> proof = new ArrayList<>();
        while ((s = nextLine()) != null && !s.isEmpty()) {
            proof.add(s);
        }

        for (int i = 0; i < hypothesis.size(); i++) {
            System.out.print(hypothesis.get(i));
            if (i + 1 != hypothesis.size()) {
                System.out.print(",");
            }
        }

        Parser parserS = new Parser(statement);
        Expression statementExpression = parserS.parse();
        Parser parserq = new Parser(question);
        Expression expressionQ = parserq.parse();
        
        
        System.out.println("|-" + new Implication(statementExpression, expressionQ));


        for (String current : proof) {
            Parser parser = new Parser(simple(current));
            Expression expression = parser.parse();
            Expression left;
            if (expression.equals(statementExpression)) {
                Expression x = new Implication(expression, new Implication(expression, expression));
                Expression y = new Implication(expression, new Implication(new Implication(expression, expression), expression));
                Expression z = new Implication(expression, expression);
                System.out.println(x);
                System.out.println(y);
                System.out.println(new Implication(x, new Implication(y, z)));
                System.out.println(new Implication(y, z));
                System.out.println(z);

            } else if ((left = check(expression)) != null) {
                Implication end = new Implication(statementExpression, expression);
                Implication first = new Implication(statementExpression, left);
                System.out.println(new Implication(first,
                                    new Implication(new Implication(statementExpression, new Implication(left, expression)),
                                                    end)));
                System.out.println(new Implication(new Implication(statementExpression, new Implication(left, expression)), end));
                System.out.println(end);
            } else {
                System.out.println(expression);
                System.out.println(new Implication(expression, new Implication(statementExpression, expression)));
                System.out.println(new Implication(statementExpression, expression));
            }
            process.add(expression);
        }

    }
        private String simple(String current) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < current.length(); i++) {
            if (Character.isWhitespace(current.charAt(i)) || current.charAt(i) == '\r') {
                continue;
            }
            stringBuilder.append(current.charAt(i));
        }
        return stringBuilder.toString();
    }

    private void parse(String s) {
        String[] subStr = new String[2];

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '|' && s.charAt(i + 1) == '-') {
                subStr[0] = s.substring(0, i);
                subStr[1] = s.substring(i + 2);
            }
        }

        question = simple(subStr[1]);
        subStr = subStr[0].split(",");
        statement = simple(subStr[subStr.length - 1]);

        for (int i = 0; i + 1 < subStr.length; i++) {
            String kek = simple(subStr[i]);
            hypothesis.add(kek);
        }
    }

    private Expression check(Expression current) {
        for (Expression i : process) {
            if (!(i instanceof Implication)) {
                continue;
            }
            Implication implication = (Implication) i;
            if (!implication.getRight().equals(current)) {
                continue;
            }
            if (process.contains(implication.getLeft())) {
                return implication.getLeft();
            }
        }
        return null;
    }

    BufferedReader br;
    PrintWriter out;

    public String nextLine() throws IOException {
        return br.readLine();
    }

    public static void main(String[] args) {
        new Second().run();
    }
}
