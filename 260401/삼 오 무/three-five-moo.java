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
            long n = m - (m / 3 + m / 5 - m / 15);

            if (n == N) {
                answer = m;
                break;
            } else if (n < N) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }

        System.out.print(answer);
    }
}