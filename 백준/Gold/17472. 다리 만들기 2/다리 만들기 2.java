import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static int[][] map;
    static int[] parent;
    static int sNumber = 1;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static List<Bridge> bridgeList = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호로 표시
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {
                    sNumber++;
                    markIsland(r, c);
                }
            }
        }

        parent = new int[sNumber + 1];
        for (int i = 2; i <= sNumber; i++) {
            parent[i] = i;
        }

        // 가로 다리 만들기
        int s;
        for (int r = 0; r < N; r++) {
            s = 0;
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0 && s != 0) {
                    c = makeBridge(s, r, c, 0);
                }
                if (isInMap(r, c)) s = map[r][c];

            }
        }
        // 세로 다리 만들기
        for (int c = 0; c < M; c++) {
            s = 0;
            for (int r = 0; r < N; r++) {
                if (map[r][c] == 0 && s != 0) {
                    r = makeBridge(s, r, c, 1);
                }
                if (isInMap(r, c)) s = map[r][c];
            }
        }

        bridgeList.sort(Comparator.comparingInt(o -> o.length));

        int bridgeCount = 0;
        for (Bridge bridge : bridgeList) {
            if (find(bridge.s1) == find(bridge.s2)) continue;

            union(bridge.s1, bridge.s2);
            answer += bridge.length;
            bridgeCount++;

            if (bridgeCount == sNumber - 2) {
                System.out.print(answer);
                return;
            }
        }
        System.out.print(-1);
    }

    static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent == bParent) return;
        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // 지금까지 처리한 index 리턴
    static int makeBridge(int s, int r, int c, int d) {
        int length = 0;

        while (isInMap(r, c) && map[r][c] == 0) {
            length++;
            r += dr[d];
            c += dc[d];
        }

        // 다리를 만들 수 있으면
        if (isInMap(r, c) && map[r][c] != 0 && 2 <= length) {
            bridgeList.add(new Bridge(s, map[r][c], length));
        }

        if (d == 0) {
            return c;
        } else {
            return r;
        }
    }

    static void markIsland(int r, int c) {
        map[r][c] = sNumber;

        for (int d = 0; d < 4; d++) {
            int nR = r + dr[d];
            int nC = c + dc[d];

            if (isInMap(nR, nC) && map[nR][nC] == 1) {
                markIsland(nR, nC);
            }
        }
    }

    static boolean isInMap(int r, int c) {
        return !(r < 0 || c < 0 || N <= r || M <= c);
    }

    static class Bridge {
        int s1;
        int s2;
        int length;

        public Bridge(int s1, int s2, int length) {
            this.s1 = s1;
            this.s2 = s2;
            this.length = length;
        }
    }
}