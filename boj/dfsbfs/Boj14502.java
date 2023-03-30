import java.util.*;
import java.io.*;

class Boj14502 {

    static int answer = 0;
    static int n;
    static int m;
    static int[][] graph;
    static ArrayList<int[]> emptyPointList = new ArrayList<>();
    static ArrayList<int[]> virusPointList = new ArrayList<>();
    static int safetyCount = 0;
    static int remainSafetyCount;
    static ArrayList<int[]> combinationList = new ArrayList<>();
    static boolean[] isChecked;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i <= emptyPointList.size() - 3; i++) {
            combination(i, 1, new int[3]);
        }

        for (int[] combination: combinationList) {
            wall(combination, 1); // 벽 세우기
            spreadVirus();
            wall(combination, 0); // 벽 없애기
        }

        System.out.print(answer);
    }

    static void spreadVirus() {
        initState();
        for (int[] point: virusPointList){
            dfs(point);
        }
        answer = Math.max(answer, remainSafetyCount);
    }

    static void initState() {
        remainSafetyCount = safetyCount - 3; // 벽 3개 새로 세운 거 빼주어야 한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }
    }

    static void dfs(int[] curPoint) {
        int r = curPoint[0]; int c = curPoint[1];
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nextR = r + dr[d]; int nextC = c + dc[d];
            if (0 <= nextR && nextR < n && 0 <= nextC && nextC < m && graph[nextR][nextC] == 0 && !visited[nextR][nextC]) {
                remainSafetyCount--;
                dfs(new int[] {nextR, nextC});
            }
        }
    }

    static void wall(int[] combination, int info) {
        for (int i: combination) {
            int[] point = emptyPointList.get(i);
            graph[point[0]][point[1]] = info;
        }
    }

    // 빈 칸 중 벽을 세울 3개의 칸 조합 구하기
    static void combination(int i, int count, int[] tmpCombination) {
        tmpCombination[count - 1] = i;

        if (count == 3) {
            makeNewCombination(tmpCombination);
            return;
        }

        for (int k = i + 1; k < emptyPointList.size(); k++) {
            if (isChecked[k]) continue;
            isChecked[k] = true;
            combination(k, count + 1, tmpCombination);
            isChecked[k] = false;
        }
    }

    static void makeNewCombination(int[] combination) {
        int[] newCombination = combination.clone();
        combinationList.add(newCombination);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int info = Integer.parseInt(st.nextToken());
                graph[i][j] = info;
                if (info == 0) {
                    emptyPointList.add(new int[] {i, j});
                    safetyCount++;
                } else if (info == 2) {
                    virusPointList.add(new int[] {i, j});
                }
            }
        }
        isChecked = new boolean[emptyPointList.size()];
    }
}