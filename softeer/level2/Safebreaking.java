import java.util.*;
import java.io.*;


public class Safebreaking
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String args[]) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int answer = 0;
        PriorityQueue<Gold> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            pq.add(new Gold(m, p));
        }

        while(w != 0 || !pq.isEmpty()) {
            Gold gold = pq.poll();

            if (w <= gold.m) {
                answer += (w * gold.p);
                w = 0;
            } else {
                w -= gold.m;
                answer += (gold.m * gold.p);
            }
        }

        System.out.println(answer);

    }
}

class Gold implements Comparable<Gold> {
    int m;
    int p;

    Gold(int m, int p) {
        this.m = m;
        this.p = p;
    }

    @Override
    public int compareTo(Gold o) {
        return o.p - this.p;
    }

}
