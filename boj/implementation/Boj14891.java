import java.util.*;
import java.io.*;

class Boj14891 {

    static int answer = 0;
    static int[][] gear = new int[5][8];
    static int[] pointer = new int[5];
    static boolean[] visited = new boolean[5];
    static int k;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = input.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int gearNumber = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 5; j++) {
                visited[j] = false;
            }
            spin(gearNumber, direction * (-1));
        }

        for (int i = 1; i <= 4; i++) {
            if (gear[i][pointer[i]] == 1) {
                answer += Math.pow(2, i - 1);
            }
        }

        System.out.println(answer);
    }

    private static void spin(int gearNumber, int direction) {
        if (!(1 <= gearNumber && gearNumber <= 4)) return;
        visited[gearNumber] = true;
        if (gearNumber <= 3
                && gear[gearNumber + 1][(pointer[gearNumber + 1] + 6) % 8] != gear[gearNumber][(pointer[gearNumber] + 2) % 8]
                && !visited[gearNumber + 1]
        ) {
            spin(gearNumber + 1, direction * (-1));
        }
        if (2 <= gearNumber
                && gear[gearNumber - 1][(pointer[gearNumber - 1] + 2) % 8] != gear[gearNumber][(pointer[gearNumber] + 6) % 8]
                && !visited[gearNumber - 1]
        ) {
            spin(gearNumber - 1, direction * (-1));
        }
        pointer[gearNumber] = (pointer[gearNumber] + direction) % 8;
        if (pointer[gearNumber] == -1) pointer[gearNumber] = 7;
    }
}
