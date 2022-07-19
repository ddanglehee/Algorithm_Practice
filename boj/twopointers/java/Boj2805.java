import java.util.*;
import java.io.*;

public class Boj2805 {

    static int N, M;
    static long[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);

        long low = 0, high = trees[trees.length - 1], mid, sum, result = 0;
        while (true) {
            sum = 0;
            mid = (low + high) / 2;

            // sum 구하기
            for (long tree: trees) {
                if (tree <= mid) continue;
                sum += tree - mid;
            }

            if (sum == M) {
                // sum == M -> 종료
                result = mid;
                break;
            } else if (sum < M) {
                // sum < M -> high = mid - 1
                high = mid - 1;
            } else {
                // sum > M -> low = mid + 1, 후보
                result = mid;
                low = mid + 1;
            }

           if (high < low) break;
        }

        System.out.println(result);
    }
}
