import java.util.ArrayDeque;

class PCCP2_4 {
    public int solution(int n, int m, int[][] holes) {
        int answer = -1;

        int[][] graph = new int[m + 1][n + 1];
        boolean[][][] visited = new boolean[m + 1][n + 1][2];
        for (int i = 0; i < holes.length; i++) {
            int[] hole = holes[i];
            graph[hole[1]][hole[0]] = 1;
        }

        // bfs
        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};

        ArrayDeque<Info> queue = new ArrayDeque<>();
        visited[1][1][0] = true;
        queue.add(new Info(1, 1, 0, false));

        while (!queue.isEmpty()) {
            Info info = queue.removeFirst();

            if (info.r == m && info.c == n) {
                return info.time;
            }

            for (int d = 0; d < 4; d++) {
                int nR = info.r + dr[d];
                int nC = info.c + dc[d];

                if (nR <= 0 || m < nR || nC <= 0 || n < nC) continue;

                if (info.isShoesUsed && graph[nR][nC] == 0 && !visited[nR][nC][1]) {
                    visited[nR][nC][1] = true;
                    queue.add(new Info(nR, nC, info.time + 1, true));
                } else if (!info.isShoesUsed && graph[nR][nC] == 0 && !visited[nR][nC][0]) {
                    visited[nR][nC][0] = true;
                    queue.add(new Info(nR, nC, info.time + 1, false));
                }

                if (info.isShoesUsed) continue;

                nR += dr[d];
                nC += dc[d];
                if (nR <= 0 || m < nR || nC <= 0 || n < nC || graph[nR][nC] == 1) continue;
                if (!visited[nR][nC][1]) {
                    visited[nR][nC][1] = true;
                    queue.add(new Info(nR, nC, info.time + 1, true));
                }
            }
        }

        return answer;
    }

    class Info {
        int r;
        int c;
        int time;
        boolean isShoesUsed;

        public Info(int r, int c, int time, boolean isShoesUsed) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.isShoesUsed = isShoesUsed;
        }
    }
}