import java.util.*;
import java.io.*;

public class Boj1374 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq1 = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> pq2 = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq1.offer(new int[] {start, end});
        }

        while (!pq1.isEmpty()) {
            int[] curClass = pq1.poll();

            if (!pq2.isEmpty() && pq2.peek()[1] <= curClass[0]) {
                pq2.poll();
            }
            pq2.offer(curClass);
        }

        System.out.print(pq2.size());
    }
}
