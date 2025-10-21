import java.util.*;
import java.io.*;

public class Boj10830 {

    static int n;
    static long b;
    static int[][] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());

        a = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] answer;
        if (b == 0) {
            answer = new int[n][n];
            for (int i = 0; i < n; i++) {
                answer[i][i] = 1;
            }
        } else {
            answer = solution(b);
        }

        // 정답 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(answer[i][j] % 1000).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int[][] solution(Long cur) {
        if (cur == 1) {
            return a;
        }

        if (cur % 2 == 0) {
            int[][] newMatrix = solution(cur / 2);
            return multiplyMatrix(newMatrix, newMatrix);
        } else {
            int[][] newMatrix = solution((cur - 1) / 2);
            return multiplyMatrix(multiplyMatrix(newMatrix, newMatrix), a);
        }
    }

    private static int[][] multiplyMatrix(int[][] x, int[][] y) {
        int[][] result = new int[n][n];

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += (x[i][k] * y[k][j]);
                    result[i][j] %= 1000;
                }
            }
        }

        return result;
    }
}