import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] parent;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N * M];

        // -- 모양 간선들의 가중치
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M - 1; j++) {
                int c = Integer.parseInt(st.nextToken());
                pq.offer(new Edge(i * M + j, i * M + j + 1, c));
            }
        }

        // | 모양 간선들의 가중치
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int c = Integer.parseInt(st.nextToken());
                pq.offer(new Edge(i * M + j, i * M + j + M, c));
            }
        }

        mst();

        System.out.print(answer);
    }

    private static void mst() {

        // parent 초기화
        for (int i = 0; i < N * M; i++) {
            parent[i] = i;
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            
            if (findParent(edge.a) == findParent(edge.b)) continue;

            union(edge.a, edge.b);
            answer += edge.c;
        }
    }

    private static void union(int a, int b) {
        int aParent = findParent(a);
        int bParent = findParent(b);

        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }

    private static int findParent(int x) {
        if (parent[x] == x) return x;

        return parent[x] = findParent(parent[x]);
    }

    static class Edge implements Comparable<Edge> {
        int a, b, c;

        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.c, o.c);
        }
    }
}