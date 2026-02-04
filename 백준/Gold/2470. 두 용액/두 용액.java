import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int s = 0; int e = N - 1;
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];
        StringBuilder sb = new StringBuilder();

        while (s < e) {
            int mix = arr[s] + arr[e];

            if (Math.abs(mix) < min) {
                min = Math.abs(mix);
                answer[0] = arr[s];
                answer[1] = arr[e];
            }

            if (mix < 0) {
                s++;
            } else {
                e--;
            }
        }

        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.print(sb);
    }
}
