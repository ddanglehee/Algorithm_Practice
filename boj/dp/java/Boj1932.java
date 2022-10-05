import java.io.*;
import java.util.*;

public class Boj1932 {

    static final int MAX = 501;
    static int N;
    static int[][] triangle = new int[MAX][MAX];
    static int[][] dp = new int[MAX][MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (answer < dp[N][i]) answer = dp[N][i];
        }
        System.out.println(answer);
    }
}
