import java.util.*;
import java.io.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] plans = new int[13];
    static int[] memo = new int[13];
    static int[] fee = new int[3]; // 이용권 가격 0: 1일, 1: 1달, 2: 3달
    static int answer;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            for (int ticket = 0; ticket < 3; ticket++) {
                fee[ticket] = Integer.parseInt(st.nextToken());
            }

            // 1년 이용권을 answer의 초기값으로 세팅
            answer = Integer.parseInt(st.nextToken());
            Arrays.fill(memo, answer);

            st = new StringTokenizer(br.readLine());
            for (int month = 1; month <= 12; month++) {
                plans[month] = Integer.parseInt(st.nextToken());
            }

            backtracking(1, 0);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void backtracking(int month, int cost) {

        if (answer <= cost) return;

        if (12 < month) {
            answer = cost;
            return;
        }

        if (memo[month] <= cost) return;
        memo[month] = cost; // month월을 처리하기 직전까지의 최소 누적 비용

        // 1일 이용권
        backtracking(month + 1, cost + fee[0] * plans[month]);

        // 1달 이용권
        backtracking(month + 1, cost + fee[1]);

        // 3달 이용권
        backtracking(month + 3, cost + fee[2]);
    }
}