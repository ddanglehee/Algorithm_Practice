import java.util.*;
import java.io.*;

public class Boj1837 {

    static String P;
    static int K;
    static boolean[] isNotPrime;
    static String answer = "GOOD";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        isNotPrime = new boolean[K + 1];

        // 에라토스테네스의 체
        for (int i = 2; i <= K; i++) {
            if (!isNotPrime[i]) {
                for (int j = 2; i * j <= K; j++) {
                    isNotPrime[i * j] = true;
                }
            }
        }

        // 나눠보기
        for (int i = 2; i < K; i++) {
            if (!isNotPrime[i]) {
                int result = div(P, i);
                if (result == 0) {
                    answer = "BAD " + i;
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    static int div(String a, int b) {
        int num = 0;
        for (int i = 0; i < a.length(); i++) {
            int curNum = num * 10 + (a.charAt(i) - '0');
            num = curNum % b;
        }
        return num;
    }
}
