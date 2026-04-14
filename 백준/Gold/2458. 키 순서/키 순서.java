import java.util.*;
import java.io.*;

class Main {
    
    static int N, M;
    static int[][] map;
    static final int INF = 1_000_000_000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) map[i][j] = 0; else map[i][j] = INF;
            }
        }
        
        while(0 < M--) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            map[s][l] = 1;
        }
        
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int s = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (map[i][j] != INF) s++;
                if (map[j][i] != INF) s++;
            }
            if (s == N - 1) answer++;
        }
        
        System.out.print(answer);
    }
}