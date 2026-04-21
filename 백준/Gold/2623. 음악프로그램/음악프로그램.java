import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] adjList;
    static int[] in;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        in = new int[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        int pre, cur, count;
        for (int i = 0; i < M; i++) {
            st = new  StringTokenizer(br.readLine());

            count = Integer.parseInt(st.nextToken());
            pre = Integer.parseInt(st.nextToken());
            for (int j = 1; j < count; j++) {
                cur = Integer.parseInt(st.nextToken());
                in[cur]++;
                adjList[pre].add(cur);
                pre = cur;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        count = 0;
        while (!q.isEmpty()) {
            cur = q.poll();
            sb.append(cur).append("\n");
            count++;

            for (int i : adjList[cur]) {
                in[i]--;
                if (in[i] == 0) {
                    q.add(i);
                }
            }
        }

        if (count == N) {
            System.out.print(sb);
        } else {
            System.out.print(0);
        }
    }
}
