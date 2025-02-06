import java.io.*;

public class Boj1515 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int curInt = str.charAt(0) == '0' ? 11 : str.charAt(0) - '0' + 1;

        int i = 1;
        while (i < str.length()) {
            String curString = String.valueOf(curInt);

            for (int k = 0; k < curString.length(); k++) {
                char c = curString.charAt(k);
                if (c == str.charAt(i)) {
                    i++;
                }
                if (i == str.length()) break;
            }
            curInt++;
        }

        System.out.println(curInt - 1);
    }
}
