import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, L;
	static List<Hamburger> burgerList = new ArrayList<>();
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			burgerList.clear();
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int t = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				burgerList.add(new Hamburger(t, k));
			}
			
			Collections.sort(burgerList, (o1, o2) -> o1.kcal - o2.kcal);
			solution(0, 0, 0);
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	static void solution(int index, int curKcal, int curScore) {
		answer = Math.max(answer, curScore);
		
		if (index == N) return;
		
		Hamburger h = burgerList.get(index);
		if (L < curKcal + h.kcal) return;
		
		solution(index + 1, curKcal + h.kcal, curScore + h.score);
		solution(index + 1, curKcal, curScore);
	}
	
	static class Hamburger {
		int score;
		int kcal;
		
		public Hamburger(int score, int kcal) {
			this.score = score;
			this.kcal =kcal;
		}
	}
}
