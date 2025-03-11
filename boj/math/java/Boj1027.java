import java.io.*;
import java.util.*;

public class Boj1027 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count;
        int answer = 0;
        double a, limit;
        for (int i = 1; i <= n; i++) {
            count = 0;
            limit = 1000000000;
            for (int j = i - 1; 0 < j; j--) {
                a = (double) (arr[i] - arr[j]) / (i - j);
                if (a < limit) {
                    count++;
                    limit = a;
                }
            }

            limit = (-1) * 1000000000;
            for (int j = i + 1; j <= n; j++) {
                a = (double) (arr[j] - arr[i]) / (j - i);
                if (limit < a) {
                    count++;
                    limit = a;
                }
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }
}
