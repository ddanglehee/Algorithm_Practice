import java.util.*;
import java.io.*;


public class GBC
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String args[]) throws IOException
    {
        int answer = 0;
        int[] limitedSpeed = new int[101];

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int start = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            for (int j = start; j < start + end; j++) {
                limitedSpeed[j] = speed;
            }
            start = start + end;
        }
        limitedSpeed[0] = limitedSpeed[1];

        start = 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            for (int j = start; j < start + end; j++) {
                if (limitedSpeed[j] < speed && answer < speed - limitedSpeed[j]) {
                    answer = speed - limitedSpeed[j];
                }
            }
            start = start + end;
        }

        System.out.println(answer);
    }
}
