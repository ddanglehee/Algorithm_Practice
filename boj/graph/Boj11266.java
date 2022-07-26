import java.io.*;
import java.util.*;

public class Boj11266 {

    static int V, E;
    static ArrayList<Integer>[] adj;
    static int[] order;
    static int currentOrder;
    static boolean[] isCut;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        isCut = new boolean[V + 1];
        order = new int[V + 1];
        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        for (int i = 1; i <= V; i++) {
            // 방문한 적이 없는 노드만 dfs
            if (order[i] == 0) {
                dfs(i, true);
            }
        }

        int answer = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (isCut[i]) {
                answer++;
            }
        }
        sb.append(answer).append("\n");
        for (int i = 1; i <= V; i++) {
            if (isCut[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    static int dfs(int cur, boolean isRoot) {
        order[cur] = ++currentOrder;
        int ret = order[cur];

        int child = 0;
        for (int next: adj[cur]) {
            if (order[next] == 0) {
                child++;
                int low = dfs(next, false);
                if (!isRoot && order[cur] <= low) {
                    isCut[cur] = true;
                }
                ret = Math.min(ret, low);
            } else {
                ret = Math.min(ret, order[next]);
            }
        }

        if (isRoot && (1 < child)) {
            isCut[cur] = true;
        }

        return ret;
    }
}
