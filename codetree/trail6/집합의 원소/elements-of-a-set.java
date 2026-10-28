import java.util.*;
import java.io.*;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                union(a, b);
            } else {
                int aParent = findParent(a);
                int bParent = findParent(b);

                if (aParent == bParent) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            }
        }
        System.out.print(sb);
    }

    private static void union(int a, int b) {
        int aParent = findParent(a);
        int bParent = findParent(b);

        if (aParent == bParent) return;

        if (aParent > bParent) {
            parent[aParent] = bParent;
        } else {
            parent[bParent] = aParent;
        }
    }

    private static int findParent(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = findParent(parent[a]);
    }
}