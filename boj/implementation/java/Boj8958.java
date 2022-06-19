import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Boj8958 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String input = br.readLine();

            int result = 0;
            int currentScore = 0;
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == 'O') {
                    currentScore++;
                    result += currentScore;
                } else {
                    currentScore = 0;
                }
            }
            sb.append(result).append('\n');
        }

        System.out.println(sb);
        br.close();
    }
}
