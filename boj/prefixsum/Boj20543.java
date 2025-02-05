import java.io.*;
import java.util.*;

public class Boj20543 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] input = new long[N][N];
        long[][] answer = new long[N][N];
        long[][] sum = new long[N+1][N+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                input[i][j] = Long.parseLong(st.nextToken()) * (-1);
            }
        }
        StringBuilder sb = new StringBuilder();

        int m = M / 2;
        for (int i = 0; i <= N - M; i++) {
            for (int j = 0; j <= N - M; j++) {
                answer[i+m][j+m] = input[i][j] - (sum[i+M-1][j+M] + sum[i+M][j+M-1] - sum[i+M-1][j+M-1]) + (sum[i+M][j] + sum[i][j+M] - sum[i][j]);
                sum[i+M][j+M] = sum[i+M-1][j+M] + sum[i+M][j+M-1] - sum[i+M-1][j+M-1] + answer[i+m][j+m];
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
