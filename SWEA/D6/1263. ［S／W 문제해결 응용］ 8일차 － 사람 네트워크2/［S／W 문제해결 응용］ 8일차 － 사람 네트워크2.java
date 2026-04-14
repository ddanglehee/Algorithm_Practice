import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int minCC;
	static int[][] dist = new int[1000][1000];
	static final int INF = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			for (int r = 0; r < N; r++) {
				Arrays.fill(dist[r], INF);
				for (int c = 0; c < N; c++) {
					int input = Integer.parseInt(st.nextToken());
					if (input == 1) {
						dist[r][c] = input;
					}
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int r = 0; r < N; r++) {
					if (dist[r][k] == INF) continue;
					for (int c = 0; c < N; c++) {
						dist[r][c] = Math.min(dist[r][c], dist[r][k] + dist[k][c]);
					}
				}				
			}

			minCC = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int cc = 0;
				for (int j = 0; j < N; j++) {
					if (i == j) continue;
					cc += dist[i][j];
				}
				
				minCC = Math.min(minCC, cc);
			}
			sb.append("#").append(t).append(" ").append(minCC).append("\n");
		}
		System.out.print(sb);
	}
}
