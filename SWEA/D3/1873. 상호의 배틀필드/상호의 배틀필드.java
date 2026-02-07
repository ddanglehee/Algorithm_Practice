import java.util.*;
import java.io.*;

public class Solution {

    static int H, W, N;
    static char[][] map;
    static int curR, curC, curD;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            for (int r = 0; r < H; r++) {
                String input = br.readLine();
                for (int c = 0; c < W; c++) {
                    map[r][c] = input.charAt(c);
                    if (!(map[r][c] == '.' || map[r][c] == '*' || map[r][c] == '#' || map[r][c] == '-')) {
                        curR = r;
                        curC = c;
                        directionCharToInt(map[r][c]);
                        map[r][c] = '.';
                    }
                }
            }
            N = Integer.parseInt(br.readLine());
            String commands = br.readLine();
            for (int i = 0; i < N; i++) {
                char command = commands.charAt(i);
                if (command == 'S') {
                    shoot();
                } else {
                    move(command);
                }
            }

            map[curR][curC] = directionIntToChar(curD);

            sb.append("#").append(test_case).append(" ");
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    sb.append(map[r][c]);
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }

    static void move(char direction) {
        directionCharToInt(direction);

        int nR = curR + dr[curD];
        int nC = curC + dc[curD];
        if (isInMap(nR, nC) && map[nR][nC] == '.') {
            curR = nR;
            curC = nC;
        }
    }

    static void shoot() {
        int nR = curR + dr[curD];
        int nC = curC + dc[curD];

        while (isInMap(nR, nC)) {
            if (map[nR][nC] == '.' || map[nR][nC] == '-') {
                nR += dr[curD];
                nC += dc[curD];
                continue;
            }

            if (map[nR][nC] == '*') {
                map[nR][nC] = '.';
            }
            break;
        }
    }

    static boolean isInMap(int r, int c) {
        return 0 <= r && r < H && 0 <= c && c < W;
    }

    static void directionCharToInt(char direction) {
        if (direction == 'U' || direction == '^') {
            curD = 0;
        } else if (direction == 'D' || direction == 'v') {
            curD = 1;
        } else if (direction == 'L' || direction == '<') {
            curD = 2;
        } else {
            curD = 3;
        }
    }

    static char directionIntToChar(int direction) {
        if (direction == 0) {
            return '^';
        } else  if (direction == 1) {
            return 'v';
        } else if (direction == 2) {
            return '<';
        } else {
            return '>';
        }
    }
}
