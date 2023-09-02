import java.util.*;
import java.io.*;

public class Boj9081 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < test_case; t++) {
            sb.append(solution(br.readLine())).append("\n");
        }

        System.out.print(sb);
    }

    private static String solution(String str) {
        char[] strArr = str.toCharArray();

        for (int i = str.length() - 2; 0 <= i; i--) {
            if (strArr[i] < strArr[i + 1]) {
                for (int j = str.length() - 1; i < j; j--) {
                    if (strArr[i] < strArr[j]) {
                        char tmp = strArr[i];
                        strArr[i] = strArr[j];
                        strArr[j] = tmp;
                        break;
                    }
                }

                char[] subStrArr = Arrays.copyOfRange(strArr, i + 1, strArr.length);
                Arrays.sort(subStrArr);

                for (int j = i + 1; j < str.length(); j++) {
                    strArr[j] = subStrArr[j - i - 1];
                }
                break;
            }

            if (i == 0) return str;
        }

        StringBuilder sb = new StringBuilder();
        for (char c: strArr) {
            sb.append(c);
        }

        return sb.toString();
    }
}
