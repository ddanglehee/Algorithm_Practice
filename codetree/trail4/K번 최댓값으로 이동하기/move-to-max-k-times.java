import java.util.*;
import java.io.*;

public class Main {

    static int N, K, r, c;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static ArrayDeque<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); 
            }
        }
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1; c = Integer.parseInt(st.nextToken()) - 1;
        
        while (K-- > 0) {
            pq.clear();
            bfs(r, c);
            if (pq.isEmpty()) break;
            Node nxt = pq.poll();
            r = nxt.r; c = nxt.c;
        }

        System.out.println((r+1) + " " + (c+1));
    }

    static private void bfs(int sr, int sc) {
        initVisited();
        visited[r][c] = true;
        q.addLast(new int[] {sr, sc});

        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int r = cur[0]; int c = cur[1];

            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (0 <= nr && nr < N && 0 <= nc && nc < N  && map[nr][nc] < map[sr][sc] && !visited[nr][nc]) {
                    q.addLast(new int[] {nr, nc});
                    visited[nr][nc] = true;
                    pq.offer(new Node(map[nr][nc], nr,nc));
                }
            }
        }
    }

    static private void initVisited() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }
    }

    static class Node implements Comparable<Node> {
        int n, r, c;

        public Node(int n, int r, int c) {
            this.n = n;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            if (this.n == o.n) {
                if (this.r == o.r) {
                    return Integer.compare(this.c, o.c);
                }
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(this.n, o.n) * -1;
        }
    }
}