import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int[] next1 = {3, 2, 1, 0};
    static int[] next2 = {1, 0, 3, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        StringTokenizer st;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int d = 0; d < 4; d++) {
            for (int n = 0; n < N; n++) {
                int sr, sc;
                if (d == 0) {
                    sr = N;
                    sc = n;
                } else if (d == 1) {
                    sr = n;
                    sc = N;
                } else if (d == 2) {
                    sr = -1;
                    sc = n;
                } else {
                    sr = n;
                    sc = -1;
                }

                answer = Math.max(answer, play(sr, sc, d));
            }
        }

        System.out.print(answer);
    }

    static int play(int r, int c, int d) {
        int t = 0;

        while(true) {
            int nR = r + dr[d];
            int nC = c + dc[d];

            if (!isInMap(nR, nC)) break;

            if (map[nR][nC] == 1) {
                d = next1[d];
            } else if (map[nR][nC] == 2) {
                d = next2[d];
            }

            r = nR;
            c = nC;
            t++;
        }
        
        return t + 1;
    }

    static boolean isInMap(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}