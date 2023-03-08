import java.util.*;
import java.io.*;

public class Boj14501 {

    static int answer = 0;
    static int n;
    static int[] t;
    static int[] p;

    public static void main(String[] args) throws IOException {
        input();

        for (int day = 1; day <= n; day++) {
            dfs(day, 0);
        }

        System.out.print(answer);
    }

    private static void dfs(int day, int tmpAnswer) {
        int nextDay = day + t[day];

        if (n + 1 <= nextDay) {
            if (n + 1 == nextDay) tmpAnswer += p[day];
            answer = Math.max(tmpAnswer, answer);
            return;
        }

        for (int i = nextDay; i <= n; i++) {
            dfs(i, tmpAnswer + p[day]);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        t = new int[n + 1];
        p = new int[n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int ti = Integer.parseInt(st.nextToken());
            int pi = Integer.parseInt(st.nextToken());
            t[i] = ti;
            p[i] = pi;
        }
    }
}
