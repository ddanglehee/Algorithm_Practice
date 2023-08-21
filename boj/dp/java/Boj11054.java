import java.util.*;
import java.io.*;

public class Boj11054 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i] && dp1[i] <= dp1[j]) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }
        for (int i = n - 2; 0 <= i; i--) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] > array[j] && dp2[i] <= dp2[j]) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp1[i] + dp2[i] + 1);
        }

        System.out.print(answer);
    }
}
