import java.io.*;
import java.util.*;

public class Boj17484 {

    static int answer = Integer.MAX_VALUE;
    static int n, m;
    static int[][] map;
    static int[] dc = {-1, 0, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int c = 0; c < m; c++) {
            dfs(0, c, -1, map[0][c]);
        }

        System.out.println(answer);
    }

    private static void dfs(int r, int c, int preD, int cost) {
        if (r == n - 1) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int d = 0; d < 3; d++) {
            if (preD == d) continue;
            int nR = r + 1;
            int nC = c + dc[d];
            if (0 <= nR && nR < n && 0 <= nC && nC < m) {
                dfs(nR, nC, d, cost + map[nR][nC]);
            }
        }
    }
}
