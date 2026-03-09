import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static ArrayList<Edge>[] adjList;
    static int answer;
    static int max, maxV;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Edge(b, c));
            adjList[b].add(new Edge(a, c));
        }

        dfs(1, 0);
        dfs(maxV, 0);

        System.out.print(max);
    }

    static void dfs(int n, int totalCost) {
        visited[n] = true;
        if (max < totalCost) {
            max = totalCost;
            maxV = n;
        }

        for (Edge e : adjList[n]) {
            if (visited[e.to]) continue;
            dfs(e.to, totalCost + e.cost);
        }

        visited[n] = false;
    }
    
    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}