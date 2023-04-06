import java.util.*;
import java.io.*;

public class Main {

    static int answer = 0;
    static int n, m;
    static int[][] graph;
    static Location[] person;
    static ArrayList<Location> baseCamp = new ArrayList<>();
    static boolean[] baseCampUsed;
    static boolean[] personInGraph;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        init();

        bfs();

        System.out.print(answer);
    }

    static void bfs() {

        Location start = findClosestBaseCamp(person[1].r, person[1].c);
        Location end = person[1];
        graph[start.r][start.c] = 1;
        personInGraph[1] = true;
        queue.add(new int[] {1, start.r, start.c, end.r, end.c});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int t = cur[0];

            // 이미 이전 시간에 도착한 경우
            if (graph[cur[3]][cur[4]] != 0) continue;

            // t초에 t번 사람 베이스캠프에 두기
            if (t <= m && !personInGraph[t]) {
                start = findClosestBaseCamp(person[t].r, person[t].c);
                end = person[t];
                graph[start.r][start.c] = t;
                personInGraph[t] = true;
                queue.add(new int[] {t, start.r, start.c, end.r, end.c});
            }

            // 원하는 편의점에 도착
            if (cur[1] == cur[3] && cur[2] == cur[4]) {
                graph[cur[3]][cur[4]] = t;
                answer = Math.max(answer, t);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nR = cur[1] + dr[d];
                int nC = cur[2] + dc[d];
                int nT = cur[0] + 1;

                if (canGo(nR, nC, nT)) {
                    queue.add(new int[] {nT, nR, nC, cur[3], cur[4]});
                }
            }
        }
    }

    static boolean canGo(int r, int c, int t) {
        return 0 < r && r <= n && 0 < c && c <= n && graph[r][c] < t;
    }

    static Location findClosestBaseCamp(int r, int c) {
        int result = -1;
        int distance = 2 * n;

        for (int i = 0; i < baseCamp.size(); i++) {
            if (baseCampUsed[i]) continue;

            int tmpDistance = getDistance(r, c, baseCamp.get(i).r, baseCamp.get(i).c);
            if (tmpDistance < distance) {
                result = i;
                distance = tmpDistance;
            }
        }

        baseCampUsed[result] = true;
        return baseCamp.get(result);
    }

    static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        person = new Location[m+1];
        personInGraph = new boolean[m+1];
        graph = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int info = Integer.parseInt(st.nextToken());
                graph[i][j] = info;
                if (info == 1) {
                    baseCamp.add(new Location(i, j));
                }
            }
        }

        baseCampUsed = new boolean[baseCamp.size()];

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Location store = new Location(r, c);
            person[i] = store;
        }
    }
}

class Location {
    int r;
    int c;

    public Location(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        Location location = (Location) o;
        return location.r == this.r && location.c == this.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}