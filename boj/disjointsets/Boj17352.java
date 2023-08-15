import java.util.*;
import java.io.*;

public class Boj17352 {

    static int n;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        init();

        int tmp = parent[1];
        for (int i = 2; i <= n; i++) {
            find(i);
            if (tmp != parent[i]) {
                System.out.print(1 + " " + i);
                break;
            }
        }
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

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
        n = Integer.parseInt(br.readLine());
        parent = new int[n+1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        StringTokenizer st;
        for (int i = 0; i < n - 2; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }
    }
}
