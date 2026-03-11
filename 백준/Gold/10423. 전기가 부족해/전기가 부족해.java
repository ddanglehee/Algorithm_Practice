import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static ArrayList<Edge>[] adj;
    static boolean[] visited;
    static int[] powerStations;
    static int[] minCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        init();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int powerStation = Integer.parseInt(st.nextToken());
            powerStations[i] = powerStation;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Edge(v, w));
            adj[v].add(new Edge(u, w));
        }

        System.out.print(prim());
    }

    static int prim() {
        int result = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int powerStation : powerStations) {
            minCost[powerStation] = 0;
            visited[powerStation] = true;
            for (Edge edge : adj[powerStation]) {
                pq.offer(edge);
            }
        }

        int count = 0;

        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();

            if (visited[curEdge.to]) continue;

            visited[curEdge.to] = true;

            result += curEdge.cost;
            count++;

            if (count == N - K) break;

            for (Edge nextEdge : adj[curEdge.to]) {
                if (!visited[nextEdge.to] && minCost[nextEdge.to] > nextEdge.cost) {
                    pq.offer(nextEdge);
                    minCost[nextEdge.to] = nextEdge.cost;
                }
            }
        }

        return result;
    }

    static void init() {
        powerStations = new int[K];

        // adj 초기화
        adj = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // minCost 초기화
        minCost = new int[N+1];
        for (int i = 1; i <= N; i++) {
            minCost[i] = Integer.MAX_VALUE;
        }

        visited = new boolean[N+1];
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}