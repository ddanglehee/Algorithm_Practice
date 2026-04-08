import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, K;
	static int[][] dp = new int[2][1001];
	static int[] v = new int[101];
	static int[] c = new int[101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				v[n] = Integer.parseInt(st.nextToken());
				c[n] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.fill(dp[0], 0);
			Arrays.fill(dp[1], 0);
			for (int n = 1; n <= N; n++) {
				for (int k = 1; k <= K; k++) {
					int i = n % 2;
					int j = (n - 1) % 2;
					
					if (k < v[n]) {
						dp[i][k] = dp[j][k];
					} else {
						dp[i][k] = Math.max(dp[j][k], dp[j][k - v[n]] + c[n]);
					}
				}
				
			}
			
			sb.append("#").append(t).append(" ").append(dp[N % 2][K]).append("\n");
		}
		
		System.out.print(sb);
	}
}
