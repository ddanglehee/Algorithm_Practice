import java.util.*;
import java.io.*;

public class Boj1922 {

    static int N, M;
    static int[] parent;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         N = Integer.parseInt(br.readLine());
         M = Integer.parseInt(br.readLine());
         parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        PriorityQueue<Link> pq = new PriorityQueue<>(Comparator.comparing(Link::getCost));
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.offer(new Link(a, b, c));
        }

        while (!pq.isEmpty()) {
            Link current = pq.poll();
            int a = current.a;
            int b = current.b;

            // 이미 연결되어있으면 -> continue
            if (find(a) == find(b)) continue;

            // 연결 안되어있으면 연결하고 cost 합하기
            union(a, b);
            answer += current.cost;
        }

        System.out.println(answer);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        parent[parentA] = parentB;
    }

    static int find(int x) {
        return parent[x] = (parent[x] == x) ? x : find(parent[x]) ;
    }

    static class Link {
        int a;
        int b;
        int cost;

        public Link(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }
}
