import java.io.*;
import java.util.*;

public class Boj2143 {

    static int T, n, m;
    static int[] A;
    static int[] B;
    static ArrayList<Long> subsetA = new ArrayList<>();
    static ArrayList<Long> subsetB = new ArrayList<>();
    static long result = 0;

    public static void main(String[] args) throws IOException {
        init();

        subset(A, subsetA);
        subset(B, subsetB);

        Collections.sort(subsetA);
        Collections.sort(subsetB, Collections.reverseOrder());

        solution();
        System.out.println(result);
    }

    static void solution() {
        int a = 0, b = 0; // index
        long sum;

        while (true) {
            sum = subsetA.get(a) + subsetB.get(b);

            if (sum == T) {
                long currentA = subsetA.get(a);
                long currentB = subsetB.get(b);
                long countA = 0, countB = 0;
                while (a < subsetA.size() && currentA == subsetA.get(a)) {
                    countA++;
                    a++;
                }
                while (b < subsetB.size() && currentB == subsetB.get(b)) {
                    countB++;
                    b++;
                }
                result += (countA * countB);
            } else if (sum < T) { // sum이 구하고자 하는 것보다 부족하면 a를 늘려야한다.
                a++;
            } else {
                b++;
            }

            if (a == subsetA.size() || b == subsetB.size()) break;
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void subset(int[] array, ArrayList<Long> subset) {
        for (int i = 0; i < array.length; i++) {
            long sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                subset.add(sum);
            }
        }
    }
}
