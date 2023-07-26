import java.io.*;
import java.util.*;

public class Boj1261 {

    static int n, m;
    static int[][] dist;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        System.out.print(dist[n - 1][m - 1]);
    }

    private static void dijkstra() {
        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};

        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparing(Info::getCount));
        pq.offer(new Info(0, 0, 0));

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (dist[info.r][info.c] < info.count) continue;

            for (int d = 0; d < 4; d++) {
                int nR = info.r + dr[d];
                int nC = info.c + dc[d];

                if (0 <= nR && nR < n && 0 <= nC && nC < m && info.count + graph[nR][nC] < dist[nR][nC]) {
                    dist[nR][nC] = info.count + graph[nR][nC];
                    pq.offer(new Info(nR, nC, info.count + graph[nR][nC]));
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        dist = new int[n][m];
        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = input.charAt(j) - '0';
                dist[i][j] = 10000;
            }
        }
        dist[0][0] = 0;
    }
}

class Info {
    int r;
    int c;
    int count;

    public Info(int r, int c, int count) {
        this.r = r;
        this.c = c;
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
