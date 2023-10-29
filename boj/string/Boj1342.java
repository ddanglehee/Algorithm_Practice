import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1342 {

    static String str;
    static int[] count = new int[26];
    static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        length = str.length();
        for (int i = 0; i < length; i++) {
            count[str.charAt(i) - 'a']++;
        }

        System.out.print(countLuckyString(-1, 0));
    }

    private static int countLuckyString(int before, int index) {
        int result = 0;

        if (index == length) return 1;

        for (int i = 0; i < 26; i++) {
            if (count[i] < 1 || before == i) continue;

            count[i]--;
            result += countLuckyString(i, index + 1);
            count[i]++;
        }

        return result;
    }
}
