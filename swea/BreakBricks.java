import java.util.*;
import java.io.*;

public class Solution {

    static int answer;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, W, H;
    static int[][] graph = new int[12][15];
    static ArrayList<Integer[]> dupPermutations = new ArrayList<>();
    static int[] dw = {-1, 0, 1, 0};
    static int[] dh = {0, -1, 0, 1};
    static int totalBrick = 0;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();
    static boolean[][] broken = new boolean[12][15];

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            init();

            for (Integer[] dupPermutation: dupPermutations) {
                LinkedList<Integer>[] curGraph = cloneGraph();

                int totalBroken = 0;
                for (Integer w: dupPermutation) { // w: 현재 구슬 쏘는 가로 index
                    if (curGraph[w].size() == 0) break;
                    totalBroken += shoot(curGraph, w, curGraph[w].size() - 1);
                }

                answer = Math.min(answer, totalBrick - totalBroken);
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static LinkedList<Integer>[] cloneGraph() {
        LinkedList<Integer>[] newGraph = new LinkedList[12];
        for (int i = 0; i < 12; i++) {
            newGraph[i] = new LinkedList<>();
        }

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if (graph[i][j] != 0) {
                    newGraph[i].add(graph[i][j]);
                }
            }
        }
//
//		for (LinkedList<Integer> a: newGraph) {
//			for (Integer k: a) {
//				System.out.print(k + " ");
//			}
//			System.out.println();
//		}

        return newGraph;
    }

    public static int shoot(LinkedList<Integer>[] curGraph, int w, int h) {
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                broken[i][j] = false;
            }
        }
        queue.clear();
        queue.offer(new int[] {w, h});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll(); int curW = cur[0]; int curH = cur[1];
            broken[cur[0]][cur[1]] = true;
            int number = curGraph[curW].get(curH);
            if (number == 1) continue;

            for (int d = 0; d < 4; d++) {
                for (int i = 1; i < number; i++) {
                    int nW = curW + dw[d] * i; int nH = curH + dh[d] * i;
                    if (nW < 0 || W <= nW || nH < 0 || curGraph[nW].size() <= nH || broken[nW][nH]) continue;
                    queue.offer(new int[] {nW, nH});
                }
            }
        }

        int brokenCount = 0;
        for (int i = 0; i < W; i++) {
            for (int j = H - 1; 0 <= j; j--) {
                if (broken[i][j]) {
                    brokenCount++;
                    curGraph[i].remove(j);
                }
            }
        }

        return brokenCount;
    }

    static void init() throws IOException {
        totalBrick = 0;
        clearGraph(); dupPermutations.clear();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        duplicatePermutations(new ArrayList<>());
        for (int h = H - 1; 0 <= h; h--) {
            st = new StringTokenizer(br.readLine());

            for (int w = 0; w < W; w++) {
                int brick = Integer.parseInt(st.nextToken());
                if (brick != 0) {
                    totalBrick++;
                    graph[w][h] = brick;
                }
            }
        }

        answer = totalBrick;
    }

    static void duplicatePermutations(ArrayList<Integer> tmp) {
        if (tmp.size() == N) {
            dupPermutations.add(tmp.toArray(new Integer[N]));
            return;
        }
        for (int w = 0; w < W; w++) {
            tmp.add(w);
            duplicatePermutations(tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    static void clearGraph() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 15; j++) {
                graph[i][j] = 0;
            }
        }
    }
}
