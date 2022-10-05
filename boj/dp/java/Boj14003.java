import java.util.*;
import java.io.*;

public class Boj14003 {

    static final int INF = Integer.MIN_VALUE;
    static int N;
    static int[] A;
    static int[] L;
    static int[] S;
    static int maxLength = 1;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        L = new int[N + 1];
        S = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int a = Integer.parseInt(st.nextToken());
            A[i] = a;
        }

        L[1] = A[1];
        S[1] = 1;
        for (int i = 2; i <= N; i++) {

            if (L[maxLength] < A[i]) {
                L[++maxLength] = A[i];
                S[i] = maxLength;
            } else {
                int start = 1, end = maxLength;

                while (start < end) {
                    int mid = (start + end) / 2;

                    if (A[i] <= L[mid]) {
                        end = mid;
                    } else {
                        start = mid + 1;
                    }
                }

                L[end] = A[i];
                S[i] = end;
            }
        }
        sb.append(maxLength).append("\n");
        int s = maxLength;
        int j = N;
        int[] answer = new int[maxLength + 1];
        while (true) {
            if (S[j] == s) {
                answer[s] = A[j];
                s--;
            }
            j--;
            if (s == 0 || j == 0) break;
        }

        for (int i = 1; i <= maxLength; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
