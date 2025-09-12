import java.util.*;
import java.io.*;

public class Boj2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = n - 1;
        if (a[s] < 0 && a[e] < 0) {
            System.out.println(a[e - 1] + " " + a[e]);
            return;
        } else if (0 < a[s] && 0 < a[e]) {
            System.out.println(a[0] + " " + a[1]);
            return;
        }

        int answer = Integer.MAX_VALUE;
        int as = 0; int ae = 0;
        while (s < e) {
            int tmp = a[e] + a[s];
            if (Math.abs(tmp) < Math.abs(answer)) {
                answer = tmp;
                ae = e;
                as = s;
            }

            if (0 < tmp) {
                e--;
            } else {
                s++;
            }
        }

        System.out.println(a[as] + " " + a[ae]);
    }
}