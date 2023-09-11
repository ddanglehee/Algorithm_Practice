import java.io.*;

public class Boj17103 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] isPrime = new boolean[1000001];
        for (int i = 2; i <= 1000000; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= 1000; i++) {
            if (!isPrime[i]) continue;

            for (int j = i + i; j <= 1000000; j += i) {
                isPrime[j] = false;
            }
        }

        int t = Integer.parseInt(br.readLine());
        int[] answer = new int[1000001];

        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < t; test_case++) {
            int input = Integer.parseInt(br.readLine());

            if (answer[input] != 0) {
                sb.append(answer[input]).append("\n");
                continue;
            }

            int cnt = 0;
            for (int i = 2; i <= input / 2; i++) {
                if (!isPrime[i] || !isPrime[input - i]) continue;
                cnt++;
            }
            answer[input] = cnt;

            sb.append(answer[input]).append("\n");
        }

        System.out.print(sb);
    }
}
