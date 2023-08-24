import java.io.*;
import java.util.*;

public class Boj2212 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> sensorPointList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int point = Integer.parseInt(st.nextToken());
            sensorPointList.add(point);
        }
        Collections.sort(sensorPointList);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < sensorPointList.size() - 1; i++) {
            pq.add(sensorPointList.get(i + 1) - sensorPointList.get(i));
        }

        int answer = 0;
        for (int i = 0; i < n - k; i++) {
            answer += pq.poll();
        }

        System.out.print(answer);
    }
}
