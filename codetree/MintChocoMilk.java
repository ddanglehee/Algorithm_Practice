import java.util.*;
import java.io.*;

public class Main {

    private static int N, T;
    private static Student[][] map;
    private static boolean[][] visited;
    private static ArrayList<Student> groupKings = new ArrayList<>();
    private static ArrayList<Student> groupMembers = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        init();

        while (0 < T--) {
            noon();
            night();
        }

        System.out.print(sb);
    }

    private static void noon() {
        initVisited();
        groupKings.clear();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visited[i][j]) continue;

                // 그룹 만들기
                groupMembers.clear();
                makeGroup(map[i][j].F, i, j);

                // 대표자는 groupKings에 넣기
                sortGroupMembers();
                groupKings.add(groupMembers.get(0));

                // 대표자는 신앙심 + (그룹원 수 - 1)
                groupMembers.get(0).B += groupMembers.size();
            }
        }
    }

    private static void night() {
        sortGroupKings();
        initVisited();

        for (int i = 0; i < groupKings.size(); i++) {
            Student king = groupKings.get(i);
            if (visited[king.r][king.c]) continue;

            // 전파 방향
            int d = king.B % 4;

            // 전파자의 신앙심, 간절함 설정
            int x = king.B - 1;
            king.B = 1;

            // 전파
            int r = king.r + dr[d];
            int c = king.c + dc[d];
            while (isInMap(r, c) && x != 0) {

                if (map[r][c].F == king.F) {
                    r += dr[d];
                    c += dc[d];
                    continue;
                }

                int y = map[r][c].B;
                // 강한 전파
                if (x > y) {
                    map[r][c].setF(king.F);
                    x -= (y + 1);
                    map[r][c].B++;
                }
                // 약한 전파
                else {
                    map[r][c].setF(map[r][c].F |= king.F) ;
                    map[r][c].B += x;
                    x = 0;
                }
                visited[r][c] = true;
                r += dr[d];
                c += dc[d];
            }
        }

        recordAnswer();
    }

    private static void recordAnswer() {
        int[] answer = new int[8];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Student s = map[i][j];
                answer[s.F] += s.B;
            }
        }

        sb.append(answer[7]).append(" ").append(answer[3]).append(" ").append(answer[5]).append(" ").append(answer[6]).append(" ").append(answer[4]).append(" ").append(answer[2]).append(" ").append(answer[1]).append("\n");
    }

    private static void sortGroupKings() {
        groupKings.sort((o1, o2) -> {
            if (o1.groupPriority != o2.groupPriority) {
                return o1.groupPriority - o2.groupPriority;
            } else if (o1.B != o2.B) {
                return o2.B - o1.B;
            } else if (o1.r != o2.r) {
                return o1.r - o2.r;
            } else {
                return o1.c - o2.c;
            }
        });
    }

    private static void sortGroupMembers() {
        groupMembers.sort((o1, o2) -> {
            if (o1.B != o2.B) {
                return o2.B - o1.B;
            } else if (o1.r != o2.r) {
                return o1.r - o2.r;
            } else {
                return o1.c - o2.c;
            }
        });
    }

    private static void makeGroup(int f, int r, int c) {
        visited[r][c] = true;
        groupMembers.add(map[r][c]);

        for (int d = 0; d < 4; d++) {
            int nR = r + dr[d];
            int nC = c + dc[d];

            if (canGo(nR, nC) && map[nR][nC].F == f) {
                makeGroup(f, nR, nC);
            }
        }
    }

    private static boolean isInMap(int r, int c) {
        return 0 < r && r <= N && 0 < c && c <= N;
    }

    private static boolean canGo(int r, int c) {
        return isInMap(r, c) && !visited[r][c];
    }

    private static void initVisited() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                visited[i][j] = false;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new Student[N+1][N+1];
        visited = new boolean[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= N; j++) {
                char f = input.charAt(j - 1);

                if (f == 'T') {
                    map[i][j] = new Student(i, j, 1);
                } else if (f == 'C') {
                    map[i][j] = new Student(i, j, 2);
                } else {
                    map[i][j] = new Student(i, j, 4);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int b = Integer.parseInt(st.nextToken());

                map[i][j].setB(b);
            }
        }
    }

    static class Student {
        int F;
        int B;
        int groupPriority;
        int r;
        int c;

        public Student(int r, int c, int F) {
            this.r = r;
            this.c = c;
            setF(F);
        }

        public void setF(int F) {
            this.F = F;

            if (F == 7) {
                groupPriority = 3;
            } else if (F == 5 || F == 6 || F == 3) {
                groupPriority = 2;
            } else {
                groupPriority = 1;
            }
        }

        public void setB(int B) {
            this.B = B;
        }
    }
}