import java.util.*;
import java.io.*;

public class Boj16987 {

    static int n;
    static int[] s;
    static int[] w;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = new int[n]; // 내구도 (변함)
        w = new int[n]; // 무게

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken());
            w[i] = Integer.parseInt(st.nextToken());
        }

        hitEgg(0);
        System.out.println(answer);
    }

    private static void hitEgg(int curEgg) {
        if (curEgg == n) {
            answer =  Math.max(answer, countBrokenEgg());
            return;
        }
        // 손에 든 계란이 깨졌거나 깨지지 않은 계란이 없으면 치지 않고 넘어간다.
        if (s[curEgg] <= 0 || countBrokenEgg() >= n - 1) {
            hitEgg(curEgg + 1);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i == curEgg || s[i] <= 0) continue;

            // 계란 치기
            s[i] -= w[curEgg];
            s[curEgg] -= w[i];

            hitEgg(curEgg + 1);

            s[i] += w[curEgg];
            s[curEgg] += w[i];
        }
    }

    private static int countBrokenEgg() {
        int result = 0;

        for (int i = 0; i < n; i++) {
            if (s[i] <= 0) result++;
        }

        return result;
    }

}
