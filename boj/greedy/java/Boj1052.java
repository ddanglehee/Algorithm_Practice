import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1052 {

    static int n, k;
    static int[] a;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if (n <= k) {
            System.out.println(answer);
            return;
        }

        a = new int[25]; a[0] = 1; // a[i] == 2^i
        for (int i = 1; i <= 24; i++) {
            a[i] = a[i - 1] * 2;
        }

        for (int i = 1; i < k; i++) {
            int base = 0;
            while (a[base] <= n) {
                base++;
            }
            n -= a[base - 1];

            if (n == 0) {
                System.out.println(answer);
                return;
            }
        }

        int base = 0;
        while (a[base] < n) {
            base++;
        }
        answer = a[base] - n;

        System.out.println(answer);
    }
}
