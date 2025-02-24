import java.util.*;
import java.io.*;

public class Boj1946 {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n, answer;
        ArrayList<int[]> list = new ArrayList<>();
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            list.clear();
            answer = 1;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int[] a = new int[2];
                a[0] = Integer.parseInt(st.nextToken());
                a[1] = Integer.parseInt(st.nextToken());
                list.add(a);
            }
            list.sort((o1, o2) -> o1[0] - o2[0]);

            int preInterviewScore = list.get(0)[1];
            for (int i = 1; i < n; i++) {
                int curInterviewScore = list.get(i)[1];
                if (curInterviewScore < preInterviewScore) {
                    answer++;
                    preInterviewScore = curInterviewScore;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
