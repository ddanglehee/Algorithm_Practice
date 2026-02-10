import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static List<Plan> plans = new ArrayList<>();
	static int[] prices = new int[3]; // 이용권 가격 0: 1일, 1: 1달, 2: 3달
	static int answer;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		
		for (int t = 1; t <= T; t++) {
			init();
			
			// 입력
			st = new StringTokenizer(br.readLine());
			for (int ticket = 0; ticket < 3; ticket++) {
				prices[ticket] = Integer.parseInt(st.nextToken());
			}
			
			// 1년 이용권을 answer의 초기값으로 세팅
			answer = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int month = 1; month <= 12; month++) {
				int days = Integer.parseInt(st.nextToken());
				
				if (days == 0) continue;
				plans.add(new Plan(month, days));
			}
			
			if (plans.size() == 0) {
				answer = 0;
			} else {
				backtracking(0, 0);
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void backtracking(int planIndex, int cost) {
		
		// 가지치기
		if (answer <= cost) return;
		
		if (planIndex == plans.size()) {
			answer = Math.min(answer, cost);
			return;
		}
		
		Plan plan = plans.get(planIndex);
		
		// 1일 이용권
		backtracking(planIndex + 1, cost + prices[0] * plan.days);
		
		// 1달 이용권
		backtracking(planIndex + 1, cost + prices[1]);
		
		// 3달 이용권
		int nextIndex = planIndex + 1;
		while (nextIndex < plans.size() && plans.get(nextIndex).month < plan.month + 3) nextIndex++;
		backtracking(nextIndex, cost + prices[2]);
	}
	
	static void init() {
		plans.clear();
	}
	
	static class Plan {
		int month;
		int days;
		
		Plan(int month, int days) {
			this.month = month;
			this.days = days;
		}
	}
}
