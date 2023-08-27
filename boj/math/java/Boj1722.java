import java.util.*;
import java.io.*;

public class Boj1722 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] factorial = new long[n + 1];
        factorial[0] = 1; factorial[1] = 1;
        for (int i = 2; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int cmd = Integer.parseInt(st.nextToken());

        boolean[] used = new boolean[n + 1];
        int[] arr = new int[n + 1];
        if (cmd == 1) {
            long k = Long.parseLong(st.nextToken());
            k--;

            for (int i = 1; i <= n; i++) {
                int m = (int) (k / factorial[n - i]);

                for (int j = 1; j <= n; j++) {
                    if (used[j]) continue;
                    --m;
                    if (m < 0) {
                        arr[i] = j;
                        used[j] = true;
                        break;
                    }
                }

                k %= factorial[n - i];
            }

            for (int i = 1; i <= n; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long answer = 0;
            for (int i = 1; i < n; i++) {
                long count = 0;

                for (int j = arr[i] - 1; 0 < j; j--) {
                    if (used[j]) continue;
                    count++;
                }

                used[arr[i]] = true;
                answer += count * factorial[n - i];
            }

            System.out.print(answer + 1);
        }
    }
}
