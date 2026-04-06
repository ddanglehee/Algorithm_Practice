import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static ArrayList<Person> personList = new ArrayList<>();
	static ArrayList<Integer> step1 = new ArrayList<>();
	static ArrayList<Integer> step2 = new ArrayList<>();
	static ArrayList<Step> steps = new ArrayList<>();
	static int answer;
	
	static int[] time = new int[301];
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			steps.clear();
			personList.clear();
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			
			// 입력
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int a = Integer.parseInt(st.nextToken());
					
					if (a == 0) continue;
					
					if (a == 1) {
						personList.add(new Person(r, c));
					} else {
						steps.add(new Step(r, c, a));
					}
				}
			}
			
			// Person - Step 거리 계산
			for (Person p : personList) {
				Step step1 = steps.get(0);
				p.d1 = Math.abs(step1.r - p.r) + Math.abs(step1.c - p.c);
				
				Step step2 = steps.get(1);
				p.d2 = Math.abs(step2.r - p.r) + Math.abs(step2.c - p.c);
			}
			
			subset(0);
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int calculateTime(ArrayList<Integer> pList, int k, int type) {
		pq.clear();
		int totalTime = 0;
		
		for (int i : pList) {
			Person p = personList.get(i);
			
			if (type == 1) {
				pq.offer(p.d1);
			} else {
				pq.offer(p.d2);
			}
		}
		
		// 계단에 있는 사람들의 "나가는 시간"을 저장하는 큐
	    Queue<Integer> stair = new LinkedList<>();

	    while (!pq.isEmpty() || !stair.isEmpty()) {
	        // 1. 현재 시간 기준으로 계단을 다 내려온 사람 제거
	        while (!stair.isEmpty() && stair.peek() <= totalTime) {
	            stair.poll();
	        }

	        // 2. 계단에 자리가 있고, 도착한 사람이 있다면 진입
	        while (!pq.isEmpty() && stair.size() < 3 && pq.peek() < totalTime) {
	            pq.poll();
	            stair.offer(totalTime + k); // 현재 시간에 진입해서 stairLen 후 나감
	        }

	        if (pq.isEmpty() && stair.isEmpty()) break;
	        totalTime++; // 1초씩 흐름
	    }
		
		return totalTime;
	}
	
	static void subset(int index) {
		if (index == personList.size()) {
			answer = Math.min(answer, Math.max(calculateTime(step1, steps.get(0).k, 1), calculateTime(step2, steps.get(1).k, 2)));
			return;
		}
		
		step1.add(index);
		subset(index + 1);
		step1.remove(step1.get(step1.size() - 1));
		
		step2.add(index);
		subset(index + 1);
		step2.remove(step2.get(step2.size() - 1));
	}
	
	static class Step {
		int r, c;
		int k;
		
		public Step(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}
	
	static class Person {
		int r, c, d1, d2;
		
		public Person(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
