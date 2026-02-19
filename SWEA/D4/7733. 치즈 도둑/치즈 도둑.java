import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[][] map = new int[100][100];
	static int lastDay;
	static ArrayList<int[]>[] locations = new ArrayList[101];
	static int answer;
	static boolean[][] visited = new boolean[100][100];
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= 100; i++) {
			locations[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			init();
			N = Integer.parseInt(br.readLine());
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					locations[map[r][c]].add(new int[] {r, c});
					lastDay = Math.max(lastDay, map[r][c]);
				}
			}
			
			for (int day = 1; day <= lastDay; day++) {
				initVisited();
				for (int[] location : locations[day]) {
					map[location[0]][location[1]] = 0;
				}
				
				int count = 0;
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (map[r][c] != 0 && !visited[r][c]) {
							dfs(r, c);
							count++;
						}
					}
				}
				
				answer = Math.max(answer, count);
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nR = r + dr[d];
			int nC = c + dc[d];
			
			if (nR < 0 || nC < 0 || nR >= N || nC >= N || map[nR][nC] == 0 || visited[nR][nC]) continue;
			dfs(nR, nC);
		}
	}
	
	static void init() {
		for (int i = 1; i <= 100; i++) {
			locations[i].clear();
		}

		answer = 1;
		lastDay = 0;
	}
	
	static void initVisited() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
	}
}
