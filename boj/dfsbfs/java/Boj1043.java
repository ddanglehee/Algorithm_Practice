import java.util.*;
import java.io.*;

public class Boj1043 {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] party;
    static boolean[] visited;
    static HashSet<Integer> knowTruthSet = new HashSet<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        init();

        if (knowTruthSet.size() == 0) {
            System.out.print(m);
            return;
        }

        for (int p: knowTruthSet) {
            if (!visited[p]) {
                bfs(p);
            }
        }

        for (int i = 0; i < m; i++) {
            boolean truth = false;

            for (int p: party[i]) {
                if (visited[p]) {
                    truth = true;
                    break;
                }
            }

            if (!truth) {
                answer++;
            }
        }

        System.out.print(answer);
    }

    private static void bfs(int start) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();

            for (int next: graph[cur]) {
                if (visited[next]) continue;

                visited[next] = true;
                queue.add(next);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        party = new ArrayList[m];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            party[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int knowTruthCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < knowTruthCount; i++) {
            knowTruthSet.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            int a = Integer.parseInt(st.nextToken());
            party[i].add(a);
            for (int j = 1; j < k; j++) {
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
                party[i].add(b);
                a = b;
            }
        }
    }
}
