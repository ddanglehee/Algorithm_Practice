import java.io.*;
import java.util.*;

class Boj1074 {

    private static int N, R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.print(solution(N, R, C));
    }

    private static long solution(int n, int r, int c) {
        if (n == 0) return 0;

        long answer = 0;
        int a = (int) Math.pow(2, n - 1);

        // 1 사분면
        if (0 <= r && r < a && 0 <= c && c < a) {
            answer += solution(n - 1, r, c);
        }
        // 2 사분면
        else if (0 <= r && r < a && a <= c) {
            answer += ((long) a * a) + solution(n - 1, r, c - a);
        }
        // 3 사분면
        else if (a <= r && 0 <= c && c < a) {
            answer += 2 * ((long) a * a) + solution(n - 1, r - a, c);
        }
        // 4 사분면
        else {
            answer += 3 * ((long) a * a) + solution(n - 1, r - a, c - a);
        }

        return answer;
    }
}