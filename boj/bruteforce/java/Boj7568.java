import java.io.*;
import java.util.*;

public class Boj7568 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] person = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            person[i][0] = Integer.parseInt(st.nextToken());
            person[i][1] = Integer.parseInt(st.nextToken());
        }

        int rank;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            rank = 1;
            int x = person[i][0];
            int y = person[i][1];

            for (int j = 0; j < n; j++) {
                if (x < person[j][0] && y < person[j][1]) rank++;
            }

            result[i] = rank;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
