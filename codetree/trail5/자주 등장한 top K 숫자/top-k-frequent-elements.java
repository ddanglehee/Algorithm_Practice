import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(new int[] {entry.getValue(), entry.getKey()});
        }

        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            int[] top = pq.poll();
            sb.append(top[1] + " ");
        }

        System.out.print(sb);
    }
}