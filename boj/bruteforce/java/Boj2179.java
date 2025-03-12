import java.io.*;

public class Boj2179 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = br.readLine();
        }

        String s = "";
        String t = "";
        int m = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = Math.min(str[i].length(), str[j].length());
                int prefixLength = 0;
                for (int k = 0; k < len; k++) {
                    if (str[i].charAt(k) == str[j].charAt(k)) prefixLength++; else break;
                }

                if (m < prefixLength) {
                    s = str[i];
                    t = str[j];
                    m = prefixLength;
                }
            }
        }

        System.out.print(s + "\n" + t);
    }
}
