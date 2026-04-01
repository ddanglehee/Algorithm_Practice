import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        int s = 1;
        int e = 1000000000;
        int answer = 0;
        while (s < e) {
            int m = (s + e) / 2;

            // m 이 몇 번째로 적히는 숫자인가?
            int n = m - (m / 3 + m / 5 - m / 15);

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