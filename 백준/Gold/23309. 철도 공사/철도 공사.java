import java.util.*;
import java.io.*;

public class Main {
	
	static int[] prev = new int[1000001];
	static int[] next = new int[1000001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 초기 노선도 
		st = new StringTokenizer(br.readLine());
		
		int pre = 0;
		int head = 0;
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			if (head == 0) {
				head = n;
			} else {
				next[pre] = n;
				prev[n] = pre;
			}
			
			if (i == N - 1) {
				prev[head] = n;
				next[n] = head;
			}
			
			pre = n;
		}
		
		int i, j;
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			
			String info = st.nextToken(); 
			i = Integer.parseInt(st.nextToken());
			switch(info) {
				case "BN": {
					j = Integer.parseInt(st.nextToken());
					sb.append(bn(i, j)).append("\n");
					break;
				}
				case "BP": {
					j = Integer.parseInt(st.nextToken());
					sb.append(bp(i, j)).append("\n");
					break;
				}
				case "CN": {
					sb.append(cn(i)).append("\n");
					break;
				}
				case "CP": {
					sb.append(cp(i)).append("\n");
					break;
				}
			}
		}
		
		System.out.print(sb);
	}
	
	static int bn(int i, int j) {
		int n = next[i];
		
		next[i] = j;
		prev[n] = j;
		
		next[j] = n;
		prev[j] = i;
		
		return n;
	}
	
	static int bp(int i, int j) {
		int p = prev[i];
		
		prev[i] = j;
		next[p] = j;
		
		next[j] = i;
		prev[j] = p;
		
		return p;
	}
	
	static int cn(int i) {
		int t = next[i];
		int n = next[t];
		
		next[i] = n;
		prev[n] = i;
		
		next[t] = 0;
		prev[t] = 0;
		
		return t;
	}
	
	static int cp(int i) {
		int t = prev[i];
		int p = prev[t];
		
		next[p] = i;
		prev[i] = p;
		
		next[t] = 0;
		prev[t] = 0;
		
		return t;
	}
}
