import java.util.*;
import java.io.*;

public class Boj9935 {

    static ArrayDeque<Character> stack1 = new ArrayDeque<>();
    static ArrayDeque<Character> stack2 = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            stack1.add(c);
        }

        String target = br.readLine();
        int targetLength = target.length();
        char firstCharacter = target.charAt(0);
        while (!stack1.isEmpty()) {
            Character c = stack1.removeLast();

            if (c == firstCharacter) {
                char[] tmp = new char[target.length()];
                tmp[0] = c;
                boolean flag = false;
                for (int i = 1; i < targetLength; i++) {
                    if (stack2.isEmpty()) {
                        for (int j = i - 1; 0 <= j; j--) {
                            stack2.add(tmp[j]);
                        }
                        flag = true;
                        break;
                    }

                    tmp[i] = stack2.removeLast();
                }

                if (flag) continue;
                if (!target.equals(new String(tmp))) {
                    for (int i = targetLength - 1; 0 <= i; i--) {
                        stack2.add(tmp[i]);
                    }
                }
            } else {
                stack2.add(c);
            }
        }

        if (stack2.isEmpty()) {
            System.out.print("FRULA");
        } else {
            char[] answer = new char[stack2.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = stack2.removeLast();
            }
            System.out.print(new String(answer));
        }
    }
}
