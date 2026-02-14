import java.util.*;
import java.io.*;

public class Main {

    static int K, N;
    static int[][] originMap;
    static int[][] map;
    static int[][] tmp;
    static int answer = Integer.MAX_VALUE;
    static int count = 0;
    static List<int[]> cases = new ArrayList<>();
    static ArrayDeque<int[]> queue = new ArrayDeque<>();
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        originMap = new int[N][N];
        map = new int[N][N];
        makeCases(0, new int[K]);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                originMap[i][j] = Integer.parseInt(st.nextToken());
                if (originMap[i][j] != 0) count++;
            }
        }

        for (int[] currentCase : cases) {
            initMap();
            for (int turn = 0; turn < K; turn++) {
                tilt(turn % 4);
                putShell(turn % 4, currentCase[turn]);
                tilt(turn % 4);
            }

            int count = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if(map[r][c] != 0) {
                        count++;
                    }
                }
            }

            answer = Math.min(answer, count);
        }

        System.out.print(answer);
    }

    static void putShell(int direction, int point) {
        if (direction == 0) {
            for (int r = 0; r < N; r++) {
                if (map[r][point] == 0) continue;
                bomb(r, point);
                break;
            }
        } else if (direction == 1) {
            for (int c = N - 1; 0 <= c; c--) {
                if (map[point][c] != 0) {
                    bomb(point, c);
                    break;
                }
            }
        } else if (direction == 2) {
            for (int r = N - 1; 0 <= r; r--) {
                if (map[r][point] != 0) {
                    bomb(r, point);
                    break;
                }
            }
        } else {
            for (int c = 0; c < N; c++) {
                if (map[point][c] != 0) {
                    bomb(point, c);
                    break;
                }
            }
        }
    }

    static void bomb(int sR, int sC) {
        queue.add(new int[] {sR, sC, map[sR][sC]});
        map[sR][sC] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            int r = cur[0];
            int c = cur[1];
            int v = cur[2];

            for (int d = 0; d < 4; d++) {
                for (int i = 1; i < v; i++) {
                    int nR = r + dr[d] * i;
                    int nC = c + dc[d] * i;
                    if (nR < 0 || nC < 0 || N <= nR || N <= nC || map[nR][nC] == 0) continue;
                    queue.add(new int[] {nR, nC, map[nR][nC]});
                    map[nR][nC] = 0;
                }
            }
        }
    }

    // 기울이기
    // direction 0: 아래로 / 1: 왼쪽으로  / 2: 위로 / 3 : 오른쪽으로
    static void tilt(int direction) {
        tmp = new int[N][N];

        if (direction == 0) {
            for(int c = 0; c < N; c++) {
                int tmpIndex = N - 1;
                for (int r = N - 1; 0 <= r; r--) {
                    if (map[r][c] == 0) continue;

                    tmp[tmpIndex--][c] = map[r][c];
                }
            }
        }
        else if (direction == 1) {
            for (int r = 0; r < N; r++) {
                int tmpIndex = 0;
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == 0) continue;

                    tmp[r][tmpIndex++] = map[r][c];
                }
            }
        }
        else if (direction == 2) {
            for (int c = 0; c < N; c++) {
                int tmpIndex = 0;
                for (int r = 0; r < N; r++) {
                    if (map[r][c] == 0) continue;

                    tmp[tmpIndex++][c] = map[r][c];
                }
            }
        }
        else {
            for (int r = 0; r < N; r++) {
                int tmpIndex = N - 1;
                for (int c = N - 1; 0 <= c; c--) {
                    if (map[r][c] == 0) continue;

                    tmp[r][tmpIndex--] = map[r][c];
                }
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = tmp[r][c];
            }
        }
    }

    static void makeCases(int index, int[] currentCase) {
        if (index == K) {
            cases.add(Arrays.copyOf(currentCase, K));
            return;
        }

        for (int i = 0; i < N; i++) {
            currentCase[index] = i;
            makeCases(index + 1, currentCase);
        }
    }

    static void initMap() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = originMap[r][c];
            }
        }
    }
}