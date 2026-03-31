import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.print(bfs(N));
    }

    static int bfs(int N) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[3000000];
        visited[N] = true;
        queue.add(new int[] {N, 0});

        if (N == 1) return 0;

        ArrayList<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            list.clear();

            int count = cur[1] + 1;

            list.add(cur[0] + 1);
            list.add(cur[0] - 1);

            if (cur[0] % 2 == 0) {
                list.add(cur[0] / 2);
            }
            if (cur[0] % 3 == 0) {
                list.add(cur[0] / 3);
            }

            for (int num : list) {
                if (num == 1) return count;

                if (!visited[num]) {
                    visited[num] = true;
                    queue.add(new int[] {num, count});
                }
            }
        }

        return -1;
    }
}