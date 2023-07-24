import java.io.*;
import java.util.*;

public class Boj16236 {

    private static int n;
    private static int[][] graph;
    private static int[] babyShark;
    private static int babySharkSize = 2;
    private static int babySharkEat = 0;
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        init();

        while (true) {
            if (!bfs()) break;
        }

        System.out.print(time);
    }

    private static boolean bfs() {
        int[] dr = {-1, 0, 0, 1};
        int[] dc = {0, -1, 1, 0};
        boolean[][] visited = new boolean[n][n];
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, babyShark[0], babyShark[1]});

        while (!queue.isEmpty()) {
            int[] info = queue.removeFirst();
            int t = info[0]; int r = info[1]; int c = info[2];

            if (graph[r][c] != 0 && graph[r][c] < babySharkSize) {
                while (!queue.isEmpty()) {
                    int[] info2 = queue.removeFirst();
                    int t2 = info2[0]; int r2 = info2[1]; int c2 = info2[2];
                    if (t < t2) break;
                    if (graph[r2][c2] != 0 && graph[r2][c2] < babySharkSize && ((r2 < r) || ((r2 == r) && (c2 < c)))) {
                        r = r2; c = c2;
                    }
                }
                graph[r][c] = 0;
                babySharkEat++;
                time += t;
                if (babySharkEat == babySharkSize) {
                    babySharkSize++;
                    babySharkEat = 0;
                }
                babyShark[0] = r; babyShark[1] = c;
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int nR = r + dr[d];
                int nC = c + dc[d];

                if (0 <= nR && nR < n && 0 <= nC && nC < n && !visited[nR][nC] && graph[nR][nC] <= babySharkSize) {
                    visited[nR][nC] = true;
                    queue.add(new int[] {t + 1, nR, nC});
                }
            }
        }

        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int fish = Integer.parseInt(st.nextToken());

                if (fish == 9) {
                    babyShark = new int[] {i, j};
                } else {
                    graph[i][j] = fish;
                }
            }
        }
    }
}
