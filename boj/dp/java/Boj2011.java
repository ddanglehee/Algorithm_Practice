import java.util.*;
import java.io.*;

public class Boj2011 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] dp = new int[input.length() + 1];

        if (input.charAt(0) == '0') {
            System.out.print(0);
            return;
        }

        dp[0] = 1; dp[1] = 1;
        for (int i = 2; i <= input.length(); i++) {
            int twoNumbers = Integer.parseInt(input.substring(i - 2, i));

            if (input.charAt(i - 1) == '0' && 26 < twoNumbers) {
                System.out.print(0);
                return;
            }

            if (input.charAt(i - 1) != '0') dp[i] = dp[i - 1] % 1000000;
            if (input.charAt(i - 2) != '0' && twoNumbers <= 26) dp[i] = (dp[i] + dp[i - 2]) % 1000000;
        }

        System.out.print(dp[input.length()]);
    }
}
