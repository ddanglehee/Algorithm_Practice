import java.util.*;
import java.io.*;

class Boj14890 {

    static int answer = 0;
    static int n;
    static int l;
    static int[][] graph;
    static int[] road;
    static boolean[] isRunwaySet;

    public static void main(String[] args) throws IOException {
        init();

        // 가로
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                road[c] = graph[r][c];
            }
            initIsRunwaySet();
            checkRoad(road);
        }

        // 세로
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n; r++) {
                road[r] = graph[r][c];
            }
            initIsRunwaySet();
            checkRoad(road);
        }

        System.out.println(answer);
    }

    static void checkRoad(int[] road) {
        int preHeight = road[0];
        int c = 1;

        while (c < n) {
            // 이전과 높이 같으면 다른 처리 안해줘도 된다.
            if (preHeight == road[c]) {
                c++;
                continue;
            }
            if (preHeight == road[c] - 1) {
                if (!setRunway(road, c - 1, -1)) break;
                preHeight = road[c++];
            } else if (preHeight - 1 == road[c]) {
                if (!setRunway(road, c, 1)) break;
                c += l;
                preHeight = road[c - 1];
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

    static boolean setRunway(int[] road, int c, int step) {
        // 경사로를 놓다가 범위를 벗어나는 경우
        if (!isInLine(c + (step * l) - step)) return false;

        int height = road[c];
        for (int i = 0; i < l; i++) {
            int setC = c + (i * step);
            // 낮은 지점의 칸의 높이가 모두 같지 않거나, L가 연속되지 않은 경우거나
            // 경사로를 놓은 곳에 또 경사로를 놓는 경우
            if (height != road[setC] || isRunwaySet[setC]) return false;
        }

        for (int i = 0; i < l; i++) {
            isRunwaySet[c + (i * step)] = true;
        }

        return true;
    }

    static boolean isInLine(int c) {
        return 0 <= c && c < n;
    }

    static void initIsRunwaySet() {
        for (int i = 0; i < n; i++) {
            isRunwaySet[i] = false;
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        road = new int[n];
        isRunwaySet = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int height = Integer.parseInt(st.nextToken());
                graph[i][j] = height;
            }
        }
    }
}