import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        Arrays.fill(dp,50000);

        for (int i = 1; i * i <= n; i++) {
            dp[i * i] = 1;
        }

        int k = 1;
        for (int i = 2; i <= n; i++) {
            if (dp[i] == 1) {
                k++;
                continue;
            }

            for (int j = k; k / 2 <= j; j--) {
                dp[i] = Math.min(dp[i], dp[j * j] + dp[i - (j * j)]);
            }
        }

        System.out.println(dp[n]);
    }
}
