import java.io.*;
import java.util.*;

public class Main {
	
	static int N, T;
	static int[] studyTime;
	static int[] score;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		studyTime = new int[N + 1];
		score = new int[N + 1];
		dp = new int[N + 1][T + 1];
		
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			
			studyTime[n] = Integer.parseInt(st.nextToken());
			score[n] = Integer.parseInt(st.nextToken());
		}
		
		for (int n = 1; n <= N; n++) {
			for (int t = 1; t <= T; t++) {
				if (t < studyTime[n]) {
					dp[n][t] = dp[n-1][t]; // n번 과목은 공부하지 않음
				} else {
					dp[n][t] = Math.max(dp[n-1][t], dp[n-1][t - studyTime[n]] + score[n]); // n번 과목 공부하는 거랑, 안하는 거랑 얻는 점수 최대 저장
				}
			}
		}
		
		System.out.print(dp[N][T]);
	}
}
