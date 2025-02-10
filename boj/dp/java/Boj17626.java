import java.io.*;
import java.util.*;

public class Boj17626 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        Arrays.fill(dp,4); // 모든 자연수는 넷 혹은 그 이하의 제곱수의 합으로 표현할 수 있다.

        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 1은 dp[j * j]
            }
        }

        System.out.println(dp[n]);
    }
}

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


