import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, a, b;
    static int[] mismatch = new int[20];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T =  Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            answer = 0;
            for (int i = 0; i < N; i++) {
                mismatch[i] = 0;
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken()) - 1;
                b = Integer.parseInt(st.nextToken()) - 1;

                mismatch[a] |= (1 << b);
                mismatch[b] |= (1 << a);
            }

            makeNewBurger(0, 0);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static void makeNewBurger(int index, int newBurger) {
        if (index == N) {
            answer++;
            return;
        }

        // 이 재료 선택 안하는 경우
        makeNewBurger(index + 1, newBurger);

        // 이 재료 선택하는 경우
        if ((newBurger & mismatch[index]) == 0) {
            makeNewBurger(index + 1, newBurger | (1 << index));
        }
    }
}
