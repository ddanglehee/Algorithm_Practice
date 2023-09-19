import java.util.*;
import java.io.*;

public class Boj18353 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[n];
        int maxCount = 1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
             for (int j = 0; j < i; j++) {
                 if (numbers[i] < numbers[j]) {
                     count[i] = Math.max(count[i], count[j] + 1);
                 }
             }
             maxCount = Math.max(maxCount, count[i]);
        }

        System.out.print(n - maxCount);
    }
}
