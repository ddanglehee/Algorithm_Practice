import java.util.*;
import java.io.*;

public class Boj12015 {

    static int n;
    static int[] a;
    static int[] lis;
    static int lastIdx = 0;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i < n; i++) {
            int idx = findPutIndex(a[i]);
            lis[idx] = a[i];
        }

        System.out.println(lastIdx + 1);
    }

    private static int findPutIndex(int target) {
        int start = 0;
        int end = n - 1;
        int result = lastIdx + 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (lis[mid] < target) {
                start = mid + 1;
            } else if (lis[mid] >= target) {
                end = mid - 1;
                result = mid;
            }
        }

        lastIdx = Math.max(lastIdx, result);
        return result;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        lis = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(lis, 1000001);
        lis[0] = a[0];
    }
}
