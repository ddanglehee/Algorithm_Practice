import java.util.*;
import java.io.*;

public class Boj2251 {

    static int a, b, c;
    static LinkedList<AmountInfo> queue = new LinkedList<>();
    static boolean[][] visited;
    static ArrayList<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visited = new boolean[a + 1][b + 1];
        visited[0][0] = true;
        queue.add(new AmountInfo(0, 0, c));

        while (!queue.isEmpty()) {
            AmountInfo info = queue.removeFirst();

            if (info.x == 0) {
                answerList.add(info.z);
            }

            int pourAmount;
            // a -> b
            pourAmount = Math.min(info.x, b - info.y);
            pour(info.x - pourAmount, info.y + pourAmount);

            // a -> c
            pourAmount = Math.min(info.x, c - info.z);
            pour(info.x - pourAmount, info.y);

            // b -> c
            pourAmount = Math.min(info.y, c - info.z);
            pour(info.x, info.y - pourAmount);

            // b -> a
            pourAmount = Math.min(info.y, a - info.x);
            pour(info.x + pourAmount, info.y - pourAmount);

            // c -> a
            pourAmount = Math.min(info.z, a - info.x);
            pour(info.x + pourAmount, info.y);

            // c -> b
            pourAmount = Math.min(info.z, b - info.y);
            pour(info.x, info.y + pourAmount);
        }

        Collections.sort(answerList);
        StringBuilder sb = new StringBuilder();
        for (int amount: answerList) {
            sb.append(amount).append(" ");
        }
        System.out.print(sb);
    }

    private static void pour(int x, int y) {
        if (visited[x][y]) return;
        visited[x][y] = true;
        queue.add(new AmountInfo(x, y, c - x - y));
    }
}

class AmountInfo {

    int x;
    int y;
    int z;

    public AmountInfo(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}