import java.io.*;
import java.util.*;

public class Boj2660 {

    private static int n;
    private static ArrayList<Integer>[] friends;
    private static boolean[] visited;
    private static int[] score;
    private static int minScore;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i <= n; i++) {
            initVisited();
            bfs(i);
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (minScore == score[i]) {
                answer.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minScore).append(" ").append(answer.size()).append("\n");
        for (int candidate: answer) {
            sb.append(candidate).append(" ");
        }
        System.out.print(sb);
    }

    private static void bfs(int start) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.add(new int[] {start, 0});

        int startScore = 0;
        while (!queue.isEmpty()) {
            int[] info = queue.removeFirst();
            score[start] = Math.max(score[start], info[1]);
            startScore = Math.max(startScore, info[1]);

            for (int friend: friends[info[0]]) {
                if (visited[friend]) continue;
                visited[friend] = true;
                queue.add(new int[] {friend, info[1] + 1});
            }
        }

        minScore = Math.min(minScore, startScore);
    }

    private static void initVisited() {
        for (int i = 1; i <= n; i++) {
            visited[i] = false;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        friends = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            friends[i] = new ArrayList<>();
        }
        visited = new boolean[n + 1];
        score = new int[n + 1];
        minScore = n;

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) break;
            friends[a].add(b);
            friends[b].add(a);
        }
    }
}
