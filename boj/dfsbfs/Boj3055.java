import java.io.*;
import java.util.*;

public class Boj3055 {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int R, C;
    static char[][] map;
    static int startR;
    static int startC;
    static int[][] distanceMap;
    static LinkedList<Node> queue = new LinkedList<>();
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st1.nextToken());
        C = Integer.parseInt(st1.nextToken());

        map = new char[R + 2][C + 2];
        distanceMap = new int[R + 2][C + 2];
        for (int i = 0; i < R + 2; i++) {
            for (int j = 0; j < C + 2; j++) {
                distanceMap[i][j] = 987654321;
            }
        }

        for (int i = 1; i <= R; i++) {
            String tmpString = br.readLine();
            for (int j = 1; j <= tmpString.length(); j++) {
                map[i][j] = tmpString.charAt(j - 1);
                if (map[i][j] == '*') {
                    queue.offer(new Node(i, j, map[i][j]));
                } else if (map[i][j] == 'S') {
                    startR = i; startC = j;
                    distanceMap[i][j] = 0;
                }
            }
        }

        queue.offer(new Node(startR, startC, 'S'));


        while (!queue.isEmpty()) {
            // 1. 큐에서 꺼내옴
            Node currentNode = queue.poll();
            int currentR = currentNode.r;
            int currentC = currentNode.c;
            int currentType = currentNode.type;

            // 2. 목적지인가? - 비버집인 경우
            if (currentNode.type == 'D') {
                result = distanceMap[currentR][currentC];
                break;
            } else {
                // 3. 연결된 곳 순회
                for (int i = 0; i < 4; i++) {
                    int tmpR = currentR + dr[i];
                    int tmpC = currentC + dc[i];

                    // 4. 갈 수 있는가?
                    if (0 < tmpR && tmpR <= R && 0 < tmpC && tmpC <= C) {
                        int nextType = map[tmpR][tmpC];
                        // 5. 체크인
                        // 6. 큐에 넣음
                        if (currentType == '*' && nextType == '.') { // 물인 경우
                            map[tmpR][tmpC] = '*';
                            queue.offer(new Node(tmpR, tmpC, '*'));
                        } else if (currentType == '.' || currentType == 'S') { // 고슴도치인 경우
                            if (nextType == '.' && distanceMap[tmpR][tmpC] == 987654321) {
                                distanceMap[tmpR][tmpC] = distanceMap[currentR][currentC] + 1;
                                queue.offer(new Node(tmpR, tmpC, 'S'));
                                map[currentR][currentC] = '.';
                            } else if (nextType == 'D') {
                                distanceMap[tmpR][tmpC] = distanceMap[currentR][currentC] + 1;
                                queue.offer(new Node(tmpR, tmpC, 'D'));
                            }
                        }
                    }
                }
            }
        }
        if (result == -1) System.out.println("KAKTUS"); else System.out.println(result);
    }

    static class Node {
        private int r;
        private int c;
        private char type;

        public Node(int r, int c, char type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }
}
