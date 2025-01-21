import java.io.*;

public class Boj1157 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        input = input.toUpperCase();
        int[] alphabetCount = new int[28];

        for (int i = 0; i < input.length(); i++) {
            int idx = input.charAt(i) - 'A';

            alphabetCount[idx]++;
        }

        char answer = 'A';
        int max = alphabetCount[0];
        for (int i = 1; i < 28; i++) {
            if (max != 0 && max == alphabetCount[i]) {
                answer = '?';
            }

            if (max < alphabetCount[i]) {
                answer = (char) (i + 'A');
                max = alphabetCount[i];
            }
        }

        System.out.print(answer);

    }
}
