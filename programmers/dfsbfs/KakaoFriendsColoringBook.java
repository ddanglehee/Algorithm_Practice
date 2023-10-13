class Solution {

    int[] dr = {0, 0, 1, -1};
    int[] dc = {-1, 1, 0, 0};
    boolean[][] visited;
    int m, n;

    public int[] solution(int m, int n, int[][] picture) {
        this.m = m; this.n = n;
        visited = new boolean[m][n];
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, dfs(i, j, picture[i][j], picture));
                }
            }
        }

        int[] answer = {numberOfArea, maxSizeOfOneArea};
        return answer;
    }

    private int dfs(int i, int j, int color, int[][] picture) {
        int result = 0;
        visited[i][j] = true;

        for (int d = 0; d < 4; d++) {
            int nR = i + dr[d];
            int nC = j + dc[d];

            if (0 <= nR && nR < m && 0 <= nC && nC < n && color == picture[nR][nC] && !visited[nR][nC]) {
                result += dfs(nR, nC, color, picture);
            }
        }

        return result + 1;
    }
}