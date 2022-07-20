import java.util.*;
import java.io.*;

public class Boj1202 {

    static int N, K;
    static int[] bags;
    static Jewel[] jewels;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewels = new Jewel[N];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }

        for (int i = 0; i < K; i++) {
            int c = Integer.parseInt(br.readLine());
            bags[i] = c;
        }

        // 가방, 보석 무게 기준 오름차순으로 정렬
        Arrays.sort(bags); Arrays.sort(jewels);

        PriorityQueue<Jewel> pq = new PriorityQueue<>((o1, o2) -> o2.v - o1.v);

        int b = 0, j = 0;
        while (b < K) {
            int bagCapacity = bags[b];
            // 가방에 넣을 수 있는 보석들 pq에 넣기
            while (j < N && jewels[j].m <= bagCapacity) {
                pq.offer(jewels[j++]);
            }
            // pq에 있는 보석 중 가장 비싼 보석 가방에 넣기
            if (!pq.isEmpty()) {
                result += pq.poll().v;
            }
            b++;
        }

        System.out.println(result);
    }

    static class Jewel implements Comparable<Jewel> {
        int m;
        int v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            return m - o.m;
        }
    }
}
