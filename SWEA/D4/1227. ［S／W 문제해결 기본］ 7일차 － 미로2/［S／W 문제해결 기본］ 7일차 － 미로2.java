import java.io.*;
import java.util.*;

public class Solution {
	
	static int[][] map = new int[100][100];
	static int[][] visited = new int[100][100];
	static int sR, sC;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static Queue<Point> queue = new ArrayDeque<>();
	
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
			
			sb.append("#").append(t).append(" ").append(bfs(sR, sC, t)).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int bfs(int r, int c, int t) {
		queue.clear();
		queue.add(new Point(r, c));
		visited[r][c] = t;
		
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nR = cur.r + dr[d];
	            int nC = cur.c + dc[d];
	             
	            if (nR < 0 || nC < 0 || nR >= 100 || nC >= 100 || visited[nR][nC] == t || map[nR][nC] == 1) continue;
	            
	            if (map[nR][nC] == 3) return 1;
	            queue.add(new Point(nR, nC));
	            visited[nR][nC] = t;
			}
		}
		
		return 0;
	}
	
	static class Point {
		int r;
		int c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
