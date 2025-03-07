import java.io.*;

public class Boj2138 {

    static int n;
    static String from;
    static String to;
    static char[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        from = br.readLine();
        to = br.readLine();
        tmp = new char[n];

        initTmp();
        int answer0 = solve(0);

        initTmp();
        toggle(0); toggle(1);
        int answer1 = solve(1);

        if (answer0 == -1) {
            System.out.print(answer1);
        } else if (answer1 == -1) {
            System.out.print(answer0);
        } else {
            System.out.print(Math.min(answer0, answer1));
        }
    }

    private static int solve(int result) {

        for (int i = 1; i < n; i++) {
            if (tmp[i - 1] != to.charAt(i - 1)) {
                result++;
                toggle(i - 1);
                toggle(i);
                if (i != n - 1) toggle(i + 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (tmp[i] != to.charAt(i)) {
                result = -1;
                break;
            }
        }

        return result;
    }

    private static void initTmp() {
        for (int i = 0; i < n; i++) {
            tmp[i] = from.charAt(i);
        }
    }

    private static void toggle(int i) {
        if (tmp[i] == '0') {
            tmp[i] = '1';
        } else {
            tmp[i] = '0';
        }
    }
}
