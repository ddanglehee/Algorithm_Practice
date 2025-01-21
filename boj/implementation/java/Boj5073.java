import java.util.*;
import java.io.*;

public class Boj5073 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[] intArray;

        while (true) {
            st = new StringTokenizer(br.readLine());
            intArray = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            Arrays.sort(intArray);

            if (intArray[0] == 0) break;

            if (intArray[0] + intArray[1] <= intArray[2]) {
                sb.append("Invalid\n");
            } else if (intArray[0] == intArray[1] && intArray[1] == intArray[2]) {
                sb.append("Equilateral\n");
            } else if (intArray[0] == intArray[1] || intArray[1] == intArray[2]) {
                sb.append("Isosceles\n");
            } else {
                sb.append("Scalene\n");
            }
        }

        System.out.print(sb);
    }
}
