import java.util.*;
import java.io.*;

class Boj23288 {

    private static int answer = 0;
    private static int n, m, k;
    private static int[][] graph;
    private static boolean[][] visited;
    private static int[] dr = {0, 1, 0, -1};
    private static int[] dc = {1, 0, -1, 0};
    private static int front = 5; private static int rear = 2;
    private static int top = 1; private static int bottom = 6;
    private static int right = 3; private static int left = 4;
    private static int curR = 1; private static int curC = 1;
    private static int d = 0;

    public static void main(String[] args) throws IOException {
        init();

        for (int step = 0; step < k; step++) {
            // 1. 이동 방향으로 한 칸 굴러한다.
            int nextR = curR + dr[d]; int nextC = curC + dc[d];
            if (!isInGraph(nextR, nextC)) {
                d = (d + 2) % 4;
                nextR = curR + dr[d]; nextC = curC + dc[d];
            }
            rollDice(d);
            curR = nextR; curC = nextC;

            // 2. (x, y)에 대한 점수 획득
            int score = graph[curR][curC];
            initVisited();
            answer += moveCount(score, curR, curC) * score;

            // 3. 이동 방향 바꾸기
            if (score < bottom) {
                d = (d + 1) % 4;
            } else if (bottom < score) {
                if (d == 0) {
                    d = 3;
                } else {
                    d -= 1;
                }
            }
        }

        System.out.print(answer);
    }

    private static int moveCount(int score, int curR, int curC) {
        int result = 1;
        visited[curR][curC] = true;

        for (int i = 0; i < 4; i++) {
            int nextR = curR + dr[i]; int nextC = curC + dc[i];
            if (isInGraph(nextR, nextC) && graph[nextR][nextC] == score && !visited[nextR][nextC]) {
                result += moveCount(score, nextR, nextC);
            }
        }

        return result;
    }

    private static void rollDice(int d) {
        int tmp = bottom;
        if (d == 0) { // 동
            bottom = right;
            right = top;
            top = left;
            left = tmp;
        } else if (d == 1) { // 남
            bottom = front;
            front = top;
            top = rear;
            rear = tmp;
        } else if (d == 2) { // 서
            bottom = left;
            left = top;
            top = right;
            right = tmp;
        } else { // 북
            bottom = rear;
            rear = top;
            top = front;
            front = tmp;
        }
    }

    private static boolean isInGraph(int r, int c) {
        return 1 <= r && r <= n && 1 <= c && c <= m;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                int score = Integer.parseInt(st.nextToken());
                graph[i][j] = score;
            }
        }
    }

    private static void initVisited() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                visited[i][j] = false;
            }
        }
    }
}