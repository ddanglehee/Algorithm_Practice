import java.io.*;

public class Boj1522 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();

        int aCount = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == 'a') aCount++;
        }

        int bCount = 0;
        for (int i = 0; i < aCount; i++) {
            if (str.charAt(i) == 'b') bCount++;
        }

        int start = 0;
        int end = aCount - 1;
        int answer = bCount;
        while (start < n) {
            if (str.charAt(start) == 'b') bCount--;
            end = (end+1) % n;
            if (str.charAt(end) == 'b') bCount++;
            start++;

            answer = Math.min(answer, bCount);
        }

        System.out.println(answer);
    }
}
