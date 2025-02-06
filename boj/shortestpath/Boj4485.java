import java.util.*;
import java.io.*;

public class Boj4485 {

    static final int MAX = 156250;
    static int n; // index: 0 ~ N-1
    static int[][] map = new int[125][125];
    static int[][] dst = new int[125][125];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int turn = 0;
        while (true) {
            turn++;

            n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            initDst();
            dijkstra();
            sb.append("Problem ").append(turn).append(": ").append(dst[n-1][n-1]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        pq.clear();
        pq.add(new int[]{0, 0, dst[0][0]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDst = cur[2];

            if (dst[curX][curY] < curDst) continue;

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) continue;
                int newDst = curDst + map[nextX][nextY];
                if (newDst < dst[nextX][nextY]) {
                    pq.add(new int[]{nextX, nextY, newDst});
                    dst[nextX][nextY] = newDst;
                }
            }
        }
    }

    private static void initDst() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dst[i][j] = MAX;
            }
        }
        dst[0][0] = map[0][0];
    }
}
