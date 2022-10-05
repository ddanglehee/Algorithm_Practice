import java.io.*;

public class Boj2839 {

    static final int MAX = 10000;
    static int N;
    static int[] dp = new int[5001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            dp[i] = MAX;
        }
        dp[3] = 1; dp[5] = 1;

        for (int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
        }

        if (MAX <= dp[N]) System.out.println(-1);
        else System.out.println(dp[N]);
    }
}
