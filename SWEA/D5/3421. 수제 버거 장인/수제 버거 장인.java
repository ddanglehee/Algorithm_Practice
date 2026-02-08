import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, a, b;
    static Set<Integer> mismatchSet = new HashSet<>();
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
            mismatchSet.clear();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = 1 << Integer.parseInt(st.nextToken());
                b = 1 << Integer.parseInt(st.nextToken());

                mismatchSet.add(a | b);
            }

            makeNewBurger(1, 0);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static void makeNewBurger(int index, int newBurger) {
        if (index == N + 1) {
            answer++;
            return;
        }

        // 이 재료 선택 안하는 경우
        makeNewBurger(index + 1, newBurger);

        // 이 재료 선택할 수 있는지 확인
        newBurger |= (1 << index);
        for (int mismatch : mismatchSet) {
            if ((mismatch & newBurger) == mismatch) return;
        }
        makeNewBurger(index + 1, newBurger);
    }
}
