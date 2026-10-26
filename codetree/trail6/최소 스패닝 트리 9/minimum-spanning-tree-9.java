import java.util.*;
import java.io.*;

public class Main {

    private static int n, m;
    private static ArrayList<int[]>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[u].add(new int[] {v, w});
            adjList[v].add(new int[] {u, w});
        }
        
        System.out.print(mst());
    }

    private static int mst() {
        int result = 0;
        boolean[] visited = new boolean[n+1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[] {1, 0});
        int count = 0;
        while (!pq.isEmpty() && count < n) {
            int[] cur = pq.poll();

            if (visited[cur[0]]) continue;

            count++;
            visited[cur[0]] = true;
            result += cur[1];
            for (int[] nxt : adjList[cur[0]]) {
                if(visited[nxt[0]]) continue;
                pq.offer(nxt);
            }
        }

        return result;
    }
}