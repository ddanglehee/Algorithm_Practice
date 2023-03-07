import java.util.*;
import java.io.*;


public class RobotRoute
{
    static int a, b;
    static String[] graph;
    static int[] start;
    static int startDirection;
    static char[] directions = {'^', '>', 'v', '<'}; // 북0, 동1, 남2, 서3
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws IOException
    {
        init();
        findStart();
        bfs(start[0], start[1]);
        System.out.println((start[0] + 1) + " " + (start[1] + 1));
        System.out.println(directions[startDirection]);
        System.out.print(sb);
    }

    private static void bfs(int x, int y) {
        boolean[][] visited = new boolean[a][b];
        visited[x][y] = true;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {x, y, startDirection});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int[] next;
            // 다음 길 찾기
            for (int i = 0; i < 4; i++) {
                int tmpX = current[0] + dx[i];
                int tmpY = current[1] + dy[i];
                if (0 <= tmpX && tmpX < a && 0 <= tmpY && tmpY < b && graph[tmpX].charAt(tmpY) == '#' && !visited[tmpX][tmpY]) {
                    next = new int[] {tmpX, tmpY, i};
                    // 방향이 다른 경우
                    if (next[2] != current[2]) {
                        turn(current[2], next[2]);
                    }
                    visited[tmpX][tmpY] = true; visited[tmpX + dx[i]][tmpY + dy[i]] = true;
                    sb.append("A");
                    queue.offer(new int[] {tmpX + dx[i], tmpY + dy[i], i});
                }
            }
        }
    }

    // 방향 바꾸기
    private static void turn(int from, int to) {
        if (0 < (to - from) && (to - from) <= 2) {
            sb.append("R".repeat(to - from));
        } else if (0 < (to - from) && 2 < (to - from)) {
            sb.append("L");
        } else if ((to - from) < 0 && (from - to) <= 2) {
            sb.append("L".repeat(from - to));
        } else {
            sb.append("R");
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        graph = new String[a];
        for (int i = 0; i < a; i++) {
            graph[i] = br.readLine();
        }
    }

    // 시작 지점 찾기
    private static void findStart() {
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                int direction = -1;

                if (graph[i].charAt(j) == '#') {
                    for (int d = 0; d < 4; d++) {
                        int tmpX = i + dx[d];
                        int tmpY = j + dy[d];
                        if (0 <= tmpX && tmpX < a && 0 <= tmpY && tmpY < b && graph[tmpX].charAt(tmpY) == '#') {
                            if (direction == -1) {
                                direction = d;
                            } else {
                                direction = -1;
                                break;
                            }
                        }
                    }
                }
                if (direction != -1) {
                    start = new int[] {i, j};
                    startDirection = direction;
                    return;
                }
            }
        }
    }
}