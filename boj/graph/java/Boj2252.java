import java.io.*;
import java.util.*;

public class Boj2252 {

    static int N, M;
    static ArrayList<Integer>[] adj;
    static int[] in;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        in = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            in[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current).append(" ");

            // 이 친구 다음으로 나오는 친구들 순회
            for (int next: adj[current]) {
                in[next]--;
                if (in[next] == 0) {
                    queue.add(next);
                }
            }
        }

        System.out.println(sb);
    }
}
