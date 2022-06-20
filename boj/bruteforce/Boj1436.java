import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int number = 666;
        int count = 0;
        int result;
        while (true) {
            if (String.valueOf(number).contains("666")) {
                count++;
                if (count == n) {
                    result = number;
                    break;
                }
            }
            number++;
        }

        System.out.println(result);
    }
}
