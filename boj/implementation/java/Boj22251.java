import java.util.*;
import java.io.*;

public class Boj22251 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        String x = st.nextToken();

        int[][] map = {
                {0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
                {4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
                {3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
                {3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
                {4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
                {3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
                {2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
                {3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
                {1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
                {2, 4, 3, 1, 2, 1, 2, 3, 1, 0},
        };

        int answer = 0;
        int count;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            if (x.equals(s)) continue;

            count = 0;
            if (x.length() == s.length()) {
                for (int j = 0; j < x.length(); j++) {
                    count += map[x.charAt(j) - '0'][s.charAt(j) - '0'];
                }
            } else {
                String a,b;
                if (x.length() > s.length()) {
                    a = x;
                    b = s;
                } else {
                    a = s;
                    b = x;
                }

                for (int j = 0; j < a.length() - b.length(); j++) {
                    count += map[a.charAt(j) - '0'][0];
                }
                for (int j = 0; j < b.length(); j++) {
                    count += map[a.charAt(j + a.length() - b.length()) - '0'][b.charAt(j) - '0'];
                }
            }

            if (count <= p) {
                answer++;
            }
        }

        System.out.print(answer);
    }
}
