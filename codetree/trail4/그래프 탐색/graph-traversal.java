import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        System.out.print(dfs(1) - 1);
    }

    private static int dfs(int x) {
        int result = 1;
        visited[x] = true;

        for (int nxt : adj[x]) {
            if (visited[nxt]) continue;
            result += dfs(nxt);
        }

        return result;
    }
}