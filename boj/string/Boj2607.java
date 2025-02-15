import java.io.*;

public class Boj2607 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int answer = 0;
        boolean isAllZero;
        boolean isAnswer;
        int oneCount, minusOneCount;
        int[] alphabetCount = new int[26];
        for (int i = 0; i < n - 1; i++) {
            isAllZero = true;
            isAnswer = true;
            oneCount = 0;
            minusOneCount = 0;

            for (int k = 0; k < 26; k++) {
                alphabetCount[k] = 0;
            }

            for (int k = 0; k < str.length(); k++) {
                alphabetCount[str.charAt(k) - 'A']++;
            }

            String cmpStr = br.readLine();
            for (int k = 0; k < cmpStr.length(); k++) {
                alphabetCount[cmpStr.charAt(k) - 'A']--;
            }

            for (int k = 0; k < 26; k++) {
                if (alphabetCount[k] == 1) {
                    oneCount++;
                    isAllZero = false;
                } else if (alphabetCount[k] == -1) {
                    minusOneCount++;
                    isAllZero = false;
                } else if (alphabetCount[k] != 0) {
                    isAnswer = false;
                    break;
                }
            }

            if (isAnswer && (isAllZero || (oneCount == 1 && minusOneCount == 1) ||
                    (oneCount == 1 && minusOneCount == 0) || (oneCount == 0 && minusOneCount == 1))) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
