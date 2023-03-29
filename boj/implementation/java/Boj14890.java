import java.util.*;
import java.io.*;

class Boj14890 {

    static int answer = 0;
    static int n;
    static int l;
    static int[][] graph;
    static boolean[][] isRunwaySet;

    public static void main(String[] args) throws IOException {
        init();

        // 가로
        for (int r = 0; r < n; r++) {
            int preHeight = graph[r][0];
            int c = 1;

            while (c < n) {
                // 이전과 높이 같으면 다른 처리 안해줘도 된다.
                if (preHeight == graph[r][c]) {
                    c++;
                    continue;
                }
                if (preHeight == graph[r][c] - 1) {
                    if (!setRunwayColumn(r, c - 1, -1)) break;
                    preHeight = graph[r][c++];
                } else if (preHeight - 1 == graph[r][c]) {
                    if (!setRunwayColumn(r, c, 1)) break;
                    c += l;
                    preHeight = graph[r][c - 1];
                }
                // 낮은 칸과 높은 칸의 높이 차이가 1이 아닌 경우
                else {
                    break;
                }
            }
            if (c == n) {
                answer++;
            }
        }

        initIsRunwaySet();

        // 세로
        for (int c = 0; c < n; c++) {
            int preHeight = graph[0][c];
            int r = 1;

            while (r < n) {
                if (preHeight == graph[r][c]) {
                    r++;
                    continue;
                }
                if (preHeight == graph[r][c] - 1) {
                    if (!setRunwayRow(r - 1, c, -1)) break;
                    preHeight = graph[r++][c];
                } else if (preHeight - 1 == graph[r][c]) {
                    if (!setRunwayRow(r, c, 1)) break;
                    r += l;
                    preHeight = graph[r - 1][c];
                }
                else {
                    break;
                }
            }
            if (r == n) {
                answer++;
            }

        }

        System.out.println(answer);
    }

    static boolean setRunwayRow(int r, int c, int step) {
        // 경사로를 놓다가 범위를 벗어나는 경우
        if (!isInGraph(r + (step * l) - step, c)) return false;

        int height = graph[r][c];
        for (int i = 0; i < l; i++) {
            int setR = r + (i * step);
            // 낮은 지점의 칸의 높이가 모두 같지 않거나, L가 연속되지 않은 경우거나
            // 경사로를 놓은 곳에 또 경사로를 놓는 경우
            if (height != graph[setR][c] || isRunwaySet[setR][c]) return false;
        }

        for (int i = 0; i < l; i++) {
            isRunwaySet[r + (i * step)][c] = true;
        }

        return true;
    }

    static boolean setRunwayColumn(int r, int c, int step) {
        // 경사로를 놓다가 범위를 벗어나는 경우
        if (!isInGraph(r, c + (step * l) - step)) return false;

        int height = graph[r][c];
        for (int i = 0; i < l; i++) {
            int setC = c + (i * step);
            // 낮은 지점의 칸의 높이가 모두 같지 않거나, L가 연속되지 않은 경우거나
            // 경사로를 놓은 곳에 또 경사로를 놓는 경우
            if (height != graph[r][setC] || isRunwaySet[r][setC]) return false;
        }

        for (int i = 0; i < l; i++) {
            isRunwaySet[r][c + (i * step)] = true;
        }

        return true;
    }

    static boolean isInGraph(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    static void initIsRunwaySet() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                isRunwaySet[i][j] = false;
            }
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        isRunwaySet = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int height = Integer.parseInt(st.nextToken());
                graph[i][j] = height;
            }
        }
    }
}