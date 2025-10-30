import java.io.*;
import java.util.*;

public class Boj15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] p = new int[n+1];
        int[] end = new int[n+1];
        int[] dp = new int[n+1]; // dp[i] : 1일 ~ i일까지 고려했을 때 최대 수익

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            end[i] = i + Integer.parseInt(st.nextToken()) - 1;
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            // i번째 날 상담을 하지 않는 경우
            dp[i] = Math.max(dp[i - 1], dp[i]);

            // i번째 날 상담을 하는 경우
            if (end[i] <= n) {
                dp[end[i]] = Math.max(dp[end[i]], dp[i - 1] + p[i]);
            }
        }

        System.out.print(dp[n]);
    }
}