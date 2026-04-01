import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long K = sc.nextLong();

        long s = 1;
        long e = N * N;
        long answer = 0;
        while (s <= e) {
            long m = (s + e) / 2;
            long count = 0;

            for (int i = 1; i <= N; i++) {
                count += Math.min(N, m / i);
            }

            if (K <= count) {
                answer = m;
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        System.out.print(answer);
    }
}