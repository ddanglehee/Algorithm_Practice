import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[][] map = new int[20][20];
	static boolean[] eaten = new boolean[101];
	static int[] dr = {-1, 1, 1, -1};
	static int[] dc = {1, 1, -1, -1};
	static int sR, sC;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			answer = -1;
			N = Integer.parseInt(br.readLine());
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int r = 1; r < N - 1; r++) {
				for (int c = 0; c < N - 2; c++) {
					sR = r;
					sC = c;
					dfs(r + dr[0], c + dc[0], 0, 1);
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	static void dfs(int r, int c, int d, int count) {
		if (r == sR && c == sC) {
			answer = Math.max(answer, count);
			return;
		}
		
		eaten[map[r][c]] = true;
		
		int nR = r + dr[d];
		int nC = c + dc[d];
		if (isInMap(nR, nC) && !eaten[map[nR][nC]]) {
			dfs(nR, nC, d, count + 1);
		}
		
		if (d != 3) {
			int nD = d + 1;
			nR = r + dr[nD];
			nC = c + dc[nD];
			if (isInMap(nR, nC) && !eaten[map[nR][nC]]) {
				dfs(nR, nC, nD, count + 1);
			}
		}
		
		eaten[map[r][c]] = false;
	}
	
	
	static boolean isInMap(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}
}
