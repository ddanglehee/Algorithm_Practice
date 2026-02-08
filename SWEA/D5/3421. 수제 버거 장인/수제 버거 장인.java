import java.util.*;
import java.io.*;

public class Solution {

    static int N, M;
    static Set<Integer>[] mismatchSet = new HashSet[21];
    static int[] ab = new int[2];
    static boolean[] canPut = new boolean[21];
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T =  Integer.parseInt(br.readLine());
        for (int i = 1; i <= 20; i++) {
            mismatchSet[i] = new HashSet<>();
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            init();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                ab[0] = Integer.parseInt(st.nextToken());
                ab[1] = Integer.parseInt(st.nextToken());
                Arrays.sort(ab);

                mismatchSet[ab[0]].add(ab[1]);
            }

            makeNewBurger(1, canPut);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static void makeNewBurger(int index, boolean[] canPut) {
        if (index == N + 1) {
            answer++;
            return;
        }

        // 이 재료를 포함시키는 경우
        if (canPut[index]) {

            List<Integer> mismatchWithThis = new ArrayList<>(); // 이 재료를 넣었을 때 궁합 안 맞는 재료를 담는 리스트 (복원용)
            for (int i : mismatchSet[index]) {
                if (canPut[i]) {
                    mismatchWithThis.add(i);
                    canPut[i] = false;
                }
            }
            makeNewBurger(index + 1, canPut);
            for (int i : mismatchWithThis) {
                canPut[i] = true;
            }
        }

        // 이 재료를 포함시키지 않는 경우
        makeNewBurger(index + 1, canPut);
    }

    static void init() {
        answer = 0;
        for (int i = 1; i <= N; i++) {
            mismatchSet[i].clear();
            canPut[i] = true;
        }
    }
}
