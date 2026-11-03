import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] a = new int[N+1][N+1];
        int[][] sum = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + a[i][j];
            }
        }

        int answer = 0;
        for (int i = K; i <= N; i++) {
            for (int j = K; j <= N; j++) {
                answer = Math.max(answer, sum[i][j] - sum[i - K][j] - sum[i][j - K] + sum[i - K][j - K]);
            }
        }
        System.out.print(answer);
    }
}