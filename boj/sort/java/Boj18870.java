import java.util.*;
import java.io.*;

class Boj18870 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] array = new int[n];
        Set<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            array[i] = a;
            set.add(a);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(map.get(array[i])).append(" ");
        }

        System.out.print(sb);
    }
}