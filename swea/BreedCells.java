import java.util.*;
import java.io.*;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K;
    static Map<Location, Cell> activeMap = new HashMap<>();
    static Map<Location, Boolean> deadMap = new HashMap<>();
    static Set<Location> tmpDead = new HashSet<>();
    static Map<Location, Cell> breedMap = new HashMap<>();
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            init();

            for (int t = 1; t <= K; t++) {
                breedMap.clear(); tmpDead.clear();

                for (Location location: activeMap.keySet()) {
                    Cell cell = activeMap.get(location);

                    // 1. cell의 생존시간 + 1
                    cell.time++;
                    if (cell.time == cell.life) { // 방금 cell이 활성화 됐다면 배양
                        tryBreed(cell.life, location);
                    } else if (cell.time == 2 * cell.life) { //cell 죽음
                        tmpDead.add(location);
                        deadMap.put(location, true);
                    }
                }
                // 2. 죽은 세포 activeMap에서 빼기
                for (Location location: tmpDead) {
                    activeMap.remove(location);
                }
                // 3. breedMap에 있는 것 map에 넣기
                if (t != K) {
                    activeMap.putAll(breedMap);
                }
            }

            int count = activeMap.keySet().size();
            sb.append("#").append(test_case).append(" ").append(count).append("\n");
        }

        System.out.print(sb);
    }

    static void tryBreed(int life, Location location) {
        for (int d = 0; d < 4; d++) {
            Location nLocation = new Location(location.r + dr[d], location.c + dc[d]);
            if (activeMap.get(nLocation) != null || deadMap.get(nLocation) != null) continue;
            if (breedMap.get(nLocation) == null || breedMap.get(nLocation).life < life) {
                breedMap.put(nLocation, new Cell(life, -1));
            }
        }
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        activeMap.clear(); deadMap.clear();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int life = Integer.parseInt(st.nextToken());
                if (life != 0) {
                    activeMap.put(new Location(i, j), new Cell(life, 0));
                }
            }
        }
    }
}

class Cell {
    int life;
    int time;

    public Cell(int life, int time) {
        this.life = life;
        this.time = time;
    }
}

class Location {
    int r;
    int c;

    public Location(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        Location location = (Location) o;
        return location.r == r && location.c == c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}