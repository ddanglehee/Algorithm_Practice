import java.util.*;
import java.io.*;

class Boj17144 {

    static int r;
    static int c;
    static int t;
    static int[][] dustAmount;
    static int[][] diffusedDust;
    static int totalDust = 0;
    static int r1;  // 공기청정기1 위치
    static int r2;  // 공기청정기2 위치
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        init();

        for (int time = 1; time <= t; time++) {
            // 확산
            diffuseDust();
            // 공기청정기 작동
            runAirCleaner();
        }

        System.out.print(totalDust);
    }

    static void diffuseDust() {
        initDiffusedDust();

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (0 < dustAmount[i][j]) {
                    int totalDiffusedAmount = 0;
                    int diffusedAmount = dustAmount[i][j] / 5; // 확산되는 양은 Arc/5이고 소수점은 버린다.
                    // 미세먼지는 인접한 네 방향으로 확산된다.
                    for (int d = 0; d < 4; d++) {
                        int newR = i + dr[d]; int newC = j + dc[d];
                        // 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
                        if (!isInGraph(newR, newC) || dustAmount[newR][newC] == -1) continue;
                        diffusedDust[newR][newC] += diffusedAmount;
                        totalDiffusedAmount += diffusedAmount;
                    }
                    dustAmount[i][j] -= totalDiffusedAmount; // (r, c)에 남은 미세먼지 양
                }
            }
        }

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                dustAmount[i][j] += diffusedDust[i][j];
            }
        }
    }

    static void runAirCleaner() {
        totalDust -= dustAmount[r1 - 1][1];
        totalDust -= dustAmount[r2 + 1][1];

        // ↓ (시계방향)
        for (int i = r1 - 2; 1 <= i; i--) {
            dustAmount[i + 1][1] = dustAmount[i][1];
        }

        // ↑ (반시계방향)
        for (int i = r2 + 2; i <= r; i++) {
            dustAmount[i - 1][1] = dustAmount[i][1];
        }

        // ← (공통)
        for (int j = 2; j <= c; j++) {
            dustAmount[1][j - 1] = dustAmount[1][j];
            dustAmount[r][j - 1] = dustAmount[r][j];
        }

        // ↑ (시계방향)
        for (int i = 2; i <= r1; i++) {
            dustAmount[i - 1][c] = dustAmount[i][c];
        }

        // ↓ (반시계방향)
        for (int i = r - 1; r2 <= i; i--) {
            dustAmount[i + 1][c] = dustAmount[i][c];
        }

        // → (공통)
        for (int j = c - 1; 2 <= j; j--) {
            dustAmount[r1][j + 1] = dustAmount[r1][j];
            dustAmount[r2][j + 1] = dustAmount[r2][j];
        }
        dustAmount[r1][2] = 0; dustAmount[r2][2] = 0;
    }

    static boolean isInGraph(int i, int j) {
        return 1 <= i && i <= r && 1 <= j && j <= c;
    }

    static void initDiffusedDust() {
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                diffusedDust[i][j] = 0;
            }
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        dustAmount = new int[r + 1][c + 1];
        diffusedDust = new int[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= c; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == -1) {
                    r1 = i - 1; r2 = i;
                } else {
                    totalDust += info;
                }
                dustAmount[i][j] = info;
            }
        }
    }
}