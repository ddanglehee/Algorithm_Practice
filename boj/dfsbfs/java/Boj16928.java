import java.util.*;
import java.io.*;

public class Boj16928 {

    static int n, m;
    static int[] map = new int[101]; // 0: 아무것도 없는데 아직 방문 안한거, -1: 방문한거, 그 외: 사다리나 뱀으로 이동하게되는 곳
    static int answer;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        System.out.print(answer);
    }

    private static void bfs() {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {1, 0});
        map[1] = -1;

        while (!queue.isEmpty()) {
            int[] info = queue.removeFirst();

            if (info[0] == 100) {
                answer = info[1];
                break;
            }

            for (int d = 1; d <= 6; d++) {
                int nextPoint = info[0] + d;

                if (nextPoint <= 100 && map[nextPoint] != -1) {
                    queue.add(new int[] {map[nextPoint], info[1] + 1});
                    map[nextPoint] = -1;
                }
            }
        }

    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 100; i++) {
            map[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x] = y;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map[u] = v;
        }
    }
}
