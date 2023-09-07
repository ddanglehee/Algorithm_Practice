public class PCCP2_1 {
    public int[] solution(String command) {
        int[] answer = {0, 0};
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int d = 0;
        for (int i = 0; i < command.length(); i++) {
            char cmd = command.charAt(i);

            if (cmd == 'R') {
                d = (d + 5) % 4;
            } else if (cmd == 'L') {
                d = (d + 3) % 4;
            } else if (cmd == 'G') {
                answer[0] += dx[d];
                answer[1] += dy[d];
            } else {
                answer[0] -= dx[d];
                answer[1] -= dy[d];
            }
        }


        return answer;
    }
}
