import java.io.*;
import java.util.*;

public class Boj17413 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringBuilder sb = new StringBuilder();

        ArrayList<Character> stack = new ArrayList<>();
        boolean isTag = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isTag) {
                sb.append(c);
                if (c == '>') isTag = false;
            } else if (c == ' ') {
                while (!stack.isEmpty()) {
                    sb.append(stack.remove(stack.size() - 1));
                }
                sb.append(" ");
            } else if (c == '<') {
                while (!stack.isEmpty()) {
                    sb.append(stack.remove(stack.size() - 1));
                }
                isTag = true;
                sb.append(c);
            } else {
                stack.add(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.remove(stack.size() - 1));
        }

        System.out.print(sb);
    }
}
