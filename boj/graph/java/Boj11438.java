import java.io.*;
import java.util.*;

public class Boj11438 {

    static int N, M;
    static int[] depth;
    static int[][] parent;
    static boolean[] visited;
    static ArrayList<Integer>[] adj;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        visited = new boolean[N + 1];
        parent = new int[18][N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(0, 1);
        for (int i = 1; i <= 17; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append("\n");
        }

        System.out.println(sb);
    }

    static int lca(int a, int b) {
        if (depth[b] < depth[a]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        if (depth[a] != depth[b]) {
            int diff = depth[b] - depth[a];
            for (int i = 0; i < 18; i++) {
                if ((diff & (1 << i)) > 0) {
                    b = parent[i][b];
                }
            }
        }

        if (a == b) return a;

        for (int i = 17; 0 <= i; i--) {
            // 2^i 만큼 올라갔을 때 parent가 다르다면 일단 거기 depth로 올라가기
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    static void dfs(int pre, int cur) {
        visited[cur] = true;
        parent[0][cur] = pre;
        depth[cur] = depth[pre] + 1;

        for (int next: adj[cur]) {
            if (!visited[next]) {
                dfs(cur, next);
            }
        }
    }
}
