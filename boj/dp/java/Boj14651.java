import java.io.*;

public class Boj14651 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[33334]; // dp[i] : i자리 수 중 3의 배수의 개수

        dp[1] = 0;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] * 3) % 1000000009;
        }

        System.out.print(dp[n]);
    }
}
