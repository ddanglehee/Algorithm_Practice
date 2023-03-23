import java.util.*;
import java.io.*;

class Boj17779 {

    static int answer = 40000;
    static int total = 0;
    static int n;
    static int[][] a;

    public static void main(String[] args) throws IOException {
        init();

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                for (int d1 = 1; d1 <= n; d1++) {
                    if (!(1 <= r + d1 && r + d1 <= n && 1 <= c - d1 && c - d1 <= n)) break;

                    for (int d2 = 1; d2 <= n; d2++) {
                        if (!(1 <= r + d2 && r + d2 <= n
                                && 1 <= c + d2 && c + d2 <= n
                                && 1 <= r + d1 + d2 && r + d1 + d2 <= n
                                && 1 <= c - d1 + d2 && c - d1 + d2 <= n)) break;
                        calculate(r, c, d1, d2);
                    }
                }
            }
        }

        System.out.print(answer);
    }

    private static void calculate(int x, int y, int d1, int d2) {
        int[] population = new int[5];

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (1 <= r && r < x + d1 && 1 <= c && c <= y && r + c < x + y) {
                    population[0] += a[r][c];
                } else if (1 <= r && r <= x + d2 && y < c && y - x + n < c - r + n) {
                    population[1] += a[r][c];
                } else if (x + d1 <= r && 1 <= c && c < y - d1 + d2 && x - y + 2 * d1 + n < r - c + n) {
                    population[2] += a[r][c];
                } else if (x + d2 < r && y - d1 + d2 <= c && 2 * d2 + x + y < r + c) {
                    population[3] += a[r][c];
                }
            }
        }

        population[4] = total - (population[0] + population[1] + population[2] + population[3]);
        int max = 0;
        int min = 40000;
        for (int i = 0; i < 5; i++) {
            max = Math.max(max, population[i]);
            min = Math.min(min, population[i]);
        }

        answer = Math.min(answer, max - min);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n + 1][n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j<= n; j++) {
                int population = Integer.parseInt(st.nextToken());
                a[i][j] = population;
                total += a[i][j];
            }
        }
    }
}