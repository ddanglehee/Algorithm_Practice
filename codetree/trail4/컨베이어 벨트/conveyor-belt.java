import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] arr = new int[2][N];
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
            arr[0][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int j = N-1; 0 <= j; j--) {
            arr[1][j] = Integer.parseInt(st.nextToken());
        }

        int tmp1, tmp2;
        while (T-- > 0) {
            tmp1 = arr[0][N-1];
            for (int i = N-2; 0 <= i; i--) {
                arr[0][i+1] = arr[0][i];
            }
           
            tmp2 = arr[1][0];
            for (int i = 1; i < N; i++) {
                arr[1][i-1] = arr[1][i];
            }

            arr[1][N-1] = tmp1;
            arr[0][0] = tmp2;
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < N; j++) {
            sb.append(arr[0][j] + " ");
        }
        sb.append("\n");
        for (int j = N-1; 0 <= j; j--) {
            sb.append(arr[1][j] + " ");
        }
        
        System.out.print(sb);
    }
}