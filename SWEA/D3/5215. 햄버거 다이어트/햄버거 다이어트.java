import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, L;
	static int[] t = new int[1000];
	static int[] k = new int[1000];
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;

			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				t[i] = Integer.parseInt(st.nextToken());
				k[i] = Integer.parseInt(st.nextToken());
			}
			solution(0, 0, 0);
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	static void solution(int index, int curKcal, int curScore) {
		
		if (index == N) {
			if (curKcal <= L) answer = Math.max(answer, curScore);
			return;
		}
		
		solution(index + 1, curKcal + k[index], curScore + t[index]);
		solution(index + 1, curKcal, curScore);
	}
}
