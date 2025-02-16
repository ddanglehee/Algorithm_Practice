import java.io.*;
import java.util.*;

public class Boj11657 {

    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static ArrayList<int[]>[] edges;
    static long[] dist;
    static StringBuilder sb = new StringBuilder();
    static boolean hasCycle = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new long[N + 1];
        edges = new ArrayList[N + 1];
        edges[1] = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            dist[i] = INF;
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[a].add(new int[]{b, c});
        }

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] inQueue = new boolean[N + 1];
        queue.add(1);
        inQueue[1] = true;
        int[] inQueueCount = new int[N + 1];

        while (!queue.isEmpty()) {
            int from = queue.poll();
            inQueue[from] = false;

            for (int[] a : edges[from]) {
                int to = a[0];
                int cost = a[1];

                if (dist[to] > dist[from] + cost) {
                    dist[to] = dist[from] + cost;

                    if (inQueueCount[to] >= N) {
                        hasCycle = true;
                        break;
                    }

                    if (!inQueue[to]) {
                        queue.add(to);
                        inQueueCount[to]++;
                        inQueue[to] = true;
                    }
                }
            }

            if (hasCycle) break;
        }

        if (hasCycle) {
            sb.append(-1);
        } else {
            for (int i = 2; i < N + 1; i++) {
                if (dist[i] == INF) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(dist[i]).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}