import java.io.*;
import java.util.*;

class Boj32177 {

    private static int n, k, t;
    private static int[] x;
    private static int[] y;
    private static int[] v;
    private static boolean[] p;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        init();
        connect();
        printAnswer();
    }

    private static void printAnswer() {
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            int iParent = getParent(i);
            if (iParent == 0 && p[i]) {
                answer.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (answer.isEmpty()) {
            sb.append(0);
        } else {
            Collections.sort(answer);
            for (Integer friend : answer) {
                sb.append(friend).append(" ");
            }
        }

        System.out.print(sb);
    }

    private static void connect() {
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (Math.abs(v[i] - v[j]) <= t && getDistance(x[i], y[i], x[j], y[j]) <= k) {
                    union(i, j);
                }
            }
        }
    }

    private static void union(int a, int b) {
        int aParent = getParent(a);
        int bParent = getParent(b);

        if (aParent == bParent) return;

        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }

    private static int getParent(int a) {
        if (parent[a] == a) return a;

        return parent[a] = getParent(parent[a]);
    }

    private static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        x = new int[n+1];
        y = new int[n+1];
        v = new int[n+1];
        p = new boolean[n+1];
        parent = new int[n+1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        x[0] = Integer.parseInt(st.nextToken());
        y[0] = Integer.parseInt(st.nextToken());
        v[0] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
            int photo = Integer.parseInt(st.nextToken());
            if (photo == 1) {
                p[i] = true;
            } else {
                p[i] = false;
            }
        }
    }
}
