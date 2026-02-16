import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static int[] answer;
    static Set<Integer>[] nextSet;
    static int[] in;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new int[N+1];
        nextSet = new HashSet[N+1];
        for (int i = 1; i <= N; i++) {
            nextSet[i] = new HashSet<>();
        }
        in = new int[N+1];

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nextSet[a].add(b);
            in[b]++;
        }

        topologySort();

        StringBuilder sb = new StringBuilder();
        for (int n = 1; n <= N; n++) {
            sb.append(answer[n]).append(" ");
        }
        System.out.print(sb);
    }

    static void topologySort() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        for (int n = 1; n <= N; n++) {
            if (in[n] == 0) {
                queue.add(new int[] {n, 1});
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            answer[cur[0]] = cur[1];

            for (int next : nextSet[cur[0]]) {
                in[next]--;
                if (in[next] == 0) {
                    queue.add(new int[] {next, cur[1] + 1});
                }
            }
        }
    }
}