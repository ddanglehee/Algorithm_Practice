import java.io.*;
import java.util.Arrays;

public class Solution {
	
	static int[][] map = new int[100][100];
	static int[][] visited = new int[100][100];
	static int sR, sC;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		String row;
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			
			for (int i = 0; i < 100; i++) {
				row = br.readLine();
				for (int j = 0; j < 100; j++) {
					map[i][j] = row.charAt(j) - '0';
					if (map[i][j] == 2) {
						sR = i; sC = j;
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(dfs(sR, sC, t)).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int dfs(int r, int c, int t) {
		visited[r][c] = t;
		
		for (int d = 0; d < 4; d++) {
			int nR = r + dr[d];
			int nC = c + dc[d];
			
			if (nR < 0 || nC < 0 || nR >= 100 || nC >= 100 || visited[nR][nC] == t || map[nR][nC] == 1) continue;

			if (map[nR][nC] == 3 || dfs(nR, nC, t) == 1) return 1;
		}
		
		return 0;
	}
}
