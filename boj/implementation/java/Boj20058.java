import java.util.*;
import java.io.*;

public class Boj20058 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, q;
    static int[][] a;
    static boolean[][] visited;
    static int ice = 0;
    static int maxIce = 0;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        init();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            int l = Integer.parseInt(st.nextToken());

            rotate90(l);
            checkIce();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0 || visited[i][j]) continue;
                maxIce = Math.max(maxIce, dfs(i, j));
            }
        }
        System.out.println(ice);
        System.out.print(maxIce);
    }

    private static int dfs(int i, int j) {
        visited[i][j] = true;

        int count = 1;
        for (int d = 0; d < 4; d++) {
            int nR = i + dr[d];
            int nC = j + dc[d];

            if (nR < 0 || n <= nR || nC < 0 || n <= nC || a[nR][nC] == 0 || visited[nR][nC]) continue;
           count += dfs(nR, nC);
        }

        return count;
    }

    private static void checkIce() {
        int[][] newA = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newA[i][j] = a[i][j];
                int iceCount = 0;

                for (int d = 0; d < 4; d++) {
                    int nR = i + dr[d];
                    int nC = j + dc[d];

                    if (nR < 0 || n <= nR || nC < 0 || n <= nC) continue;
                    if (a[nR][nC] != 0) {
                        iceCount++;
                    }
                }

                if (iceCount < 3 && a[i][j] != 0) {
                    newA[i][j] = a[i][j] - 1;
                    ice--;
                }
            }
        }

        a = newA;
    }

    private static void rotate90(int l) {
        l = pow2(l);

        int[][] newA = new int[n][n];
        for (int r = 0; r < n; r += l) {
            for (int c = 0; c < n; c += l) {
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < l; j++) {
                        int curR = r + i;
                        int curC = c + j;
                        int curIce = a[curR][curC];

                        int nxtR = j + r;
                        int nxtC = l - 1 - i + c;
                        newA[nxtR][nxtC] = curIce;
                    }
                }
            }
        }

        a = newA;
    }

    private static int pow2(int x) {
        return (int) Math.pow(2, x);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        n = pow2(n);
        a = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                ice += a[i][j];
            }
        }
    }
}
