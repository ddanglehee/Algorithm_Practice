import java.util.*;
import java.io.*;

class Boj13023 {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        for (int a = 0; a < n; a++) {
            if (dfs(a, 1)) {
                System.out.print(1);
                return;
            }
        }
        System.out.print(0);
    }

    private static boolean dfs(int cur, int count) {
        visited[cur] = true;

        if (count == 5) {
            return true;
        }

        for (int next: graph[cur]) {
            if (visited[next]) continue;
            if (dfs(next, count + 1)) return true;
        }

        visited[cur] = false;
        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
    }
}
