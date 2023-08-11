import java.util.*;
import java.io.*;

public class Boj1647 {

    static int n, m;
    static ArrayList<int[]>[] graph;
    static boolean[] visited;
    static int answer = 0;
    static int max = 0;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[1] - o2[1];
        }
    });

    public static void main(String[] args) throws IOException {
        init();
        prim(1);
        System.out.print(answer - max);
    }

    private static void prim(int start) {
        visited[start] = true;
        for(int[] info: graph[start]) {
            pq.add(info);
        }

        while (!pq.isEmpty()) {
            int[] info = pq.poll();

            if (visited[info[0]]) continue;
            visited[info[0]] = true;
            answer += info[1];
            max = Math.max(max, info[1]);

            for (int[] nInfo: graph[info[0]]) {
                if (visited[nInfo[0]]) continue;
                pq.add(nInfo);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }
    }
}
