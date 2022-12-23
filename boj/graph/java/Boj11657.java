import java.io.*;
import java.util.*;

public class Boj11657 {

    static final long MAX = Long.MAX_VALUE;
    static int N, M;
    static ArrayList<Edge> edges;
    static long[] dist;
    static StringBuilder sb = new StringBuilder();
    static boolean hasCycle = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new long[N + 1];
        edges = new ArrayList<>();
        for (int i = 2; i < N + 1; i++) {
            dist[i] = MAX;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
            if (a == 1) {
                dist[b] = c;
            }
        }

        for (int i = 1; i < N; i++) {
            for (Edge edge: edges) {
                if (dist[edge.from] == Long.MAX_VALUE) continue;
                if (dist[edge.from] + edge.cost < dist[edge.to]) {
                    if (i == N - 1) {
                        hasCycle = true;
                        break;
                    } else {
                        dist[edge.to] = dist[edge.from] + edge.cost;
                    }
                }
            }
        }

        if (hasCycle) {
            sb.append(-1);
        } else {
            for (int i = 2; i < N + 1; i++) {
                if (dist[i] == Long.MAX_VALUE) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(dist[i]).append("\n");
                }
            }
        }

        System.out.println(sb);
    }

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
