import java.util.*;
import java.io.*;


public class Speed8Gearbox
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String args[]) throws IOException
    {
        int[] ascending = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        int[] descending = new int[] {8, 7, 6, 5, 4, 3, 2, 1};
        int[] input = new int[8];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 8; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        if (Arrays.equals(input, ascending)) {
            System.out.println("ascending");
        } else if (Arrays.equals(input, descending)) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }
}