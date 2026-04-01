import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int s = 1;
        int e = N * N;
        int answer = 0;
        while (s <= e) {
            int m = (s + e) / 2;
            int count = 0;

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