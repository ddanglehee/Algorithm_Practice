import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N+1];
        parent = new int[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        travel(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void travel(int n) {
        visited[n] = true;
        for (int nxt : adjList[n]) {
            if (visited[nxt]) continue;
            parent[nxt] = n;
            travel(nxt);
        }
    }
}