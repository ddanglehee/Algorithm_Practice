import java.io.*;
import java.util.*;

class Secret2 {
    static StringTokenizer st;
    static String N, x, y;
    static StringBuilder answer = new StringBuilder();
    static int big, small;
    static int bigLastIndex;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++) {
            init();

            if (!findProperCard()) {
                answer.setLength(0);
                answer.append(-1);
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        x = st.nextToken();
        y = st.nextToken();

        int integerX = Integer.parseInt(x);
        int integerY = Integer.parseInt(y);
        big = Math.max(integerX, integerY);
        small = Math.min(integerX, integerY);

        bigLastIndex = -1;
        answer.setLength(0);
    }

    static boolean findProperCard() {
        for (int i = 0; i < N.length(); i++) {
            int n = Integer.parseInt(String.valueOf(N.charAt(i)));

            if (n < small) {
                if (bigLastIndex == -1) {
                    answer.setLength(0);
                    answer.append(repeatBig(N.length() - 1));
                } else {
                    String subAnswer = answer.substring(0, bigLastIndex);
                    answer.setLength(0);
                    answer.append(subAnswer).append(small).append(repeatBig(N.length() - bigLastIndex - 1));
                }
                break;
            } else if (small == n) {
                answer.append(small);
            } else if (n < big) {
                answer.append(small).append(repeatBig(N.length() - i - 1));
                break;
            } else if (n == big) {
                answer.append(big);
                bigLastIndex = i;
            } else {
                answer.append(repeatBig(N.length() - i));
                break;
            }
        }

        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == '0') {
                answer.deleteCharAt(i);
            } else {
                break;
            }
        }

        return answer.length() != 0;
    }

    static String repeatBig(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(big);
        }
        return result.toString();
    }
}
