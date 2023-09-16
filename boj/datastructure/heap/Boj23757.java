import java.io.*;
import java.util.*;

public class Boj23757 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> giftQueue = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            giftQueue.add(Integer.parseInt(st.nextToken()));
        }

        int[] want = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < m; j++) {
            want[j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            int curGift = giftQueue.poll();
            if (curGift < want[i]) {
                System.out.print(0);
                return;
            } else {
                giftQueue.add(curGift - want[i]);
            }
        }

        System.out.print(1);
    }
}
