import java.io.*;

public class Boj14916 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[Math.max(6, n + 1)];

        coins[1] = -1; coins[2] = 1; coins[3] = -1; coins[4] = 2; coins[5] = 1;
        for (int i = 6; i <= n; i++) {
            int a = coins[i - 2];
            int b = coins[i - 5];

            if (a == -1 && b == -1) {
                coins[i] = -1;
            } else if (a == -1) {
                coins[i] = b + 1;
            } else if (b == -1) {
                coins[i] = a + 1;
            } else {
                coins[i] = Math.min(a, b) + 1;
            }
        }

        System.out.println(coins[n]);
    }
}
