import java.io.*;

public class Boj2292 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 1;
        int i = 1;

        while (i < n) {
            answer++;
            i += (answer - 1) * 6;
        }

        System.out.println(answer);
    }
}