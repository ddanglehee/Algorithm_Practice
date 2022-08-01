import java.io.*;

public class Boj1003 {

    static int T;
    static Result[] dp = new Result[41];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        dp[0] = new Result(1, 0);
        dp[1] = new Result(0, 1);
        for (int i = 2; i <= 40 ; i++) {
            dp[i] = new Result(dp[i - 1].zero + dp[i - 2].zero, dp[i - 1].one + dp[i - 2].one);
        }

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            sb.append(dp[n].zero).append(" ").append(dp[n].one).append("\n");
        }

        System.out.println(sb);
    }
}

class Result {
    int zero;
    int one;

    public Result(int zero, int one) {
        this.zero = zero;
        this.one = one;
    }
}



