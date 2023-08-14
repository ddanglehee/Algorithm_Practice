import java.util.*;
import java.io.*;

public class Boj6593 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int l, r, c;
    static char[][][] graph = new char[30][30][30];
    static boolean[][][] visited = new boolean[30][30][30];
    static int[] start = new int[3];
    static int[] end = new int[3];
    static int[] dh = {1, -1, 0, 0, 0, 0};
    static int[] dr = {0, 0, 1, -1, 0, 0};
    static int[] dc = {0, 0, 0, 0, 1, -1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (init()) {
            int result = bfs();
            if (result == -1) {
                sb.append("Trapped!");
            } else {
                sb.append("Escaped in ").append(result).append(" minute(s).");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited[start[0]][start[1]][start[2]] = true;
        queue.add(new int[] {0, start[0], start[1], start[2]});

        while (!queue.isEmpty()) {
            int[] info = queue.removeFirst();

            if (info[1] == end[0] && info[2] == end[1] && info[3] == end[2]) {
                return info[0];
            }

            for (int d = 0; d < 6; d++) {
                int nH = info[1] + dh[d];
                int nR = info[2] + dr[d];
                int nC = info[3] + dc[d];

                if (0 <= nH && nH < l && 0 <= nR && nR < r && 0 <= nC && nC < c && graph[nH][nR][nC] != '#' && !visited[nH][nR][nC]) {
                    visited[nH][nR][nC] = true;
                    queue.add(new int[] {info[0] + 1, nH, nR, nC});
                }
            }
        }

        return -1;
    }

    private static boolean init() throws IOException {
        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        if (l == 0 && r == 0 && c == 0) return false;

        initVisited();
        for (int k = 0; k < l; k++) {
            for (int i = 0; i < r; i++) {
                String input = br.readLine();
                for (int j = 0; j < c; j++) {
                    graph[k][i][j] = input.charAt(j);
                    if (graph[k][i][j] == 'S') {
                        start[0] = k; start[1] = i; start[2] = j;
                    } else if (graph[k][i][j] == 'E') {
                        end[0] = k; end[1] = i; end[2] = j;
                    }
                }
            }
            br.readLine();
        }

        return true;
    }

    private static void initVisited() {
        for (int k = 0; k < l; k++) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    visited[k][i][j] = false;
                }
            }
        }
    }
}
