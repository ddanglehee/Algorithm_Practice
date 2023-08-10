import java.util.*;
import java.io.*;

public class Boj2565 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Wire> wireList = new ArrayList<Wire>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wireList.add(new Wire(a, b));
        }

        wireList.sort(Comparator.comparingInt(Wire::getA));
        int[] dp = new int[wireList.size()];
        int max = 1;
        dp[0] = 1;
        for (int i = 1; i < wireList.size(); i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (wireList.get(j).b < wireList.get(i).b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        System.out.print(wireList.size() - max);
    }

    static class Wire {
        int a;
        int b;

        public Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }
    }
}
