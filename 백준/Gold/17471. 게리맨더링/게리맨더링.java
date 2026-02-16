import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[] population;
    static List<Integer>[] adj;
    static boolean[] selected;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        adj = new ArrayList[N + 1];
        selected = new boolean[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            population[n] = Integer.parseInt(st.nextToken());
        }

        for (int n = 1; n <= N; n++) {
            st  = new StringTokenizer(br.readLine());
            int count =  Integer.parseInt(st.nextToken());

            for (int i = 1; i <= count; i++) {
                int next = Integer.parseInt(st.nextToken());
                adj[n].add(next);
            }
        }

        // 문제 풀이
        for (int count = N / 2; 0 < count; count--) {
            combination(1, 0, count);
        }

        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.print(answer);
    }

    static void combination(int index, int curCount, int count) {
        if (curCount == count) {
            separate();
            return;
        }

        for (int i = index; i <= N; i++) {
            selected[i] = true;
            combination(i + 1, curCount + 1, count);
            selected[i] = false;
        }
    }

    static void separate() {
        int a = 0;
        int b = 0;
        for (int n = 1; n <= N; n++) {
            if (selected[n]) {
                a += population[n];
            } else {
                b += population[n];
            }
        }

        int diff = Math.abs(a - b);
        if (diff < answer && isValid()) {
            answer = diff;
        }
    }

    static boolean isValid() {
        int count = 0; // dfs가 호출되는 횟수 (=> 선거구의 개수)

        Arrays.fill(visited, false);
        for (int n = 1; n <= N; n++) {
            if (!visited[n]) {
                dfs(n, n);
                count++;
            }
        }

        return count == 2;
    }

    static void dfs(int n, int start) {
        visited[n] = true;

        for (int next : adj[n]) {
            if (selected[start] == selected[next] && !visited[next]) {
                dfs(next, start);
            }
        }
    }
}