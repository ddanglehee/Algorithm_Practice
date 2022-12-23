import java.util.*;
import java.io.*;

public class Boj1753 {

    static final int MAX = 987654321;
    static int V, E, K;
    static ArrayList<Edge>[] adj;
    static int[] dist;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        adj = new ArrayList[V + 1];
        dist = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = MAX;
        }


        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Edge(b, c));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getCost));
        dist[K] = 0;
        pq.add(new Edge(K, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curNum = cur.nextNumber;
            int curCost = cur.cost;

            if (dist[curNum] < curCost) continue;

            for (Edge next: adj[curNum]) {
                if (curCost + next.cost < dist[next.nextNumber]) {
                    pq.add(new Edge(next.nextNumber, curCost + next.cost));
                    dist[next.nextNumber] = curCost + next.cost;
                }
            }
        }

        for (int i = 1; i < V + 1; i++) {
            int d = dist[i];
            if (d == MAX) {
                sb.append("INF\n");
            } else {
                sb.append(d).append("\n");
            }
        }
        System.out.println(sb);
    }

    static class Edge {
        int nextNumber;
        int cost;

        public Edge(int next, int cost) {
            this.nextNumber = next;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }
}
