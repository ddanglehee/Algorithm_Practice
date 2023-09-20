import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj21921 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] visit = new int[n];

        for (int i = 0; i < n; i++) {
            visit[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int count = 1;
        for (int i = 0; i < x; i++) {
            max += visit[i];
        }

        int tmp = max;
        for (int i = x; i < n; i++) {
            tmp -= visit[i - x];
            tmp += visit[i];

            if (max != 0 && max == tmp) {
                count++;
            } else if (max < tmp) {
                max = tmp;
                count = 1;
            }
        }

        if (max == 0) {
            System.out.print("SAD");
        } else {
            System.out.println(max);
            System.out.println(count);
        }

    }
}
