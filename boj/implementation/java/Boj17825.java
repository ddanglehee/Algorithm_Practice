import java.util.*;
import java.io.*;

public class Boj17825 {

    static int[][] graph = { {0, 1}, {2, 2}, {4, 3}, {6, 4}, {8, 5}, {10, 6}, {12, 7}, {14, 8}, {16, 9}, {18, 10},
            {20, 11}, {22, 12}, {24, 13}, {26, 14}, {28, 15}, {30, 16}, {32, 17}, {34, 18}, {36, 19}, {38, 20}, {40, 21},
            {0, 0}, {13, 23}, {16, 24}, {19, 25}, {25, 26}, {30, 27}, {35, 20}, {22, 29}, {24, 25},
            {28, 31}, {27, 32}, {26, 25}
    }; // {점수, 다음}
    static HashMap<Integer, Integer> blue = new HashMap<>() {{
        put(5, 22);
        put(10, 28);
        put(15, 30);
    }}; // 파란색 {현재, 다음}
    static int[] horse = new int[4];
    static int[] diceInfo = new int[10];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            int dice = Integer.parseInt(st.nextToken());
            diceInfo[i] = dice;
        }

        dfs(0, 0, 0);
        System.out.print(answer);
    }

    private static void dfs(int horseIdx, int score, int diceIdx) {
        int cur = horse[horseIdx];
        int count = diceInfo[diceIdx];

        if (blue.containsKey(cur)) {
            horse[horseIdx] = blue.get(cur);
            count--;
        }

        for (int i = 0; i < count; i++) {
            horse[horseIdx] = graph[horse[horseIdx]][1];
            if (horse[horseIdx] == 21) break;
        }

        for (int h = 0; h < 4; h++) {
            if (h == horseIdx) continue;
            if (horse[h] != 21 && horse[horseIdx] == horse[h]) {
                horse[horseIdx] = cur;
                return;
            }
        }

        answer = Math.max(answer, score + graph[horse[horseIdx]][0]);

        if (diceIdx < 9) {
            for (int h = 0; h < 4; h++) {
                if (horse[h] == 21) continue;
                dfs(h, score + graph[horse[horseIdx]][0], diceIdx + 1);
            }
        }

        horse[horseIdx] = cur;
    }
}
