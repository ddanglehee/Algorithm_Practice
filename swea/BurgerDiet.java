import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.io.*;


class Solution
{

    private static int N, L;
    private static int[] t = new int[20];
    private static int[] k = new int[20];
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            answer = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                t[i] = Integer.parseInt(st.nextToken());
                k[i] = Integer.parseInt(st.nextToken());
            }

            solution(0, 0, 0);

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);

    }

    private static void solution(int index, int totalT, int totalK) {
        if (L < totalK) return;

        if (index == N) {
            answer = Math.max(answer, totalT);
            return;
        }

        solution(index + 1, totalT, totalK); // 이 햄버거를 안 먹는 경우
        solution(index + 1, totalT + t[index], totalK + k[index]); // 이 햄버거를 먹는 경우
    }
}

//----------------------- 이하는 DP로 풀이

import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.io.*;


class Solution
{

    private static int N, L;
    private static int[] t = new int[21];
    private static int[] k = new int[21];
    private static int[][] dp = new int[21][10001]; // dp[i][k] : i번 햄버거까지 고려했고, 제한 칼로리가 W일 때 선호도의 최댓

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            init();
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                t[i] = Integer.parseInt(st.nextToken());
                k[i] = Integer.parseInt(st.nextToken());
            }

            solution();

            sb.append("#").append(test_case).append(" ").append(dp[N][L]).append("\n");
        }
        System.out.print(sb);

    }

    private static void solution() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= L; j++) {

                // 현재 제한 칼로리(j)보다 i번 햄버거의 칼로리가 큰 경우 -> i번 햄버거를 먹지 않음
                if (j < k[i]) {
                    dp[i][j] = dp[i-1][j];
                }
                // 현재 제한 칼로리(j)보다 i번 햄버거의 칼로리가 큰 경우 -> i번 햄버거를 먹지 않을 때와, i번 햄버거를 먹을 때 중 선호도가 높은 것 선택
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - k[i]] + t[i]);
                }
            }
        }
    }

    private static void init() {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= L; j++) {
                dp[i][j] = 0;
            }
        }
    }
}