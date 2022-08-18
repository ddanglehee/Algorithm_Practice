import java.util.*;

public class ShortcutOfGameMap {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static boolean[][] visited = new boolean[100][100];

    public int solution(int[][] maps) {
        int answer = -1;
        int n = maps.length - 1;
        int m = maps[0].length - 1;

        LinkedList<Location> queue = new LinkedList<>();
        queue.offer(new Location(0, 0, 1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Location current = queue.poll();

            if (current.y == n && current.x == m) {
                answer = current.moveCount;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int tmpY = current.y + dy[i];
                int tmpX = current.x + dx[i];

                if (0 <= tmpY && tmpY <= n && 0 <= tmpX && tmpX <= m) {
                    if (!visited[tmpY][tmpX] && maps[tmpY][tmpX] == 1) {
                        queue.offer(new Location(tmpY, tmpX, current.moveCount + 1));
                        visited[tmpY][tmpX] = true;
                    }
                }
            }
        }

        return answer;
    }

    static class Location {
        int x;
        int y;
        int moveCount;

        public Location(int y, int x, int moveCount) {
            this.x = x;
            this.y = y;
            this.moveCount = moveCount;
        }
    }
}
