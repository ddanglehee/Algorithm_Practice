import java.util.*;
import java.io.*;

public class Boj1956 {

    static final int MAX = Integer.MAX_VALUE;
    static int v,e;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        input();

        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (map[i][k] == MAX || map[k][j] == MAX) continue;

                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int answer = MAX;
        for (int i = 1; i <= v; i++) {
            answer = Math.min(answer, map[i][i]);
        }
        if (answer == MAX) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        map = new int[v+1][v+1];
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                map[i][j] = MAX;
            }
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[from][to] = cost;
        }
    }
}
