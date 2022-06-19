import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = new int[9];

        for (int i = 0; i < 9; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < 9; i++) {
            if (max < numbers[i]) {
                max = numbers[i];
                maxIndex = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(max);
        sb.append("\n");
        sb.append(maxIndex + 1);
        System.out.println(sb);
    }
}
