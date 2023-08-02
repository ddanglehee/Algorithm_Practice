import java.util.*;
import java.io.*;

public class Boj1918 {

    static String expression;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        expression = br.readLine();
        System.out.print(postfix());
    }

    private static String postfix() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Character> operatorStack = new ArrayList<>();

        for (char c: expression.toCharArray()) {
            if (c == '(') {
                operatorStack.add(c);
            } else if (c == '+' || c == '-' ) {
                while (!operatorStack.isEmpty() && operatorStack.get(operatorStack.size() - 1) != '(') {
                    sb.append(operatorStack.remove(operatorStack.size() - 1));
                }
                operatorStack.add(c);
            } else if (c == '*' || c == '/') {
                while (!operatorStack.isEmpty() && (operatorStack.get(operatorStack.size() - 1) == '*' || operatorStack.get(operatorStack.size() - 1) == '/')) {
                    sb.append(operatorStack.remove(operatorStack.size() - 1));
                }
                operatorStack.add(c);
            } else if (c == ')') {
                while (operatorStack.get(operatorStack.size() - 1) != '(') {
                    sb.append(operatorStack.remove(operatorStack.size() - 1));
                }
                operatorStack.remove(operatorStack.size() - 1);
            } else {
                sb.append(c);
            }
        }

        while (!operatorStack.isEmpty()) {
            sb.append(operatorStack.remove(operatorStack.size() - 1));
        }

        return sb.toString();
    }
}
