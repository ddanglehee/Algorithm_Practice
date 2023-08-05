import java.util.*;
import java.io.*;

public class Boj20056 {

    static int n, m, k;
    static ArrayList<Fireball> fireballs = new ArrayList<>();
    static ArrayList<Fireball>[][] graph;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        init();

        for (int cmd = 0; cmd < k; cmd++) {
            moveFireBalls();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ArrayList<Fireball> fireballList = graph[i][j];
                    if (2 <= fireballList.size()) {
                        renewalFireballs(i, j);
                    }
                }
            }
        }

        int answer = 0;
        for (Fireball fireball: fireballs) {
            answer += fireball.m;
        }
        System.out.print(answer);
    }

    static private void renewalFireballs(int r, int c) {
        ArrayList<Fireball> fireballList = graph[r][c];

        int newM = fireballList.get(0).m;
        int newS = fireballList.get(0).s;
        int d = fireballList.get(0).d % 2;
        int dType = 0;
        int[][] newD = {
                {0, 2, 4, 6},
                {1, 3, 5, 7}
        };

        fireballs.remove(fireballList.get(0));
        for (int i = 1; i < fireballList.size(); i++) {
            Fireball fireball = fireballList.get(i);
            newM += fireball.m;
            newS += fireball.s;
            fireballs.remove(fireball);
            if (dType == 0 && (fireball.d % 2 != d)) dType = 1;
        }
        newM /= 5;
        newS /= fireballList.size();


        graph[r][c].clear();
        if (newM == 0) return;

        for (int i = 0; i < 4; i++) {
            Fireball fireball = new Fireball(r, c, newM, newD[dType][i], newS);
            fireballs.add(fireball);
            graph[r][c].add(fireball);
        }
    }

    static private void moveFireBalls() {
        for (int i = 0; i < fireballs.size(); i++) {
            Fireball fireball = fireballs.get(i);
            if (fireball == null) continue;

            int nR = (fireball.r + dr[fireball.d] * fireball.s) % n;
            if (nR < 0) nR = n + nR;
            int nC = (fireball.c + dc[fireball.d] * fireball.s) % n;
            if (nC < 0) nC = n + nC;

            fireball.r = nR;
            fireball.c = nC;
        }

        initGraph();

        for (int i = 0; i < fireballs.size(); i++) {
            Fireball fireBall = fireballs.get(i);
            graph[fireBall.r][fireBall.c].add(fireBall);
        }
    }

    static private void initGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j].clear();
            }
        }
    }

    static private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Fireball fireball = new Fireball(r - 1, c - 1, m, d, s);
            graph[r - 1][c - 1].add(fireball);
            fireballs.add(fireball);
        }
    }

    static class Fireball {
        int r;
        int c;
        int m;
        int d;
        int s;

        public Fireball(int r, int c, int m, int d, int s) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
}
