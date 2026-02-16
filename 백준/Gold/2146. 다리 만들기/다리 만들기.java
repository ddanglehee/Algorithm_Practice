import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();
    static ArrayDeque<int[]> bridgeQueue = new ArrayDeque<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int answer = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 찾으면 -> -1로 표시
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 1) {
                    bridgeQueue.clear();
                    initVisited();
                    checkIsland(r, c);
                    setBridge();
                }
            }
        }

        System.out.print(answer);
    }

    static void checkIsland(int sR, int sC) {
        queue.add(new int[] {sR, sC});
        visited[sR][sC] = true;
        map[sR][sC] = -1;

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();

            for (int d = 0; d < 4; d++) {
                int nR = cur[0] + dr[d];
                int nC = cur[1] + dc[d];

                if (!isInMap(nR, nC) || visited[nR][nC]) continue;

                visited[nR][nC] = true;
                if (map[nR][nC] == 1) {
                    map[nR][nC] = -1; // 이미 처리한 섬은 -1로 처리 (중복 계산 막기 위해)
                    queue.add(new int[] {nR, nC});
                } else if (map[nR][nC] == 0) {
                    bridgeQueue.add(new int[] {nR, nC, 1});
                }
            }
        }
    }

    static void setBridge() {
        while (!bridgeQueue.isEmpty()) {
            int[] cur = bridgeQueue.removeFirst();

            for (int d = 0; d < 4; d++) {
                int nR = cur[0] + dr[d];
                int nC = cur[1] + dc[d];

                if (!isInMap(nR, nC) || visited[nR][nC]) continue;

                visited[nR][nC] = true;
                if (map[nR][nC] == 0) {
                    if (cur[2] == answer) return; // 이미 세워본 다리보다 길이 길면 답이 아님
                    bridgeQueue.add(new int[] {nR, nC, cur[2] + 1});
                }
                else if (map[nR][nC] == 1) {
                    answer = cur[2];
                    return;
                }
            }
        }
    }

    static boolean isInMap(int r, int c) {
        return !(r < 0 || c < 0 || N <= r || N <= c);
    }

    static void initVisited() {
        for (int r = 0; r < N; r++) {
            Arrays.fill(visited[r], false);
        }
    }
}
