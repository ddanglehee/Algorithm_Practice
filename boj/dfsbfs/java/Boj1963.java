import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Boj1963 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int from, to;
    static boolean[] isPrime = new boolean[10001];
    static boolean[] visited = new boolean[10001];

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        initIsPrime();

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            Arrays.fill(visited, false);
            visited[from] = true;
            if (!solution(from, to)) {
                sb.append("Impossible\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean solution(int from, int to) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{from, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == to) {
                sb.append(cur[1]).append("\n");
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int[] arr = Stream.of(new StringBuffer(String.valueOf(cur[0])).reverse().toString().split("")).mapToInt(Integer::parseInt).toArray();
                for (int j = 1; j <= 9; j++) {
                    if (i == 3 && (arr[i] + 1) % 10 == 0) {
                        arr[i]++;
                        continue;
                    }

                    arr[i] = (arr[i] + 1) % 10;
                    int newInt = arrayToInt(arr);

                    if (isPrime[newInt] && !visited[newInt]) {
                        visited[newInt] = true;
                        queue.offer(new int[]{newInt, cur[1] + 1});
                    }
                }
            }
        }

        return false;
    }

    private static int arrayToInt(int[] arr) {
        int newInt = 0;
        for (int i = 0; i < 4; i++) {
            newInt += (int) (arr[i] * Math.pow(10, i));
        }

        return newInt;
    }

    private static void initIsPrime() {
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= 10000; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= 10000; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
