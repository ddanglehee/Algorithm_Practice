import java.io.*;

public class Boj1309 {

    // 1차원 배열 풀이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        dp[0] = 1; dp[1] = 3

        for (int i = 2; i <= n; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i - 2])  % 9901;
        }

        System.out.print(dp[n]) % 9901);
    }

    // 2차원 배열 풀이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][2]; // dp[i][0]: 세로 i칸에 사자가 0마리인 경우, dp[i][1]: 세로 i칸에 사자가 1마리인 경우
        dp[1][0] = 1; dp[1][1] = 2;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
            dp[i][1] = (dp[i - 1][1] + 2 * dp[i - 1][0]) % 9901;
        }

        System.out.print((dp[n][0] + dp[n][1]) % 9901);
    }
}
