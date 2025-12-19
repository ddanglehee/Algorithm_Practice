import java.io.*;
import java.util.*;

class Boj1038 {

    private static final ArrayList<Long> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < 10; i++) {
            answerList.add((long) i);
            getDecreasingNumber(i, i);
        }
        Collections.sort(answerList);
        if (answerList.size() <= N) {
            System.out.println(-1);
        } else {
            System.out.println(answerList.get(N));
        }
    }

    private static void getDecreasingNumber(long num, int max) {
        for (int i = 0; i < max; i++) {
            long cur = num * 10 + i;
            answerList.add(cur);
            getDecreasingNumber(cur, i);
        }
    }
}