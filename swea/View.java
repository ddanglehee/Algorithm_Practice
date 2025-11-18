import java.util.StringTokenizer;
import java.io.*;


class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N;
        int[] arr = new int[1000];
        int answer;

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            answer = 0;
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 2; i < N - 2; i++) {
                if (arr[i - 2] < arr[i] && arr[i - 1] < arr[i] && arr[i + 1] < arr[i] && arr[i + 2] < arr[i]) {
                    answer += arr[i] - Math.max(Math.max(arr[i- 2], arr[i - 1]), Math.max(arr[i + 2], arr[i + 1]));
                }
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}