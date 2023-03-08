import java.util.*;
import java.io.*;

public class Boj13458 {

    static long answer = 0L;
    static int n;
    static int[] a;
    static int b, c;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < n; i++) {
            answer++;
            a[i] -= b;
            if (0 < a[i]) {
                answer += (a[i] - 1) / c + 1;
            }
        }

        System.out.print(answer);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int ai = Integer.parseInt(st.nextToken());
            a[i] = ai;
        }

        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }
}
