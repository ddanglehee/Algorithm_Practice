import java.io.*;
import java.util.*;

public class Boj1041 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dice = new int[6];
        int min1 = 50;
        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            int num = Integer.parseInt(st.nextToken());
            dice[i] = num;
            min1 = Math.min(min1, num);
            max = Math.max(max, num);
        }

        if (n == 1) {
            System.out.print(Arrays.stream(dice).sum() - max);
            return;
        }

        int min2 = 100;
        int min3 = 150;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (i + j == 5) continue;
                int sum = dice[i] + dice[j];
                min2 = Math.min(min2, sum);

                for (int k = j + 1; k < 6; k++) {
                    if (i + k == 5 || j + k == 5) continue;
                    min3 = Math.min(min3, sum + dice[k]);
                }
            }
        }

        long answer = 0;
        answer += min3 * 4L;
        answer += min2 * ((n - 1) * 4L + (n - 2) * 4L);
        answer += min1 * ((n - 1) * 4L * (n - 2) + (long) (n - 2) * (n - 2));

        System.out.print(answer);
    }
}
