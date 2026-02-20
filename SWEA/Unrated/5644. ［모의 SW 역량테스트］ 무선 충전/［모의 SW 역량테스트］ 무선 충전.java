import java.util.*;
import java.io.*;

public class Solution {
	
	static int M, A;
	static List<BC>[][] map = new ArrayList[10][10];
	static int[] userA = new int[2];
	static int[] userB = new int[2];
	static int[] moveA = new int[101];
	static int[] moveB = new int[101];
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};
	static BC bc0 = new BC(0, 0);
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			init();
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				moveA[m] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				moveB[m] = Integer.parseInt(st.nextToken());
			}
			
			for (int a = 1; a <= A; a++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken()) - 1;
				int Y = Integer.parseInt(st.nextToken()) - 1;
				int C = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				BC newBC = new BC(a, P);
				
				for (int y = Y - C; y <= Y + C; y++) {
					for (int x = X - C; x <= X + C; x++) {
						if (!isInMap(x, y)) continue;
						if (Math.abs(x - X) + Math.abs(y - Y) <= C) {
							map[x][y].add(newBC);
						}
					}
				}
			}
			
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 10; y++) {
					Collections.sort(map[x][y]);
				}
			}
			
			for (int m = 0; m <= M; m++) {
				userA[0] += dx[moveA[m]];
				userA[1] += dy[moveA[m]];
				userB[0] += dx[moveB[m]];
				userB[1] += dy[moveB[m]];
				
				BC aBC;
				BC bBC;
				
				if (map[userA[0]][userA[1]].size() == 0) {
					aBC = bc0;
				} else  {
					aBC = map[userA[0]][userA[1]].get(0); 
				}
				
				if (map[userB[0]][userB[1]].size() == 0) {
					bBC = bc0;
				} else {
					bBC = map[userB[0]][userB[1]].get(0);
				}
				
				if (aBC.P == 0 || bBC.P == 0 || (aBC.id != bBC.id)) {
					answer += (aBC.P + bBC.P);

				} else {
					int power = aBC.P;
					if (map[userA[0]][userA[1]].size() > 1) power = Math.max(power, aBC.P + map[userA[0]][userA[1]].get(1).P);
					if (map[userB[0]][userB[1]].size() > 1) power = Math.max(power, aBC.P + map[userB[0]][userB[1]].get(1).P);
					answer += power;
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}	
		
		System.out.print(sb);
	}
	
	static void init() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j].clear();
			}
		}
		userA[0] = 0; userA[1] = 0; userB[0] = 9; userB[1] = 9;
		answer = 0;
	}
	
	static boolean isInMap(int x, int y) {
		return !(x < 0 || y < 0 || x >= 10 || y >= 10);
	}
	
	static class BC implements Comparable<BC> {
		int id;
		int P;
		
		public BC(int id, int P) {
			this.id = id;
			this.P = P;
		}
		
		@Override
		public int compareTo(BC o) {
			return Integer.compare(o.P, this.P);
		}
	}
}
