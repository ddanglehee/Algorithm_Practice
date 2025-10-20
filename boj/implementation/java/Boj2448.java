import java.io.*;
import java.util.*;

public class Boj2448 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        char[][] stars = new char[N][2 * N - 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                stars[i][j] = ' ';
            }
        }
        drawStars(stars, 0, N - 1, N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(stars[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void drawStars(char[][] stars, int r, int c, int size) {
        if (size == 3) {
            stars[r][c] = '*';
            stars[r + 1][c - 1] = '*';
            stars[r + 1][c + 1] = '*';
            stars[r + 2][c - 2] = '*';
            stars[r + 2][c - 1] = '*';
            stars[r + 2][c] = '*';
            stars[r + 2][c + 1] = '*';
            stars[r + 2][c + 2] = '*';
            return;
        }

        int nextSize = size / 2;
        drawStars(stars, r, c, nextSize);
        drawStars(stars, r + nextSize, c - nextSize, nextSize);
        drawStars(stars, r + nextSize, c + nextSize, nextSize);
    }
}
