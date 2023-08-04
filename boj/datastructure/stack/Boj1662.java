import java.util.*;
import java.io.*;

public class Boj1662 {

    static String string;
    static int index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        string = br.readLine();

        System.out.print(solution());
    }

    private static int solution() {
        int length = 0;
        ArrayList<Character> stack = new ArrayList<>();

        while (index < string.length()) {
            char c = string.charAt(index);

            if (c == '(') {
                index++;
                length += (stack.remove(stack.size() - 1) - '0') * solution();
            } else if (c == ')') {
                return length + stack.size();
            } else {
                stack.add(c);
            }

            index++;
        }

        length += stack.size();

        return length;
    }
}
