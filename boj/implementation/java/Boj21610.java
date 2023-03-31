import java.util.*;
import java.io.*;

class Boj21610 {

    static int n;
    static int m;
    static int d;
    static int s;
    static int[][] water;
    static int totalWater = 0;
    static ArrayList<int[]> curCloud = new ArrayList<>();
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            moveCloud(d - 1, s % n); // 1. 모든 구름이 di 방향으로 si칸 이동
            raining(); // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
            copyWaterMagic(); // 4. 물복사버그 마법 사용
            createNewCloud(); // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                totalWater += water[i][j];
            }
        }

        System.out.print(totalWater);
    }

    static void createNewCloud() {
        boolean[][] visited = new boolean[n][n];
        for (int[] location: curCloud) {
            visited[location[0]][location[1]] = true;
        }
        curCloud.clear();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (2 <= water[i][j]) {
                    water[i][j] -= 2;
                    curCloud.add(new int[] {i, j});
                }
            }
        }
    }

    static void copyWaterMagic() {
        for (int[] location: curCloud) {
            int increaseAmount = 0;
            for (int i = 1; i < 8; i += 2) {
                int r = location[0] + dr[i]; int c = location[1] + dc[i];
                if (isInGraph(r, c) && water[r][c] != 0) increaseAmount++;
            }
            water[location[0]][location[1]] += increaseAmount;
        }
    }

    static boolean isInGraph(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    static void raining() {
        for (int[] location: curCloud) {
            water[location[0]][location[1]]++;
        }
    }

    static void moveCloud(int d, int s) {
        for (int[] location : curCloud) {
            location[0] = (location[0] + dr[d] * s + n) % n;
            location[1] = (location[1] + dc[d] * s + n) % n;
        }
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        water = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int w = Integer.parseInt(st.nextToken());
                water[i][j] = w;
            }
        }

        curCloud.add(new int[] {n - 2, 0});
        curCloud.add(new int[] {n - 2, 1});
        curCloud.add(new int[] {n - 1, 0});
        curCloud.add(new int[] {n - 1, 1});
    }
}