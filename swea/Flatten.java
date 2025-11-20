import java.util.*;
import java.io.*;


class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int dumpCount;
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer st;
        for (int t = 1; t <= 10; t++) {
            dumpCount = Integer.parseInt(br.readLine());
            pq1.clear(); pq2.clear();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 100; i++) {
                int x = Integer.parseInt(st.nextToken());

                pq1.add(x);
                pq2.add(x);
            }

            while(0 < dumpCount--) {
                int a = pq1.poll();
                int b = pq2.poll();

                if (b - a <= 1) {
                    pq1.add(a);
                    pq2.add(b);
                    break;
                } else {
                    pq1.add(a + 1);
                    pq2.add(b - 1);
                }
            }

            sb.append('#').append(t).append(" ").append(pq2.peek() - pq1.peek()).append("\n");
        }

        System.out.print(sb);
    }
}