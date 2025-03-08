import java.io.*;
import java.util.*;

public class Boj1863 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        StringTokenizer st;
        int x, y;
        int answer = 0;
        ArrayList<Integer> stack = new ArrayList<>();
        stack.add(0);
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            while (y < stack.get(stack.size() - 1)) stack.remove(stack.size() - 1);
            if (y == stack.get(stack.size() - 1)) continue;
            stack.add(y);
            answer++;
        }

        System.out.print(answer);
    }
}
