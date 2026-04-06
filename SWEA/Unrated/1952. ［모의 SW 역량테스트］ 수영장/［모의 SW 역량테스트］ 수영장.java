import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int[] ticket = new int[4];
		int[] schedule = new int[13];
		int[] dp = new int[13];
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			Arrays.fill(dp, 1_000_000_000);
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int month = 1; month <= 12; month++) {
				schedule[month] = Integer.parseInt(st.nextToken());
			}
			
			dp[0] = 0;
			dp[1] = Math.min(ticket[0] * schedule[1], ticket[1]);
			dp[2] = dp[1] + Math.min(ticket[0] * schedule[2], ticket[1]);
			dp[12] = ticket[3];
			for (int i = 3; i <= 12; i++) {
				dp[i] = Math.min(dp[i], Math.min(dp[i - 1] + Math.min(ticket[0] * schedule[i], ticket[1]), dp[i - 3] + ticket[2]));
			}
			
			sb.append("#").append(t).append(" ").append(dp[12]).append("\n");
		}
		
		System.out.print(sb);
	}
}
