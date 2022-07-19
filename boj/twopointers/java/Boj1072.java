import java.io.*;
import java.util.*;

public class Boj1072 {

    static long X, Y;
    static double Z;
    static long result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());
        Z = Math.floor(((double)Y * 100/ (double)X));

        long low = 0, high = X;
        while (true) {
            long mid = (low + high) / 2;

            // 새로운 승률 계산
            double newZ = Math.floor(((double)(Y + mid) * 100 / (double)(X + mid)) );
            // Z < 새로운 승률 -> 답 후보, high = mid - 1
            if (Z < newZ) {
                result = mid;
                high = mid - 1;
            // Z == 새로운 승률 -> low = mid + 1
            } else {
                low = mid + 1;
            }

            if (high < low) break;
        }

        System.out.println(result);
    }
}
