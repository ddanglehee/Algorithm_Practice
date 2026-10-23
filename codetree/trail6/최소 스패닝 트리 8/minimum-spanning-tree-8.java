import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(dist, 987654321);

        adjList = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Edge(b, c));
            adjList[b].add(new Edge(a, c));
        }

        prim();

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer += dist[i];
        }
        System.out.print(answer);
    }

    private static void prim() {
        dist[1] = 0;
        visited[1] = true;
        for (Edge edge : adjList[1]) {
            pq.offer(edge);
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (visited[edge.b] || dist[edge.b] <= edge.c) continue;

            visited[edge.b] = true; 
            dist[edge.b] = edge.c;

            for (Edge nxt : adjList[edge.b]) {
                if (dist[nxt.b] <= nxt.c) continue;
                pq.offer(nxt);
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int b, c;

        public Edge(int b, int c) {
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.c, o.c);
        }
    }
}