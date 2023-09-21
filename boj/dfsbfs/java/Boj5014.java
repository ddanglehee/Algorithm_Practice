import java.io.*;
import java.util.*;

public class Boj5014 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[f + 1];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {s, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == g) {
                System.out.print(cur[1]);
                return;
            }

            if (cur[0] + u <= f && !visited[cur[0] + u]) {
                visited[cur[0] + u] = true;
                q.add(new int[] {cur[0] + u, cur[1] + 1});
            }
            if (1 <= cur[0] - d && !visited[cur[0] - d]) {
                visited[cur[0] - d] = true;
                q.add(new int[] {cur[0] - d, cur[1] + 1});
            }
        }

        System.out.print("use the stairs");
    }
}
