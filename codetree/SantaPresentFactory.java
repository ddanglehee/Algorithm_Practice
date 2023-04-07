import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static LinkedList<Integer>[] belt;
    static int[] present;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());

        for (int q = 1; q <= Q; q++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 100) {
                command1();
            } else if (command == 200) {
                command2();
            } else if (command == 300) {
                command3();
            } else if (command == 400) {
                command4();
            } else if (command == 500) {
                command5();
            } else {
                command6();
            }
        }

        System.out.print(sb);
    }

    static void command6() {
        int bNum = Integer.parseInt(st.nextToken());
        int a = -1; int b = -1; int c = belt[bNum].size();
        if (c != 0) {
            a = belt[bNum].getFirst();
            b = belt[bNum].getLast();
        }
        sb.append(a + 2 * b + 3 * c).append("\n");
    }

    static void command5() {
        int pNum = Integer.parseInt(st.nextToken());
        int a = -1; int b = -1;
        LinkedList<Integer> curBelt = belt[present[pNum]];

        if (curBelt.size() == 1) {
            sb.append(-3).append("\n");
            return;
        }

        Object[] beltArray = curBelt.toArray();
        for (int i = 0; i < curBelt.size(); i++) {
            if (Integer.parseInt(beltArray[i].toString()) == pNum) {
                if (i != 0) a = Integer.parseInt(beltArray[i - 1].toString());
                if (i != curBelt.size() - 1) b = Integer.parseInt(beltArray[i + 1].toString());
                break;
            }
        }

        sb.append(a + 2 * b).append("\n");
    }

    static void command4() {
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());
        int srcSize = belt[src].size();

        if (2 <= srcSize) {
            ArrayList<Integer> tmpList = new ArrayList<>();
            for (int i = 0; i < srcSize / 2; i++) {
                tmpList.add(belt[src].removeFirst());
            }
            for (int i = 0; i < srcSize / 2; i++) {
                int pNum = tmpList.remove(tmpList.size() - 1);
                present[pNum] = dst;
                belt[dst].addFirst(pNum);
            }
        }

        sb.append(belt[dst].size()).append("\n");
    }

    static void command3() {
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        if (belt[src].size() != 0 && belt[dst].size() == 0) {
            int pNum = belt[src].removeFirst();
            present[pNum] = dst;
            belt[dst].addFirst(pNum);
        } else if (belt[src].size() == 0 && belt[dst].size() != 0) {
            int pNum = belt[dst].removeFirst();
            present[pNum] = src;
            belt[src].addFirst(pNum);
        } else if (belt[src].size() != 0 && belt[dst].size() != 0) {
            int fromSrc = belt[src].removeFirst();
            int fromDst = belt[dst].removeFirst();
            present[fromSrc] = dst;
            present[fromDst] = src;
            belt[src].addFirst(fromDst);
            belt[dst].addFirst(fromSrc);
        }

        sb.append(belt[dst].size()).append("\n");
    }

    static void command2() {
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        if (belt[src].size() != 0) {
            int srcSize = belt[src].size();

            for (int i = 0; i < srcSize; i++) {
                int pNum = belt[src].removeLast();
                present[pNum] = dst;
                belt[dst].addFirst(pNum);
            }
        }

        sb.append(belt[dst].size()).append("\n");
    }

    static void command1() {
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        belt = new LinkedList[n + 1];
        present = new int[m + 1];

        for (int i = 0; i <= n; i++) {
            belt[i] = new LinkedList<>();
        }

        for (int i = 1; i <= m; i++) {
            int bNum = Integer.parseInt(st.nextToken());
            belt[bNum].add(i);
            present[i] = bNum;
        }
    }
}