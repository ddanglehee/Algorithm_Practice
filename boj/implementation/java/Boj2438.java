import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Boj2438 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("*".repeat(i + 1));
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
