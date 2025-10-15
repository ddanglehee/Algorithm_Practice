import java.util.*;
import java.io.*;

class Boj15666 {

    private static int N, M;
    private static ArrayList<Integer> list;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        list = new ArrayList<>(set);
        Collections.sort(list);

        backtracking(0, 0, new ArrayList<>());
        System.out.print(sb);
    }

    private static void backtracking(int si, int m, List<String> curList) {
        if (m == M) {
            sb.append(String.join(" ", curList)).append("\n");
            return;
        }

        for (int i = si; i < list.size(); i++) {
            curList.add(String.valueOf(list.get(i)));
            backtracking(i, m + 1, curList);
            curList.removeLast();
        }
    }
}
