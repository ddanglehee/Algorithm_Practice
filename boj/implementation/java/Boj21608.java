import java.util.*;
import java.io.*;

public class Boj21608 {

    static int n;
    static ArrayList<Integer>[] favoriteFriends;
    static int[][] graph;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        init();
        calculateScore();
        System.out.print(answer);
    }

    public static void calculateScore() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int student = graph[r][c];
                int count = 0;

                for (int d = 0; d < 4; d++) {
                    int nR = r + dr[d]; int nC = c + dc[d];

                    if (!(0 <= nR && nR < n && 0 <= nC && nC < n)) continue;
                    if (favoriteFriends[student].contains(graph[nR][nC])) count++;
                }

                if (count == 1) {
                    answer += 1;
                } else if (count == 2) {
                    answer += 10;
                } else if (count == 3) {
                    answer += 100;
                } else if (count == 4) {
                    answer += 1000;
                }
            }
        }
    }

    public static void arrange(int student) {
        int maxFavoriteCount = 0;
        int maxEmptyCount = 0;
        int[] result = {-1, -1};

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (graph[r][c] != 0) continue;

                if (result[0] == -1) {
                    result[0] = r; result[1] = c;
                }

                int tmpFavoriteCount = 0;
                int tmpEmptyCount = 0;

                for (int d = 0; d < 4; d++) {
                    int nR = r + dr[d]; int nC = c + dc[d];

                    if (!(0 <= nR && nR < n && 0 <= nC && nC < n)) continue;
                    if (graph[nR][nC] == 0) {
                        tmpEmptyCount++;
                    } else if (favoriteFriends[student].contains(graph[nR][nC])) {
                        tmpFavoriteCount++;
                    }
                }

                if (maxFavoriteCount < tmpFavoriteCount) {
                    maxFavoriteCount = tmpFavoriteCount;
                    maxEmptyCount = tmpEmptyCount;
                    result[0] = r; result[1] = c;
                } else if (maxFavoriteCount == tmpFavoriteCount && maxEmptyCount < tmpEmptyCount) {
                    maxEmptyCount = tmpEmptyCount;
                    result[0] = r; result[1] = c;
                }
            }
        }

        graph[result[0]][result[1]] = student;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        favoriteFriends = new ArrayList[n * n + 1];

        StringTokenizer st;
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            favoriteFriends[student] = new ArrayList<>();
            for (int k = 0; k < 4; k++) {
                favoriteFriends[student].add(Integer.parseInt(st.nextToken()));
            }
            arrange(student);
        }
    }
}
