import java.util.*;
import java.io.*;

public class Boj10655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] checkpoint = new int[n][2];
        int[] dist1 = new int[n]; // dist[i] : 인덱스 0번 체크포인트 ~ 인덱스i번 체크포인트 거리합
        int[] dist2 = new int[n]; // dist[i] : 인덱스 n-1번 체크포인트 ~ 인덱스i번 체크포인트 거리합

        StringTokenizer st = new StringTokenizer(br.readLine());
        checkpoint[0][0] = Integer.parseInt(st.nextToken());
        checkpoint[0][1] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            checkpoint[i][0] = Integer.parseInt(st.nextToken());
            checkpoint[i][1] = Integer.parseInt(st.nextToken());

            dist1[i] = dist1[i - 1] + Math.abs(checkpoint[i - 1][0] - checkpoint[i][0]) + Math.abs(checkpoint[i - 1][1] - checkpoint[i][1]);
        }
        dist2[0] = dist1[n - 1];
        for (int i = 1; i < n; i++) {
            dist2[i] = dist2[i - 1] - (dist1[i] - dist1[i - 1]);
        }

        int answer = 987654321;
        for (int i = 1; i < n - 1; i++) {
            answer = Math.min(answer, dist1[i - 1] + Math.abs(checkpoint[i - 1][0] - checkpoint[i + 1][0]) + Math.abs(checkpoint[i - 1][1] - checkpoint[i + 1][1]) + dist2[i + 1]);
        }

        System.out.print(answer);
    }
}
