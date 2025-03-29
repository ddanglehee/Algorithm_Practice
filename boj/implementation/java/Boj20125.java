import java.io.*;

public class Boj20125 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] board = new String[n];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine();
        }

        int[] heart = new int[2];
        int[] cookie = new int[5]; // 0: 왼팔, 1: 오른팔, 2: 허리, 3: 왼다리, 4: 오른다리
        boolean found = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i].charAt(j);
                if (c == '*') {
                    heart[0] = i + 1;
                    heart[1] = j;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        // 왼팔
        for (int j = heart[1] - 1; 0 <= j; j--) {
            if (board[heart[0]].charAt(j) == '_') break;
            cookie[0]++;
        }
        // 오른팔
        for (int j = heart[1] + 1; j < n; j++) {
            if (board[heart[0]].charAt(j) == '_') break;
            cookie[1]++;
        }
        // 허리
        int waistX = 0;
        for (int i = heart[0] + 1; i < n; i++) {
            if (board[i].charAt(heart[1]) == '_') {
                waistX = i - 1;
                break;
            }
            cookie[2]++;
        }
        // 왼다리
        for (int i = waistX + 1; i < n; i++) {
            if (board[i].charAt(heart[1] - 1) == '_') break;
            cookie[3]++;
        }
        // 오른다리
        for (int i = waistX + 1; i < n; i++) {
            if (board[i].charAt(heart[1] + 1) == '_') break;
            cookie[4]++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(heart[0] + 1).append(" ").append(heart[1] + 1).append("\n");
        for (int i = 0; i < 5; i++) {
            sb.append(cookie[i]).append(" ");
        }
        System.out.print(sb);
    }
}
