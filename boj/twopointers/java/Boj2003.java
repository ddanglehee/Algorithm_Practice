import java.io.*;
import java.util.*;

public class Boj2003 {

    static int N, M;
    static int[] array;
    static int low = 0;
    static int high = 0;
    static int sum = 0;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        array = new int[N];

        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st2.nextToken());
        }

        sum = array[0];
        while (high < N) {
            if (sum < M) {
                if (high == N - 1) break;
                sum += array[++high]; // high를 옮기고나서 더해줘야함
            } else if (sum == M) {
                result++;
                sum -= array[low++]; // low를 옮기기 전에 빼줘야함
            } else {
                sum -= array[low++];
            }
        }

        System.out.println(result);
    }
}
