import java.util.*;
import java.io.*;

public class Boj2638 {

    static int n, m;
    static int time = 0;
    static int[][] graph;
    static boolean[][] isOutside;
    static LinkedList<int[]> cheeseList = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        init();

        while (!cheeseList.isEmpty()) {
            time++;

            // 1. 실제 외부 공간 표시
            initIsOutside();
            checkOutside();

            // 2. 치즈 위치 순회하면서 녹는 치즈 확인
            Iterator<int[]> iter = cheeseList.iterator();
            while (iter.hasNext()) {
                int[] cur = iter.next();
                int i = cur[0]; int j = cur[1];

                int outsideCount = 0;
                for (int d = 0; d < 4; d++) {
                    int nR = i + dr[d];
                    int nC = j + dc[d];

                    if (nR < 0 || n <= nR || nC < 0 || m <= nC) continue;
                    if (isOutside[nR][nC]) outsideCount++;
                }

                if (2 <= outsideCount) {
                    // 치즈 녹음
                    iter.remove();
                    graph[i][j] = 0;
                }
            }
        }

        System.out.print(time);
    }

    private static void checkOutside() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        isOutside[0][0] = true;
        queue.add(new int[] {0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();

            for (int d = 0; d < 4; d++) {
                int nR = cur[0] + dr[d];
                int nC = cur[1] + dc[d];

                if (0 <= nR && nR < n && 0 <= nC && nC < m && graph[nR][nC] == 0 && !isOutside[nR][nC]) {
                    isOutside[nR][nC] = true;
                    queue.add(new int[] {nR, nC});
                }
            }
        }
    }

    private static void initIsOutside() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                isOutside[i][j] = false;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        isOutside = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int info = Integer.parseInt(st.nextToken());

                if (info == 1) {
                    cheeseList.add(new int[]{i, j});
                }
                graph[i][j] = info;
            }
        }
    }
}
