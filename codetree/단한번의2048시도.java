import java.util.*;
import java.io.*;

public class Main {

    static final int SIZE = 4;
    static int[][] map = new int[SIZE][SIZE];
    static int[][] tmp = new int[SIZE][SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int r = 0; r < SIZE; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < SIZE; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        String dir = br.readLine();

        switch(dir) {
            case "L":
                rotateRight();
                down();
                rotateLeft();
                break;
            case "R":
                rotateLeft();
                down();
                rotateRight();
                break;
            case "U":
                rotate180();
                down();
                rotate180();
                break;
            case "D":
                down();
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                sb.append(map[r][c] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void down() {
        int[] tmp;
        int pre;
        int cur;
        int tmpIdx;

        for (int c = 0; c < SIZE; c++) {
            pre = 0;
            tmpIdx = 0;
            tmp = new int[SIZE];
            for (int r = SIZE - 1; 0 <= r; r--) {
                cur = map[r][c];
                if (cur == 0) continue;

                if (pre == cur) {
                    tmp[tmpIdx- 1] += cur;
                    pre = 0;
                } else {
                    tmp[tmpIdx++] = cur;
                    pre = cur;
                }
            }

            for (int r = 0; r < SIZE; r++) {
                map[SIZE-r-1][c] = tmp[r];
            }
        }
    }

    static void rotateRight() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                tmp[SIZE-c-1][r] = map[r][c];
            }
        }
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                map[r][c] = tmp[r][c];
            }
        }
    }

    static void rotateLeft() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                tmp[c][4-r-1] = map[r][c];
            }
        }
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                map[r][c] = tmp[r][c];
            }
        }
    }

    static void rotate180() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                tmp[4-r-1][4-c-1] = map[r][c];
            }
        }
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                map[r][c] = tmp[r][c];
            }
        }
    }
}