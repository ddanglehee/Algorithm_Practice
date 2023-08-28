import java.util.*;
import java.io.*;

public class Boj24391 {

    static int n, m;
    static int[] parent;
    static int[] schedule;

    public static void main(String[] args) throws IOException {
        init();

        int answer = 0;
        int cur = find(schedule[0]);
        for (int i = 1; i < n; i++) {
            int nxt = find(schedule[i]);

            if (cur != nxt) {
                answer++;
            }
            cur = nxt;
        }

        System.out.print(answer);
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent == bParent) return;
        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        schedule = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            schedule[i] = Integer.parseInt(st.nextToken());
        }
    }
}
