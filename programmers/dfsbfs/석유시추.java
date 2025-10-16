import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

class Solution {

    private int n, m;
    private int[] oilAmount;
    private int[] dr = {0, 1, 0, -1};
    private int[] dc = {1, 0, -1, 0};
    private Set<Integer> columnSet = new HashSet<>();

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        oilAmount = new int[m];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (land[r][c] == 0) continue;

                columnSet.clear();
                bfs(r, c, land);
            }
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, oilAmount[i]);
        }

        return answer;
    }

    private  bfs(int sr, int sc, int[][] land) {
        int count = 1;

        land[sr][sc] = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sr, sc});
        columnSet.add(sc);

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            int r = cur[0]; int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nR = r + dr[d];
                int nC = c + dc[d];

                if (0 <= nR && nR < n && 0 <= nC && nC < m && land[nR][nC] == 1) {
                    land[nR][nC] = 0;
                    columnSet.add(nC);
                    count++;
                    queue.add(new int[] {nR, nC});
                }
            }
        }

        for (int column : columnSet) {
            oilAmount[column] += count;
        }
    }
}