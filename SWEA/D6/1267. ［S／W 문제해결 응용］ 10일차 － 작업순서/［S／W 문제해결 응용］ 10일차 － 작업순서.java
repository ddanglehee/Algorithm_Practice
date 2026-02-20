import java.util.*;
import java.io.*;

public class Solution {
	
	static int V, E;
	static int[] in = new int[1001];
	static int[] end = new int[1001];
	static ArrayList<Integer>[] adjList = new ArrayList[1001];
	static Queue<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 1; i <= 1000; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			init();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				in[b]++;
				adjList[a].add(b);
			}

			sb.append("#").append(t).append(" ");
			topologySort();
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void topologySort() {
		for (int i = 1; i <= V;i++) {
			if (in[i] == 0) {
				queue.add(i);
				end[i] = 1;
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append(" ");
			
			for (int next : adjList[cur]) {
				in[next]--;
				if (in[next] == 0) {
					queue.offer(next);
					end[next] = end[cur] + 1;
				}
			}
		}
	}
	
	static void init() {
		for (int i = 1; i <= V; i++) {
			end[i] = 0;
			in[i] = 0;
			adjList[i].clear();
		}
	}
}
