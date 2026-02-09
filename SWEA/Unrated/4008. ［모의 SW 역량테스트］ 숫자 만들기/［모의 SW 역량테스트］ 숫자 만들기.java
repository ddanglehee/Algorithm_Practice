import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int[] operands = new int[12];
	static int[] operators;
	static int max, min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			init();
			
			st = new StringTokenizer(br.readLine());
			int j = 0;
			operators = new int[N - 1];
			for (int i = 0; i < 4; i++) {
				int input = Integer.parseInt(st.nextToken());
				for (int k = 0; k < input; k++) {
					operators[j++] = i;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				operands[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(operators);
			
			do {
				calculate();
			} while(nextPermutation());
			
			sb.append("#").append(t).append(" ").append(max - min).append("\n");
		}
		System.out.print(sb);
	}
	
	static boolean nextPermutation() {
		int i = N - 2;
		while (0 < i && operators[i] <= operators[i-1]) i--;
		
		if (i == 0) return false;
		int j = N - 2;
		while (operators[i - 1] >= operators[j]) j--;
		swap(i - 1, j);
		
		int k = N - 2;
		while (i < k) swap(i++, k--);
		
		return true;
	}
	
	static void swap(int a, int b) {
		int tmp = operators[a];
		operators[a] = operators[b];
		operators[b] = tmp;
	}
	
	static void calculate() {
		int result = operands[0];
		
		for (int i = 0; i < N - 1; i++) {
			if (operators[i] == 0) {
				result += operands[i+1];
			} else if (operators[i] == 1) {
				result -= operands[i+1];
			} else if (operators[i] == 2) {
				result *= operands[i+1];
			} else {
				result /= operands[i+1];
			}
		}
		
		max = Math.max(max, result);
		min = Math.min(min, result);
	}
	
	static void init() {
		max = -1 * Integer.MAX_VALUE;
		min = Integer.MAX_VALUE;
	}
}
