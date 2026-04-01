import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[20];
        dp[0] = 1;

        for (int n = 1; n <= N; n++) {
            for (int i = 0; i < n; i++) {
                dp[n] += dp[i] * dp[n - i - 1];
            }
        }

        System.out.print(dp[N]);
    }
}