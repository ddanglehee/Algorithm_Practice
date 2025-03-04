import java.io.*;

public class Boj12919 {

    static String s;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        String t = br.readLine();

        if (t.length() < s.length() ||
                (t.length() == s.length() && !t.equals(s))) {
            System.out.println(answer);
        }

        solve(t);
        System.out.println(answer);
    }

    private static void solve(String cur) {
        if (answer == 1 || cur.length() < s.length()) {
            return;
        }

        if (cur.length() == s.length() && cur.equals(s)) {
            answer = 1;
            return;
        }

        if (cur.charAt(cur.length() - 1) == 'A') {
            solve(cur.substring(0, cur.length() - 1));
        }

        if (cur.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(cur.substring(1));
            solve(sb.reverse().toString());
        }
    }
}
