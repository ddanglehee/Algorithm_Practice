import java.util.*;
import java.io.*;

public class Boj23290 {

    static int answer = 0;
    static int m;
    static int s;
    static int sharkR;
    static int sharkC;
    static Map<Location, ArrayList<Integer>> graph = new HashMap<>(); // Location에 있는 물고기의 방향 리스트
    static int[][] fishSmell = new int[5][5];
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1}; // 물고기 방향
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0}; // 상어 방향
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<int[]> dictionary = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        init();


        for (int count = 1; count <= s; count++) {

            // 1. 상어가 모든 물고기에게 복제 마법을 시전한다. 복제 마법은 시간이 조금 걸리기 때문에, 아래 5번에서 물고기가 복제되어 칸에 나타난다.
            Map<Location, ArrayList<Integer>> copiedGraph = new HashMap<>(graph); // 깊은 복사

            // 2. 모든 물고기가 한 칸 이동한다.
            moveFish();

            // 3. 상어가 연속해서 3칸 이동한다.
            moveShark();

            // 4. 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
            updateFishSmell(); // 연습이 거듭될수록 물고기 냄새 옅어짐

            // 5. 1에서 사용한 복제 마법이 완료된다.
            for (Location location: copiedGraph.keySet()) {
                ArrayList<Integer> copied = copiedGraph.get(location);
                if (graph.get(location) == null) {
                    graph.put(location, copied);
                } else {
                    graph.get(location).addAll(copied);
                }
            }
        }

        for (Location location: graph.keySet()) {
            answer += graph.get(location).size();
        }
        System.out.print(answer);
    }

    static void updateFishSmell() {
        for (int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                if (fishSmell[i][j] > 0) {
                    fishSmell[i][j]--;
                }
            }
        }
    }

    static void moveShark() {
        Set<Location> removedLocation = new HashSet<>(); // 상어가 지나가는 위치 set, Set으로 해야 [상좌우]처럼 칸을 재방문할 때 물고기를 중복해서 계산하는 거 막음
        int maxRemovedFishCount = 0;
        int maxSharkR = 0; int maxSharkC = 0;

        // 상어의 가능한 이동 방법 중
        for (int[] sharkD : dictionary) { // sharkD : 상상상, 상상좌 이런 정보 들어가있음

            int tmpR = sharkR; int tmpC = sharkC;
            Set<Location> tmpRemovedLocation = new HashSet<>(); // 제외되는 물고기 잠시 담아두는 리스트

            boolean check = true;
            for (int j = 0; j < 3; j++) {
                tmpR += dx[sharkD[j]]; tmpC += dy[sharkD[j]];

                if (!isInGraph(tmpR, tmpC))  {
                    // 연속해서 이동하는 칸 중에 격자의 범위를 벗어나는 칸이 있으면, 그 방법은 불가능한 이동 방법이다.
                    tmpRemovedLocation.clear();
                    check = false;
                    break;
                }
                Location location = new Location(tmpR, tmpC);
                tmpRemovedLocation.add(location);
            }

            int tmpRemovedFishCount = countRemovedFish(tmpRemovedLocation);

            // dictionary에 있는 방법들 중, 상어가 이동 중에 제외되는 물고기가 없는 경우도 고려하기 위해서
            if (tmpRemovedFishCount == 0 && maxSharkR == 0) {
                if (check) maxSharkR = tmpR; maxSharkC = tmpC;
            } else if (maxRemovedFishCount < tmpRemovedFishCount) { // 제외되는 물고기 수가 가장 많은 방법으로 이동 (사전 순)
                removedLocation = tmpRemovedLocation;
                maxRemovedFishCount = tmpRemovedFishCount;
                maxSharkR = tmpR; maxSharkC = tmpC;
            }

        }

        removeFish(removedLocation);
        sharkR = maxSharkR; sharkC = maxSharkC;
    }

    static void removeFish(Set<Location> removedLocation) {

        for (Location location: removedLocation) {

            if (graph.get(location) != null) {
                graph.get(location).clear(); // 그 칸에 있는 모든 물고기는 격자에서 제외되며
                fishSmell[location.r][location.c] = 3; // 제외되는 모든 물고기는 물고기 냄새를 남긴다
            }
        }
    }

    static int countRemovedFish(Set<Location> locations) {
        int count = 0;

        for (Location location: locations) {
            if (graph.get(location) != null) {
                count += graph.get(location).size();
            }
        }

        return count;
    }

    static void moveFish() {
        Map<Location, ArrayList<Integer>> newGraph = new HashMap<>(); // 물고기들이 이동한 정보

        for (Location location: graph.keySet()) {
            ArrayList<Integer> fishDList = graph.get(location);

            for (int fishD: fishDList) {
                int r = location.r; int c = location.c;
                boolean moved = false;
                // 각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다.
                for (int i = 0; i <= 7; i++) {
                    int newD = (fishD + 8 - i) % 8;
                    int newR = r + dr[newD]; int newC = c + dc[newD];

                    if (canFishGo(newR, newC)) {
                        addFish(newR, newC, newD, newGraph);
                        moved = true;
                        break;
                    }
                }

                if (!moved) addFish(r, c, fishD, newGraph); // (fishD + 7) % 8 : 물고기가 이동할 수 있는 방향 찾을 때 마지막으로 멈춘 방향으로 했는데 안돼서 삽질을 아주마니함
            }
        }

        graph = newGraph;
    }

    static boolean isInGraph(int r, int c) {
        return 1 <= r && r <= 4 && 1 <= c && c <= 4;
    }

    static boolean canFishGo(int r, int c) {
        // 격자의 범위를 벗어나는 칸, 물고기의 냄새가 있는 칸, 상어가 있는 칸으로는 이동할 수 없다
        return isInGraph(r, c) && fishSmell[r][c] == 0 && (sharkR != r || sharkC != c);
    }

    // 매개변수로 받은 맵에 (r, c)에 방향d를 가진 물고기 추가
    static void addFish(int r, int c, int d, Map<Location, ArrayList<Integer>> graph) {
        Location location = new Location(r, c);
        if (graph.get(location) == null) {
            graph.put(location, new ArrayList<>());
        }
        graph.get(location).add(d);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            addFish(r, c, d - 1, graph);
        }

        st = new StringTokenizer(br.readLine());
        sharkR = Integer.parseInt(st.nextToken());
        sharkC = Integer.parseInt(st.nextToken());

        initDictionary();
    }

    static void initDictionary() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    dictionary.add(new int[] {i, j, k});
                }
            }
        }
    }

    static class Location {
        int r;
        int c;

        Location(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            Location location = (Location) obj;
            return location.r == this.r && location.c == this.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}