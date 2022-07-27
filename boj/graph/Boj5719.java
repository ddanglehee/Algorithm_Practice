import java.io.*;
import java.util.*;

public class Boj5719 {

    static final long INF = Long.MAX_VALUE;
    static int N, M, S, D;
    static int[][] adj;
    static long[] dist;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            dist = new long[N];
            adj = new int[N][N];

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                adj[U][V] = P;
            }

            // 1. 다익스트라
            dijkstra(S);

            // 2. 최단 경로 지우기
            removeShortestPath();

            // 3. 다시 다익스트라
            dijkstra(S);
            if (dist[D] == INF) {
                sb.append(-1).append("\n");
            } else {
                sb.append(dist[D]).append("\n");
            }
        }
        System.out.print(sb);
    }

    static void removeShortestPath() {
        Queue<Integer> q = new LinkedList<>();
        q.add(D);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < N; i++) {
                if (adj[i][cur] == 0) continue;

                if (dist[cur] - adj[i][cur] == dist[i]) {
                    q.add(i);
                    adj[i][cur] = 0;
                }
            }
        }
    }

    static void dijkstra(int s) {
        for (int i = 0; i < N; i++) {
            dist[i] = INF;
        }
        PriorityQueue<Link> pq = new PriorityQueue<>(Comparator.comparing(Link::getCost));
        dist[s] = 0;
        pq.add(new Link(s, 0));

        while (!pq.isEmpty()) {
            Link curLink = pq.poll();
            int cur = curLink.num;
            long curCost = curLink.cost;
            if (dist[cur] < curCost) continue;

            for (int i = 0; i < N; i++) {
                long w = curCost + adj[cur][i];
                if ((adj[cur][i] != 0) && w < dist[i]) {
                    dist[i] = w;
                    pq.add(new Link(i, w));
                }
            }
        }
    }

    static class Link {
        int num;
        long cost;

        public Link(int to, long cost) {
            this.num = to;
            this.cost = cost;
        }

        public long getCost() {
            return cost;
        }
    }
}
