import java.util.*;
import java.io.*;

public class Boj3176 {

    static int N, K;
    static ArrayList<Link>[] adj;
    static STInfo[][] parent;
    static int[] depth;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        parent = new STInfo[18][N + 1];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j <= N; j++) {
                parent[i][j] = new STInfo(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            }
        }
        visited = new boolean[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            adj[A].add(new Link(B, C));
            adj[B].add(new Link(A, C));
        }

        dfs(1);
        for (int i = 1; i < 18; i++) {
            for (int n = 1; n <= N; n++) {
                // parent[i][n] = parent[i - 1][parent[i - 1][n]]
                STInfo link1 = parent[i - 1][n];
                STInfo link2 = parent[i - 1][parent[i - 1][n].to];

                int min = Math.min(link1.minLength, link2.minLength);
                int max = Math.max(link1.maxLength, link2.maxLength);

                parent[i][n] = new STInfo(link2.to, min, max);
            }
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            lca(D, E);
        }

        System.out.println(sb);
    }

    static void lca(int d, int e) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        if (depth[e] < depth[d]) {
            int tmp = d;
            d = e;
            e = tmp;
        }

        int diff = depth[e] - depth[d];
        for (int i = 0; i < 18; i++) {
            if (0 < (diff & 1 << i)) {
                min = Math.min(min, parent[i][e].minLength);
                max = Math.max(max, parent[i][e].maxLength);
                e = parent[i][e].to;
            }
        }

        if (d == e){
            sb.append(min).append(" ").append(max).append("\n");
            return;
        }

        for (int i = 17; 0 <= i; i--) {
            // 다르면
            if (parent[i][d].to != parent[i][e].to) {
                min = Math.min(min, Math.min(parent[i][d].minLength, parent[i][e].minLength));
                max = Math.max(max, Math.max(parent[i][d].maxLength, parent[i][e].maxLength));
                d = parent[i][d].to;
                e = parent[i][e].to;
            }
        }

        min = Math.min(min, Math.min(parent[0][d].minLength, parent[0][e].minLength));
        max = Math.max(max, Math.max(parent[0][d].maxLength, parent[0][e].maxLength));
        sb.append(min).append(" ").append(max).append("\n");
    }

    static void dfs(int cur) {
        visited[cur] = true;

        for (Link link: adj[cur]) {
            int next = link.to;
            if (!visited[next]) {
                int cost = link.cost;
                depth[next] = depth[cur] + 1;
                parent[0][next] = new STInfo(cur, cost, cost);
                dfs(next);
            }
        }
    }

    static class Link {
        int to;
        int cost;

        public Link(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class STInfo {
        int to;
        int minLength;
        int maxLength;

        public STInfo(int to, int minLength, int maxLength) {
            this.to = to;
            this.minLength = minLength;
            this.maxLength = maxLength;
        }
    }
}
