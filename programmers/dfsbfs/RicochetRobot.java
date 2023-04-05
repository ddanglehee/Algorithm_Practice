import java.util.*;

class RicochetRobot {

    int answer = -1;
    int[][] graph; // 0: 갈 수 있는 곳, 1: 장애물
    boolean[][] visited;
    int r, c;
    int sR, sC;
    int eR, eC;
    int[] dr = {0, 0, -1, 1};
    int[] dc = {-1, 1, 0, 0};

    public int solution(String[] board) {
        initGraph(board);

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, sR, sC});
        visited[sR][sC] = true;

        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int count = info[0]; int cR = info[1]; int cC = info[2];

            if (cR == eR && cC == eC) {
                answer = count;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int tR = cR; int tC = cC;

                int i = 1;
                while (true) {
                    if (!canGo(tR + dr[d] * i, tC + dc[d] * i)) {
                        break;
                    }
                    i++;
                }

                int nR = tR + dr[d] * (i - 1);
                int nC = tC + dc[d] * (i - 1);
                if (i != 1 && !visited[nR][nC]) {
                    visited[nR][nC] = true;
                    queue.add(new int[] {count + 1, nR, nC});
                }
            }
        }

        return answer;
    }

    boolean canGo(int nR, int nC) {
        return 0 <= nR && nR < r && 0 <= nC && nC < c && graph[nR][nC] != 1;
    }

    void initGraph(String[] board) {
        r = board.length;
        c = board[0].length();
        graph = new int[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i].charAt(j) == 'D') {
                    graph[i][j] = 1;
                } else if (board[i].charAt(j) == 'R') {
                    sR = i; sC = j;
                } else if (board[i].charAt(j) == 'G') {
                    eR = i; eC = j;
                }
            }
        }
    }
}