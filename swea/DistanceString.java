import java.util.StringTokenizer;
import java.io.*;


class Solution
{

    private static int[] arr = new int[10];
    private static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        String input;
        for (int test_case = 1; test_case <= T; test_case++) {
            input = br.readLine();
            init();
            solution(input);
        }

        System.out.print(sb);
    }

    private static void solution(String input) {
        boolean flag = true;

        for (int i = 0; i < input.length(); i++) {
            int a = input.charAt(i) - '0';

            if (arr[a] == -1) {
                arr[a] = i;
            } else {
                if (arr[a] == -2 || i - arr[a] - 1 != a) {
                    flag = false;
                    break;
                }
                arr[a] = -2;
            }
        }

        for (int i = 0; i < 10; i++) {
            if (0 <= arr[i]) {
                flag = false;
                break;
            }
        }

        if (flag) {
            sb.append("yes\n");
        } else {
            sb.append("no\n");
        }
    }

    private static void init() {
        for (int i = 0; i < 10; i++) {
            arr[i] = -1;
        }
    }
}