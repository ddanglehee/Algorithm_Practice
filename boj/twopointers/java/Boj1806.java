import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1806 {

    static int N, S;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int low = 0, high = 0, sum = nums[0], result = 100001;
        while (true) {
            if (sum >= S) {
                result = Math.min(result, high - low + 1);
                sum -= nums[low++];
            } else {
                sum += nums[++high];
            }

            if (high == N) break;
        }

        if (result == 100001) result = 0;
        System.out.println(result);
    }
}
