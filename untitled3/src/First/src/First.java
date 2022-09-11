package First.src;

import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        in.close();


        Parser parser = new Parser(s);
        Expression parsed = parser.parse();
        int pos = 0, neg = 0;
        for (int i = 0; i < (1 << parser.getVariables()); i++) {
                if (parsed.evaluate(i)) {
                    pos++;
                } else {
                    neg++;
                }
        }
        if (pos == 0) {
            System.out.println("Unsatisfiable");
        } else if (neg == 0) {
            System.out.println("Valid");
        } else  {
            System.out.println("Satisfiable and invalid, " + pos + " true and " + neg + " false cases");
        }
    }
}
