import java.util.*;
import java.io.*;

class Boj19236 {

    static int answer = 0;
    static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[][] graph = new int[4][4];
    static int[][] fishInfo = new int[17][3];

    public static void main(String[] args) throws IOException {
        init();
        DFS(0, 0, 0);
        System.out.print(answer);
    }

    private static void DFS(int sharkR, int sharkC, int totalEat) {
        // 현재 상태 저장
        int[][] originGraph = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                originGraph[i][j] = graph[i][j];
            }
        }
        int[] originFishDirection = new int[17];
        for (int i = 1; i <= 16; i++) {
            originFishDirection[i] = fishInfo[i][2];
        }

        // 현재 위치에 있는 물고기 먹기
        int fishNumber = graph[sharkR][sharkC];
        int fishDirection = fishInfo[fishNumber][2];
        fishInfo[fishNumber][0] = -1; fishInfo[fishNumber][1] = -1; // 물고기 위치 (-1,-1)으로 바꾸기
        graph[sharkR][sharkC] = 0;

        // 물고기 이동
        for (int number = 1; number <= 16; number++) {
            int fishR = fishInfo[number][0]; int fishC = fishInfo[number][1]; int fishD = fishInfo[number][2];
            if (fishR == -1 && fishC == -1) continue;

            for (int spin = 0; spin < 8; spin++) {
                int newR = fishR + dr[(fishD + spin) % 8];
                int newC = fishC + dc[(fishD + spin) % 8];
                if (!canFishGo(newR, newC, sharkR, sharkC)) continue;
                int newD = (fishD + spin) % 8;
                moveFish(fishR, fishC, newR, newC, newD);
                break;
            }
        }

        // 상어 이동
        for (int step = 1; step < 4; step++) {
            int newR = sharkR + dr[fishDirection] * step;
            int newC = sharkC + dc[fishDirection] * step;
            if (!isInGraph(newR, newC)) break;
            if (graph[newR][newC] == 0) continue; // 새로운 위치에 물고기 없으면 이동 못함

            DFS(newR, newC, totalEat + fishNumber);
        }

        // 이전 상태로 되돌리기
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                graph[i][j] = originGraph[i][j];
            }
        }
        updateFishInfo(originFishDirection);

        answer = Math.max(answer, totalEat + fishNumber);
    }

    private static void moveFish(int r, int c, int newR, int newC, int newD) {
        int fish1 = graph[r][c]; int fish2 = graph[newR][newC];
        graph[r][c] = fish2; graph[newR][newC] = fish1;
        fishInfo[fish1][0] = newR; fishInfo[fish1][1] = newC; fishInfo[fish1][2] = newD;
        fishInfo[fish2][0] = r; fishInfo[fish2][1] = c;
    }

    private static boolean canFishGo(int r, int c, int sharkR, int sharkC) {
        return isInGraph(r, c) && (graph[r][c] != 0 || (r != sharkR || c != sharkC));
    }

    private static boolean isInGraph(int r, int c) {
        return 0 <= r && r < 4 && 0 <= c && c < 4;
    }

    private static void updateFishInfo(int[] originFishDirection) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int fishNumber = graph[i][j];
                fishInfo[fishNumber][0] = i;
                fishInfo[fishNumber][1] = j;
                fishInfo[fishNumber][2] = originFishDirection[fishNumber];
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int r = 0; r < 4; r++) {
            st = new StringTokenizer(br.readLine());
            for (int tmpC = 0; tmpC < 8; tmpC++) {
                int c = tmpC / 2;
                if (tmpC % 2 == 0) {
                    int fishNumber = Integer.parseInt(st.nextToken());
                    graph[r][c] = fishNumber;
                } else {
                    int fishNumber = graph[r][c];
                    int direction = Integer.parseInt(st.nextToken());
                    fishInfo[fishNumber][0] = r;
                    fishInfo[fishNumber][1] = c;
                    fishInfo[fishNumber][2] = direction - 1;
                }
            }
        }
    }
}
