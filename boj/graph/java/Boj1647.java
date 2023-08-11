import java.util.*;
import java.io.*;

public class Boj1647 {

    static int n, m;
    static ArrayList<int[]>[] graph;
    static boolean[] visited;
    static int[] parent;
    static int answer = 0;
    static int max = 0;
    static PriorityQueue<int[]> pqForPrim = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[1] - o2[1];
        }
    });
    static PriorityQueue<int[]> pqForKruskal = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[2] - o2[2];
        }
    });

    public static void main(String[] args) throws IOException {
        init();
//        prim(1);
        kruskal();
        System.out.print(answer - max);
    }

    private static void kruskal() {
        while (!pqForKruskal.isEmpty()) {
            int[] info = pqForKruskal.poll();
            if(find(info[0]) == find(info[1])) continue;

            answer += info[2];
            max = Math.max(max, info[2]);
            union(info[0], info[1]);
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent == bParent) return;

        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }

    private static void prim(int start) {
        visited[start] = true;
        for(int[] info: graph[start]) {
            pqForPrim.add(info);
        }

        while (!pqForPrim.isEmpty()) {
            int[] info = pqForPrim.poll();

            if (visited[info[0]]) continue;
            visited[info[0]] = true;
            answer += info[1];
            max = Math.max(max, info[1]);

            for (int[] nInfo: graph[info[0]]) {
                if (visited[nInfo[0]]) continue;
                pqForPrim.add(nInfo);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            parent[i] = i;
        }
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
            pqForKruskal.add(new int[] {a, b, c});
        }
    }
}
