import java.util.*;
import java.io.*;

public class Boj15683 {

    static int n, m;
    static int[][] graph;
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][][] directions = { {{}}, // 0
            {{0}, {1}, {2}, {3}}, // 1
            {{1, 3}, {0, 2}}, // 2
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4
            {{0, 1, 2, 3}} // 5
    };
    static int answer = 64;

    public static void main(String[] args) throws IOException {
        init();

        if (cctvList.size() == 0) {
            updateAnswer();
        } else {
            solution(0);
        }

        System.out.print(answer);
    }

    static void solution(int cctvIndex) {
        if (cctvList.size() == cctvIndex) {
            updateAnswer();
            return;
        }

        CCTV cctv = cctvList.get(cctvIndex);

        for (int i = 0; i < directions[cctv.type].length; i++) {
            ArrayList<int[]> monitoringList = new ArrayList<>();

            for (int j = 0; j < directions[cctv.type][i].length; j++) {
                monitoring(directions[cctv.type][i][j], cctv.r, cctv.c, monitoringList);
            }
            solution(cctvIndex + 1);

            for (int[] location: monitoringList) {
                graph[location[0]][location[1]] = 0;
            }
        }
    }

    static void updateAnswer() {
        int tmp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) {
                    tmp++;
                }
            }
        }

        answer = Math.min(answer, tmp);
    }

    static void monitoring(int d, int r, int c, ArrayList<int[]> monitoringList) {
        int i = 1;

        while (true) {
            int nR = r + dr[d] * i;
            int nC = c + dc[d] * i;

            if (nR < 0 || n <= nR || nC < 0 || m <= nC || graph[nR][nC] == 6) {
                break;
            }

            if (graph[nR][nC] == 0) {
                graph[nR][nC] = -1;
                monitoringList.add(new int[] {nR, nC});
            }

            i++;
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int info = Integer.parseInt(st.nextToken());
                graph[i][j] = info;

                if (1 <= info && info <= 5) {
                    cctvList.add(new CCTV(info, i, j));
                }
            }
        }
    }

    static class CCTV {
        int type;
        int r;
        int c;

        public CCTV(int type, int r, int c) {
            this.type = type;
            this.r = r;
            this.c = c;
        }
    }
}
