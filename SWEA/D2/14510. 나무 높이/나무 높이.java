import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, maxHeight, diff;
	static int[] trees;
	static int even, odd;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			maxHeight = 0;
			even = 0; odd = 0;
			st = new StringTokenizer(br.readLine());
			
			trees = new int[N];
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, trees[i]); // 최대 높이 구해놓기
			}
			
			for (int i = 0; i < N; i++) {
				diff = maxHeight - trees[i];
				even += diff / 2;
				odd += diff % 2;
			}
			
			while (even - odd > 1) {
				even--;
				odd += 2;
			}
			
			if (even < odd) {
				answer = 1 + (odd - 1) * 2;
			} else {
				answer = 2 * even;
			}

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
}
