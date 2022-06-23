import java.io.*;
import java.util.*;

public class Boj1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int currentNumber = 1;
        ArrayList<Integer> stack = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int input = Integer.parseInt(br.readLine());
            if (currentNumber <= input) {
                while (currentNumber <= input) {
                    stack.add(currentNumber);
                    sb.append('+').append('\n');
                    currentNumber++;
                }
            }
            if (stack.get(stack.size() - 1) <= input && !stack.isEmpty()) {
                stack.remove(stack.size() - 1);
                sb.append('-').append('\n');
            } else {
                sb.setLength(0);
                sb.append("NO");
                break;
            }
        }
        System.out.println(sb);
    }
}
