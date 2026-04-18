import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static Wire[] wires;
    static int[] lis;
    static int[] record; // 각 전깃줄이 LIS의 몇 번 인덱스에 들어갔었는지 기록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        wires = new Wire[N];
        lis = new int[N];
        record = new int[N];

        Arrays.fill(lis, 500001);
        StringTokenizer st;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            wires[n] = new Wire(a, b);
        }

        Arrays.sort(wires);

        int len = 1;
        lis[0] = wires[0].b;
        for (int n = 1; n < N; n++) {
            int b = wires[n].b;

            int point = Arrays.binarySearch(lis, b);
            if (point < 0) {
                point = -1 * (point + 1);
            }

            lis[point] = b;
            record[n] = point;
            if (len == point) {
                len++;
            }
        }

        // 역추적으로 LIS에 포함된 전깃줄 표시
        boolean[] isLIS = new boolean[N];
        int findIdx = len - 1;
        for (int n = N - 1; n >= 0; n--) {
            if (record[n] == findIdx) {
                isLIS[n] = true;
                findIdx--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(N - len).append("\n");
        for (int n = 0; n < N; n++) {
            if (!isLIS[n]) {
                sb.append(wires[n].a).append("\n");
            }
        }

        System.out.print(sb);
    }

    static class Wire implements Comparable<Wire> {
        int a;
        int b;

        public Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Wire o) {
            return Integer.compare(this.a, o.a);
        }
    }
}
