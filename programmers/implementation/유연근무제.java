import java.util.Arrays;

class Solution {

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length; // 사람 수
        // weekends[i][j] : i요일에 시작할 경우, 주말인 날은 weekends[i][0], weekends[i][1]
        int[][] weekends = {
                {0, 0}, {6, 7}, {5, 6}, {4, 5}, {3, 4}, {2, 3}, {1, 2}, {1, 7}
        };

        for (int i = 0; i < n; i++) {
            schedules[i] += 10;

            int h = (schedules[i] / 100) * 100;
            if (60 <= schedules[i] - h) {
                schedules[i] += 100;
                schedules[i] -= 60;
            }
        }

        boolean[] isSuccess = new boolean[n];
        Arrays.fill(isSuccess, true);
        for (int day = 1; day <= 7; day++) {
            if (day == weekends[startday][0] || day == weekends[startday][1]) continue;
            for (int i = 0; i < n; i++) {
                if (!isSuccess[i]) continue;

                if (schedules[i] < timelogs[i][day - 1]) {
                    isSuccess[i] = false;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (isSuccess[i]) {
                answer++;
            }
        }

        return answer;
    }
}