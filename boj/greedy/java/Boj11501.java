import java.util.*;
import java.io.*;

public class Boj11501 {

    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1[0] == o2[0]) {
            return o1[1] - o2[1];
        }
        return o2[0] - o1[0];
    });
    static int N = 0;
    static int[] price = new int[1000001];
    static ArrayList<Integer> stocks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (0 < T--) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }
            init();
            sb.append(predict()).append("\n");
        }

        System.out.print(sb);
    }

    private static long predict() {
        long answer = 0;
        int[] cur = pq.poll();

        for (int i = 0; i < N; i++) {
            while (cur[1] < i && !pq.isEmpty()) {
                cur = pq.poll();
            }

            if (pq.isEmpty()) break;

            if (i == cur[1]) {
                for (int j = 0; j < stocks.size(); j++) {
                    answer += (cur[0] - stocks.get(j));
                }
                stocks.clear();
            } else {
                if (price[i] < cur[0]) {
                    stocks.add(price[i]);
                }
            }
        }

        for (int j = 0; j < stocks.size(); j++) {
            answer += (cur[0] - stocks.get(j));
        }

        return answer;
    }

    private static void init() {
        pq.clear();
        stocks.clear();

        for (int i = 0; i < N; i++) {
            pq.offer(new int[] {price[i], i});
        }
    }
}