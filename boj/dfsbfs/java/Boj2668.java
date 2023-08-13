import java.util.*;
import java.io.*;

class Boj2668 {

    static int n;
    static int[] graph;
    static boolean[] visited;
    static ArrayList<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            graph[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            dfs(i, i);
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(answerList);
        sb.append(answerList.size()).append("\n");
        for (Integer answer: answerList) {
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int start, int cur) {
        visited[cur] = true;

        if (!visited[graph[cur]]) {
            dfs(start, graph[cur]);
        }
        if (graph[cur] == start) answerList.add(start);

        visited[cur] = false;
    }
}