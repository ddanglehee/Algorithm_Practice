import java.util.StringTokenizer;
import java.io.*;


class Solution
{
    private static int[] row = new int[50];
    private static int[] column = new int[50];
    private static int H, W, r, c, answer;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        String input;
        for (int test_case = 1; test_case <= T; test_case++) {
            init();

            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            for (int i = 0; i < H; i++) {
                input = br.readLine();
                for (int j = 0; j < W; j++) {
                    if (input.charAt(j) == '#') {
                        row[i]++;
                        column[j]++;
                    }
                }
            }

            for (int i = 0; i < H; i++) {
                if (row[i] == W) {
                    r++;
                }
            }

            for (int j = 0; j < W; j++) {
                if (column[j] == H) {
                    c++;
                }
            }

            if (r == H) {
                answer = Math.min(H, W);
            } else {
                answer = r + c;
            }

            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }

    private static void init() {
        for (int i = 0; i < 50; i++) {
            column[i] = 0;
            row[i] = 0;
        }
        r = 0;
        c = 0;
        answer = 0;
    }
}