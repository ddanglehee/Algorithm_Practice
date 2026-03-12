import java.util.*;
import java.io.*;
 
public class Solution {
 
    static int N, R, C;
    static int[][] map = new int[15][12];
    static int[][] originalMap = new int[15][12];
    static int[] selected = new int[4];
    static Queue<int[]> removeQueue = new ArrayDeque<>();
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static int answer;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
 
            answer = Integer.MAX_VALUE;
 
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    originalMap[r][c] = Integer.parseInt(st.nextToken());
                }
            }
 
            permutation(0);
 
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
 
        System.out.print(sb);
    }
 
    static void shoot(int c) {
        int r = getR(c);
 
        if (r == R) return; // 해당 column에 벽돌이 없을 때
 
        removeQueue.add(new int[] {r, c, map[r][c]});
        map[r][c] = 0;
 
        while (!removeQueue.isEmpty()) {
            int[] location =  removeQueue.poll();
            r = location[0]; c = location[1]; int n = location[2];
 
            for (int d = 0; d < 4; d++) {
                for (int i = 1; i < n; i++) {
                    int nR = r + dr[d] * i;
                    int nC = c + dc[d] * i;
 
                    if (isInMap(nR, nC) && map[nR][nC] != 0) {
                        removeQueue.add(new int[] {nR, nC, map[nR][nC]});
                        map[nR][nC] = 0;
                    }
                }
            }
        }
    }
 
    static void downBrick() {
        for (int c = 0; c < C; c++) {
            int index = R - 1;
            for (int r = R - 1; 0 <= r; r--) {
                if (map[r][c] != 0) {
                    map[index--][c] = map[r][c];
                }
            }
            for (int r = index; 0 <= r; r--) {
                map[r][c] = 0;
            }
        }
    }
 
    static int getR(int c) {
        for (int r = 0; r < R; r++) {
            if (map[r][c] != 0) {
                return r;
            }
        }
        return R; // 아무것도 없을 때
    }
 
    static int getBrickCount() {
        int count = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] != 0) count++;
            }
        }
        return count;
    }
 
    static boolean isInMap(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
 
    static void play() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = originalMap[r][c];
            }
        }
 
        for (int i = 0; i < N; i++) {
            shoot(selected[i]);
            downBrick();
        }
 
        answer = Math.min(answer, getBrickCount());
    }
 
    static void permutation(int index) {
        if (index == N) {
            play();
            return;
        }
 
        for (int c = 0; c < C; c++) {
            selected[index] = c;
            permutation(index + 1);
        }
    }
}