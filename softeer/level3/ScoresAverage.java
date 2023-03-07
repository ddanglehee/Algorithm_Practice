import java.util.*;
import java.io.*;


public class ScoresAverage
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        double[] scoresPrefixSum = new double[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            double score = Double.parseDouble(st.nextToken());
            scoresPrefixSum[i] = scoresPrefixSum[i - 1] + score;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            double avg = (scoresPrefixSum[to] - scoresPrefixSum[from - 1]) / (to - from + 1);
            sb.append(String.format("%.2f", Math.round(avg * 100) / 100.0)).append("\n");
        }

        System.out.print(sb);
    }
}
