import java.util.*;
import java.io.*;


public class PlayfairCipher
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Map<Character, int[]> tableMap = new HashMap<>();
    static char[][] table = new char[5][5];
    static ArrayList<char[]> partition = new ArrayList<>();
    static String answer = "";

    public static void main(String args[]) throws IOException
    {
        String message = br.readLine();
        String key = br.readLine();

        setTable(key);
        encrypt(message);

        System.out.print(answer);
    }

    private static void setTable(String key) {
        boolean[] isUsed = new boolean[26];
        isUsed['J' - 'A'] = true;

        int n = 0;
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            if (!isUsed[currentChar - 'A']) {
                isUsed[currentChar - 'A'] = true;
                int row = n / 5; int column = n % 5;
                table[row][column] = currentChar;
                tableMap.put(currentChar, new int[]{row, column});
                n++;
            }
        }

        if (25 <= n) return;
        for (int i = 0; i < 26; i++) {
            if (isUsed[i]) continue;
            int row = n / 5; int column = n % 5;
            table[row][column] = (char) (i + 'A');
            tableMap.put((char) (i + 'A'), new int[] {row, column});
            n++;
        }
    }

    private static void encrypt(String message) {
        partitioning(message);
        encrypting();
    }

    private static void partitioning(String message) {
        int n = 0;

        while (n < message.length()) {
            // 마지막 문자만 남은 경우
            if (n == message.length() - 1) {
                partition.add(new char[] {message.charAt(n), 'X'});
                break;
            }

            char char1 = message.charAt(n);
            char char2 = message.charAt(n + 1);
            // 인접한 두개의 문자들이 서로 같을 경우
            if (char1 == char2) {
                if (char1 == 'X') { // 그 문자가 X이면
                    partition.add(new char[] {char1, 'Q'});
                } else {
                    partition.add(new char[] {char1, 'X'});
                }
                n++;
            }
            // 다를 경우
            else {
                partition.add(new char[] {char1, char2});
                n += 2;
            }
        }
    }

    private static void encrypting() {
        for (char[] p : partition) {
            int[] char1 = tableMap.get(p[0]); int[] char2 = tableMap.get(p[1]);
            int r1 = char1[0]; int c1 = char1[1];
            int r2 = char2[0]; int c2 = char2[1];

            if ((r1 == r2 && c1 == c2) || r1 == r2) {
                answer += table[r1][(c1 + 1) % 5];
                answer += table[r2][(c2 + 1) % 5];
            } else if (c1 == c2) {
                answer += table[(r1 + 1) % 5][c1];
                answer += table[(r2 + 1) % 5][c2];
            } else {
                answer += table[r1][c2];
                answer += table[r2][c1];
            }
        }
    }
}
