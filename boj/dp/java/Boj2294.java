import java.util.*;
import java.io.*;

public class Boj2294 {

    static int n, k;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            dp[i] = 10001;
        }

        Set<Integer> coinSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            coinSet.add(Integer.parseInt(br.readLine()));
        }
        ArrayList<Integer> coinList = new ArrayList<>(coinSet);
        Collections.sort(coinList);

        for (Integer coin: coinList) {
            if (k < coin) break;
            dp[coin] = 1;
        }

        for (int i = 1; i <= k; i++) {
            for (int c = 0; c < coinList.size(); c++) {
                Integer coin = coinList.get(c);

                if (i <= coin) break;
                if (dp[i - coin] == 10001) continue;

                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }


        if (dp[k] == 10001) {
            System.out.print(-1);
        } else {
            System.out.print(dp[k]);
        }
    }
}
