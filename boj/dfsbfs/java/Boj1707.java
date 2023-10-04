import java.util.*;
import java.io.*;

public class Boj1707 {

    private static int k, v, e;
    private static ArrayList<Integer>[] graph;
    private static int[] setNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 0; t < k; t++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            setNumber = new int[v + 1];
            graph = new ArrayList[v + 1];
            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
                setNumber[i] = -1;
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                graph[b].add(a);
            }

            String answer = "YES";
            for (int i = 1; i <= v; i++) {
                if (setNumber[i] != -1) continue;
                if (!isBipartiteGraph(i)) {
                    answer = "NO";
                    break;
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }

    private static boolean isBipartiteGraph(int start) {
        setNumber[start] = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {start, 0}); // {정점 번호, 집합 종류}

        while(!queue.isEmpty()) {
            int[] cur = queue.removeFirst();

            for (int nxt: graph[cur[0]]) {
                if (setNumber[nxt] == -1) {
                    int nxtSetNumber = (cur[1] + 1) % 2;
                    setNumber[nxt] = nxtSetNumber;
                    queue.add(new int[] {nxt, nxtSetNumber});
                } else if (setNumber[nxt] == cur[1]) {
                    return false;
                }
            }
        }

        return true;
    }
}
