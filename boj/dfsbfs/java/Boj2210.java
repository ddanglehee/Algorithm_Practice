import java.io.*;
import java.util.*;

public class Boj2210 {

    static int[][] map = new int[5][5];
    static HashSet<String> set = new HashSet<>();
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 0, new int[6]);
            }
        }

        System.out.print(set.size());
    }

    private static void dfs(int i, int j, int count, int[] tmp) {
        tmp[count] = map[i][j];
        if (count == 5) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < 6; k++) {
                sb.append(tmp[k]);
            }
            set.add(sb.toString());
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nR = i + dr[d];
            int nC = j + dc[d];

            if (nR < 0 || 5 <= nR || nC < 0 || 5 <= nC) continue;
            dfs(nR, nC, count + 1, tmp);
        }
    }
}
