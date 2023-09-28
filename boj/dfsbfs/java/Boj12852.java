import java.io.*;
import java.util.*;

public class Boj12852 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[n + 1];
        ArrayDeque<Info> queue = new ArrayDeque<>();
        visited[n] = true;
        queue.add(new Info(n, new ArrayList<>()));

        ArrayList<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            Info info = queue.removeFirst();

            if (info.num == 1) {
                answer = info.list;
                break;
            }

            if (info.num % 3 == 0 && !visited[info.num / 3]) {
                queue.add(new Info(info.num / 3, info.list));
            }
            if (info.num % 2 == 0 && !visited[info.num / 2]) {
                queue.add(new Info(info.num / 2, info.list));
            }
            if (!visited[info.num - 1]) queue.add(new Info(info.num - 1, info.list));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size() - 1).append("\n");
        for (int num: answer) {
            sb.append(num).append(" ");
        }
        System.out.print(sb);
    }

    static class Info {
        int num;
        ArrayList<Integer> list = new ArrayList<>();

        public Info(int num, ArrayList<Integer> list) {
            this.num = num;
            this.list.addAll(list);
            this.list.add(num);
        }
    }
}
