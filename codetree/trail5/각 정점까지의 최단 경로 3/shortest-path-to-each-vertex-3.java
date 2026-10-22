import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[s].add(new int[] {e, c});
        }

        int INF = 987654321;
        int[] dist = new int[n+1];
        for (int i = 2; i <= n; i++) {
            dist[i] = INF;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        dist[1] = 0;
        pq.offer(new int[] {1, 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (dist[cur[0]] < cur[1]) continue;

            for (int[] nxt : list[cur[0]]) {
                if (cur[1] + nxt[1] < dist[nxt[0]]) {
                    dist[nxt[0]] = cur[1] + nxt[1];
                    pq.offer(new int[] {nxt[0], cur[1] + nxt[1]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            if (dist[i] == INF) {
                sb.append(-1);
            } else {
                sb.append(dist[i]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}