import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static int[][] originMap;
    static int[][] map;
    static int[][] tmp;
    static int answer;
    static List<int[]> selectedRowList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        originMap = new int[N][N];
        map = new int[N][N];
        tmp = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                originMap[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0, new int[K]);
        int score;
        for (int[] selectedRows : selectedRowList) {
            setMap(originMap, map);
            score = 0;
            for (int turn = 0; turn < M; turn++) {
                score += selectDoll(selectedRows);
                rotateMap();
                down();
            }
            answer = Math.max(answer, score);
        }

        System.out.print(answer);
    }

    static void down() {
        int tmpR;
        initTmp();
        for (int c = 0; c < N; c++) {
            tmpR = N - 1;
            for (int r = N - 1; 0 <= r; r--) {
                if (map[r][c] == 0) continue;
                tmp[tmpR--][c] = map[r][c];
            }
        }

        setMap(tmp, map);
    }

    static void rotateMap() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                tmp[c][N-r-1] = map[r][c];
            }
        }
        setMap(tmp, map);
    }

    static int selectDoll(int[] selectedRows) {
        int max = 0;
        int maxR = 0;
        int maxC = 0;

        for (int r : selectedRows) {
            for (int c = 0; c < N; c++) {
                if (max < map[r][c]) {
                    max = map[r][c];
                    maxR = r;
                    maxC = c;
                }
            }
        }

        map[maxR][maxC] = 0;
        return max;
    }

    static void combination(int index, int startRow, int[] rows) {
        if (index == K) {
            selectedRowList.add(Arrays.copyOf(rows, K));
            return;
        }

        for (int r = startRow; r < N; r++) {
            rows[index] = r;
            combination(index + 1, r + 1, rows);
        }
    }

    static void setMap(int[][] from, int[][] to) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                to[r][c] = from[r][c];
            }
        }
    }

    static void initTmp() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                tmp[r][c] = 0;
            }
        }
    }
}