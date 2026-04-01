import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        
        long s = 1;
        long e = 15 * 1000000000;
        long answer = 0;
        while (s <= e) {
            long m = (s + e) / 2;

            // m 이 몇 번째로 적히는 숫자인가?
            // m이 3의 배수거나 5의 배수일 수 있음
            long n = m - (m / 3 + m / 5 - m / 15);

            if (N <= n) {
                answer = m;
                e = m - 1;
            } else if (n < N) {
                s = m + 1;
            }
        }

        System.out.print(answer);
    }
}