import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Secret1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static int R, C;
    static int[][] graph = new int[21][21];
    static boolean[][] visited = new boolean[21][21];
    static boolean[] alpha = new boolean[26];
    static int answer = 0;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String args[]) throws Exception {

        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            init();
            travel(new Location(1, 1), 1);
            System.out.println("#" + test_case + " " + answer);
        }
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            String columnItemInfo = br.readLine();

            for (int c = 0; c < columnItemInfo.length(); c++) {
                graph[i + 1][c + 1] = columnItemInfo.charAt(c) - 'A';
                visited[i + 1][c + 1] = false;
            }
        }

        for (int i = 0; i < 26; i++) {
            alpha[i] = false;
        }

        answer = 0;
    }

    static void travel(Location currentLocation, int count) {

        int currentR = currentLocation.row;
        int currentC = currentLocation.column;

        alpha[graph[currentR][currentC]] = true;
        visited[currentR][currentC] = true;

        if (answer < count) answer = count;

        for (int i = 0; i < 4; i++) {
            int tmpR = currentLocation.row + dr[i];
            int tmpC = currentLocation.column + dc[i];

            if (1 <= tmpR && tmpR <= R && 1 <= tmpC && tmpC <= C) {
                if (alpha[graph[tmpR][tmpC]] || visited[tmpR][tmpC]) continue;
                travel(new Location(tmpR, tmpC), count + 1);
            }
        }

        alpha[graph[currentR][currentC]] = false;
        visited[currentR][currentC] = false;
    }

    static class Location {
        private int row;
        private int column;

        public Location(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
