import java.io.*;
import java.util.*;

public class Boj20040 {

    static int N, M;
    static int[] parent;
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();

        int answer = 0;
        for (int turn = 1; turn <= M; turn++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (findParent(a) == findParent(b)) {
                answer = turn;
                break;
            } else {
                union(a, b);
            }
        }

        System.out.println(answer);
    }

    private static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int findParent(int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }

        return parent[x];
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }
}
