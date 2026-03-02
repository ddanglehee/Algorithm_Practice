import java.util.*;
import java.io.*;

class Main {

    static int N;
    static String[][] map;
    static ArrayList<Location> teacherLocations = new  ArrayList<>();
    static ArrayList<Location> candidateLocationList = new ArrayList<>();
    static Location[] selectedLocation = new Location[3];
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        // 입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new String[N][N];
        StringTokenizer st;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = st.nextToken();
                if (map[r][c].equals("T")) {
                    teacherLocations.add(new Location(r, c));
                } else if (map[r][c].equals("X")) {
                    candidateLocationList.add(new Location(r, c));
                }
            }
        }

        // 조합
        if (combination(0, 0)) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }

    static boolean combination(int index, int count) {
        if (count == 3) {
            for (int i = 0; i < 3; i++) {
                map[selectedLocation[i].r][selectedLocation[i].c] = "O";
            }
            if (check()) return true;
            for (int i = 0; i < 3; i++) {
                map[selectedLocation[i].r][selectedLocation[i].c] = "X";
            }
            return false;
        }

        for (int i = index; i < candidateLocationList.size(); i++) {
            selectedLocation[count] = candidateLocationList.get(i);
            if (combination(i + 1, count + 1)) return true;
        }

        return false;
    }

    static boolean check() {
        for (int t = 0; t < teacherLocations.size(); t++) {
            Location teacherLocation = teacherLocations.get(t);
            int r = teacherLocation.r;
            int c = teacherLocation.c;

            for (int d = 0; d < 4; d++) {
                int nR = r + dr[d];
                int nC = c + dc[d];

                while (isInMap(nR, nC)) {
                    if (map[nR][nC].equals("T") || map[nR][nC].equals("O")) {
                        break;
                    }
                    else if (map[nR][nC].equals("S") ) {
                        return false;
                    }

                    nR += dr[d];
                    nC += dc[d];
                }
            }
        }
        return true;
    }

    static boolean isInMap(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object obj) {
            Location location = (Location) obj;
            return this.r == location.r && this.c == location.c;
        }
    }
}