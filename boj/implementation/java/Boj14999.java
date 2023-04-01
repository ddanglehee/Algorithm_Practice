import java.util.*;
import java.io.*;

class Boj14999 {

    static int n, m, x, y, k;
    static int[][] graph;
    static int front = 0; static int rear = 0;
    static int top = 0; static int bottom = 0;
    static int right = 0; static int left = 0;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            int d = Integer.parseInt(st.nextToken());
            if (rollDice(d - 1)) {
                // 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
                if (graph[x][y] == 0) {
                    copyToMap();
                }
                // 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
                else {
                    copyToDice();
                }
                sb.append(top).append("\n");
            }
        }

        System.out.print(sb);
    }

    static void copyToMap() {
        graph[x][y] = bottom;
    }

    static void copyToDice() {
        bottom = graph[x][y];
        graph[x][y] = 0;
    }

    static boolean rollDice(int d) {
        int nextX = x + dr[d]; int nextY = y + dc[d];
        if (!isInGraph(nextX, nextY)) return false;

        int tmp = bottom;
        if (d == 0) { // 동
            bottom = right;
            right = top;
            top = left;
            left = tmp;
        } else if (d == 1) { // 서
            bottom = left;
            left = top;
            top = right;
            right = tmp;
        } else if (d == 2) { // 북
            bottom = rear;
            rear = top;
            top = front;
            front = tmp;
        } else { // 남
            bottom = front;
            front = top;
            top = rear;
            rear = tmp;
        }

        x = nextX; y = nextY;

        return true;
    }

    static boolean isInGraph(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int number = Integer.parseInt(st.nextToken());
                graph[i][j] = number;
            }
        }
    }
}
