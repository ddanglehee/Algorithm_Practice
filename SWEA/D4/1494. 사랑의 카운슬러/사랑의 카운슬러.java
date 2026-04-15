import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static long answer;
	static ArrayList<Worm> worms = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			answer = Long.MAX_VALUE;
			worms.clear();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				worms.add(new Worm(x, y));
			}
			
			moveWorms(0, 0, 0, 0, 0);
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void moveWorms(int index, long curX, long curY, int pCount, int mCount) {
		
		if (pCount > N / 2 || mCount > N / 2) return;
		
		if (index == N) {
			answer = Math.min(answer, curX * curX + curY * curY);
			return;
		}
		
		Worm worm = worms.get(index);
		long x = worm.x;
		long y = worm.y;
		
		moveWorms(index + 1, curX + x, curY + y, pCount + 1, mCount);
		moveWorms(index + 1, curX - x, curY - y, pCount, mCount + 1);
	}
	
	static class Worm {
		long x;
		long y;
		
		public Worm(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
}
