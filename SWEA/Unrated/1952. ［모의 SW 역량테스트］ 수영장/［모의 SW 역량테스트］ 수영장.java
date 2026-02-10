import java.util.*;
import java.io.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] plans = new int[13];
    static int[] dp = new int[13]; // month월까지 처리했을 때 최소 누적 비용
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

            st = new StringTokenizer(br.readLine());
            for (int month = 1; month <= 12; month++) {
                plans[month] = Integer.parseInt(st.nextToken());
            }

            for (int month = 1; month <= 12; month++) {
                // 1일권과 1달권 중 저렴한 것으로 이번 달을 채움
                dp[month] = dp[month - 1] + Math.min(plans[month] * fee[0], fee[1]);

                // 3월 이상이라면, 2달 전에 3달권을 끊고 온 것과 비교
                if (month >= 3) {
                    dp[month] = Math.min(dp[month], dp[month - 3] + fee[2]);
                }
            }

            // 최종적으로 계산된 12월까지의 합산과 1년권 중 최솟값 비교
            answer = Math.min(answer, dp[12]);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}