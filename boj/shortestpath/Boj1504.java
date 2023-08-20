import java.util.*;
import java.io.*;

public class Boj1504 {

    static int n, e;
    static ArrayList<int[]>[] graph;
    static int[] mustVisit = new int[2];
    static int answer = 987654321;

    public static void main(String[] args) throws IOException {
        init();

        answer = Math.min(answer, dijkstra(1, mustVisit[0]) + dijkstra(mustVisit[0], mustVisit[1]) + dijkstra(mustVisit[1], n));
        answer = Math.min(answer, dijkstra(1, mustVisit[1]) + dijkstra(mustVisit[1], mustVisit[0]) + dijkstra(mustVisit[0], n));
        if (answer == 987654321 || answer < 0) answer = -1;
        System.out.print(answer);
    }

    private static int dijkstra(int start, int end) {
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = 987654321;
        }
        dist[start] = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparing(Info::getDist));
        pq.offer(new Info(start, 0));

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (dist[info.cur] < info.dist) continue;

            for (int[] nextInfo: graph[info.cur]) {
                int nextDist = info.dist + nextInfo[1];
                if (nextDist < dist[nextInfo[0]]) {
                    dist[nextInfo[0]] = nextDist;
                    pq.add(new Info(nextInfo[0], nextDist));
                }
            }
        }

        return dist[end];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }

        st = new StringTokenizer(br.readLine());
        mustVisit[0] = Integer.parseInt(st.nextToken());
        mustVisit[1] = Integer.parseInt(st.nextToken());
    }

    static class Info {
        int cur;
        int dist;

        public Info(int cur, int dist) {
            this.cur = cur;
            this.dist = dist;
        }

        public int getDist() {
            return dist;
        }
    }
}
