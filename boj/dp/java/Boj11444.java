import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Boj11444 {

    private static Map<Long, Long> dp = new HashMap<>();
    private static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();

        dp.put(0L, 0L);
        dp.put(1L, 1L);
        dp.put(2L, 1L);

        System.out.print(fibo(n));
    }

    private static long fibo(long n) {
        if (dp.get(n) != null) {
            return dp.get(n);
        }

        if (n % 2 == 0) {
            long a = fibo(n / 2);
            long b = fibo(n / 2 + 1);
            long c = fibo(n / 2 - 1);
            dp.put(n, (a * ((b + c) % MOD)) % MOD);
        } else {
            long a = fibo((n + 1) / 2);
            long b = fibo((n - 1) / 2);
            dp.put(n, ((a * a) % MOD + (b * b) % MOD) % MOD);
        }

        return dp.get(n);
    }
}
