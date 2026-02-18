import java.util.*;
import java.io.*;

class Solution {

    static int N;
    static int[] trees = new int[100];
    static int maxLength;
    static int answer;
    static int odd;
    static int even;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            maxLength = 0; odd = 0; even = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                maxLength = Math.max(maxLength, trees[i]);
            }

            for (int i = 0; i < N; i++) {
                int diff = maxLength - trees[i];
                odd += (diff % 2);
                even += (diff / 2);
            }

            while (even - odd > 1) {
                even--;
                odd += 2;
            }

            if (odd > even) {
                answer = odd * 2 - 1;
            } else {
                answer = even * 2;
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}