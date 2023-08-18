import java.util.*;
import java.io.*;

public class Boj3190 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, l;
    static int[][] graph;
    static ArrayDeque<int[]> snake = new ArrayDeque<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int time = 0;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            while (time < x) {
                if (!moveSnake()) return;
            }

            turnHead(d);
        }

        while (moveSnake()) {}
    }

    private static boolean moveSnake() {
        time++;
        int[] head = snake.getFirst();
        int nR = head[0] + dr[head[2]];
        int nC = head[1] + dc[head[2]];

        if (nR < 1 || n < nR || nC < 1 || n < nC || graph[nR][nC] == 2) {
            System.out.print(time);
            return false;
        }

        if (graph[nR][nC] == 0) { // 이동한 칸에 사과가 없다면
            int[] tail = snake.removeLast();
            graph[tail[0]][tail[1]] = 0;
        }
        snake.addFirst(new int[] {nR, nC, head[2]});
        graph[nR][nC] = 2;

        return true;
    }

    private static void turnHead(String d) {
        int[] head = snake.getFirst();
        if (Objects.equals(d, "L")) {
            head[2] = (head[2] + 4 - 1) % 4;
        } else {
            head[2] = (head[2] + 1) % 4;
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        graph = new int[n + 1][n + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[r][c] = 1; // 사과
        }

        snake.add(new int[] {1, 1, 0});
        graph[1][1] = 2; // 뱀
        l = Integer.parseInt(br.readLine());
    }
}
