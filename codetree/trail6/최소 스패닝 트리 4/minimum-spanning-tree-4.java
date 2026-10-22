import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static char[] type;
    static int answer;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        type = new char[N+1];
        parent = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            type[i] = st.nextToken().charAt(0);
            parent[i] = i;
        } 

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (type[a] == type[b]) continue;
            pq.offer(new Edge(a, b, c));
        }

        mst();

        System.out.print(answer);
    }

    static boolean union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent == bParent) return false;
        parent[aParent] = bParent;
        return true;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void mst() {
        int count = 0;
        int cost = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if(union(edge.a, edge.b)) {
                cost += edge.c;
                count++;

                if (count == N - 1) break;
            }
        }

        if (count == N - 1) {
            answer = cost;
        } else {
            answer = -1;
        }
    }

    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int c;

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