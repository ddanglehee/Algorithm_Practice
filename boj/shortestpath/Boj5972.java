import java.util.*;
import java.io.*;

public class Boj5972 {

    static int n, m;
    static int[] cost;
    static ArrayList<int[]>[] map;


    public static void main(String[] args) throws IOException {
        init();
        dijkstra();

        System.out.print(cost[n]);
    }

    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        cost[1] = 0;

        pq.add(new int[] {1, 0});
        while(!pq.isEmpty()) {
            int[] info = pq.poll();
            int cur = info[0];
            int curCost = info[1];

            if (cost[cur] < curCost) continue;
            for (int[] nxt : map[cur]) {
                int nxtCost = nxt[1];
                if (curCost + nxtCost < cost[nxt[0]]) {
                    cost[nxt[0]] = curCost + nxtCost;
                    pq.add(new int[] {nxt[0], curCost + nxtCost});
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cost = new int[n + 1];
        map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            cost[i] = 100000000;
            map[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a].add(new int[] {b, c});
            map[b].add(new int[] {a, c});
        }
    }
}
