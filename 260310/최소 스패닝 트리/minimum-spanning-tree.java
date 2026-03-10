import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int answer;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] parent;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(a, b, c));
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            int aParent = find(edge.a);
            int bParent = find(edge.b);

            if(aParent == bParent) continue;
            parent[aParent] = bParent;
            answer += edge.c;
            count++;
            if (count == N - 1) break;
        }

        System.out.print(answer);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
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