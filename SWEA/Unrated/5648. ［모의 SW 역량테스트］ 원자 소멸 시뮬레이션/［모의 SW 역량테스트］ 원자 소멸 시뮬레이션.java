import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, xCount;
	static int answer;
	static int[][] map = new int[4001][4001];
	static Atom[] atoms = new Atom[1000];
	static final int OFFSET = 2000; 
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			xCount = 0;
			
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) * 2 + OFFSET;
				int y = Integer.parseInt(st.nextToken()) * 2 + OFFSET;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				atoms[i] = new Atom(x, y, d, k);
			}
			
			int t = 4000;
			while (t-- > 0) {
				if (xCount == N) break;
				
				// 원자 이동하기
				for (int i = 0; i < N; i++) {
					Atom atom = atoms[i];
					
					if (atom.k == 0) continue; // 소멸된 원자면 스킵
					
					atom.x += dx[atom.d];
					atom.y += dy[atom.d];
					
					// map 밖으로 나가면 소멸
					if (!isInMap(atom.x, atom.y)) {
						atom.k = 0;
						xCount++;
						continue;
					}
					
					// 새로운 위치에 에너지 합산
					map[atom.x][atom.y] += atom.k;
				}
				
				// 충돌난 거 없는지 확인
				for (int i = 0; i < N; i++) {
					Atom atom = atoms[i];
					
					if (atom.k == 0) continue; // 소멸된 원자면 스킵
					
					if(atom.k < map[atom.x][atom.y]) {
						answer += atom.k;
						atom.k = 0;
						xCount++;
					}
				}
				
				// 쌓인 에너지 정리
				clearMap();
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	static void clearMap() {
		for (int i = 0; i < N; i++) {
			Atom atom = atoms[i];
			
			if (!isInMap(atom.x, atom.y)) continue;
			map[atom.x][atom.y] = 0;
		}
	}
	
	static boolean isInMap(int r, int c) {
		return 0 <= r && r <= 4000 && 0 <= c && c <= 4000;
	}
	
	
	static class Atom {
		int x;
		int y;
		int d;
		int k; // 소멸하면 에너지 0
		
		public Atom(int x, int y, int d, int k) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
	}
	
}
