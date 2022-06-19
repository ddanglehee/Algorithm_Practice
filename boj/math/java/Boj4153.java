import java.io.*;
import java.util.*;

public class Boj4153 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine();
            if (input.equals("0 0 0")) break;

            StringTokenizer st = new StringTokenizer(input, " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[] numbers = {a, b, c};
            Arrays.sort(numbers);

            boolean isRightTriangle = (Math.pow(numbers[0], 2) + Math.pow(numbers[1], 2) == Math.pow(numbers[2], 2));
            if (isRightTriangle) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
        br.close();
    }
}