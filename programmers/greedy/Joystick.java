import java.util.*;

class Joystick {

    public int solution(String name) {
        int n = name.length();

        int alphabetMove = 0;
        int cursorMove = n - 1;
        for (int i = 0; i < n; i++) {
            char c = name.charAt(i);

            // 알파벳 이동 횟수
            alphabetMove += Math.min(c - 'A', 'Z' - c + 1);

            // 커서 이동 횟수
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }

            // index 0 에서 커서를 어느쪽으로 먼저 움직여서 i위치의 알파벳을 처리할 것이냐
            cursorMove = Math.min(cursorMove, Math.min(2 * i + n - next, 2 * (n - next) + i));
        }

        return alphabetMove + cursorMove;
    }
}