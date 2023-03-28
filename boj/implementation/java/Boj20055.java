import java.util.*;
import java.io.*;

class Boj20055 {

    static int n;
    static int k;
    static int step = 1;
    static LinkedList<Boolean> isRobotOn = new LinkedList<>(); // isRobotOn.get(i) : i칸에 로봇이 있는지
    static LinkedList<Integer> beltDurability = new LinkedList<>(); // 벨트 칸의 내구성

    public static void main(String[] args) throws IOException {
        init();

        // while문 한 바퀴 -> 단계
        while(true) {
            // 1. 각 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            rotateBelt();

            // 로봇이 내리는 위치에 도달하면 그 즉시 내린다.
            if (isRobotOn.get(n - 1)) {
                isRobotOn.set(n - 1, false);
            }

            // 2. 가장 먼저 벨트에 올라간 로봇부터,
            for (int i = n - 2; 0 <= i; i--) {
                // 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
                if (isRobotOn.get(i) && canRobotGo(i)) {
                    moveRobot(i, i + 1);
                }
            }

            // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if (beltDurability.get(0) != 0) {
                isRobotOn.set(0, true);
                decreaseDurability(0);
            }

            // 4. 내구도가 0인 칸의 개수가 k개 이상이라면 과정을 종료한다.
            if (k <= 0) {
                break;
            }
            // 그렇지 않다면 1번으로 돌아간다.
            else {
                step++;
            }
        }
        System.out.println(step);
    }

    static void moveRobot(int from, int to) {
        isRobotOn.set(from, false);
        if (to != n - 1) { // 내리는 위치가 아닐 때만 로봇 올림
            isRobotOn.set(to, true);
        }
        decreaseDurability(to);
    }

    static void decreaseDurability(int index) {
        int decreasedDurability = beltDurability.get(index) - 1;
        beltDurability.set(index, decreasedDurability); // 내구성 감소
        if (decreasedDurability == 0) k--;
    }

    static boolean canRobotGo(int i) {
        // 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1이상 남아 있어야 한다.
        return !isRobotOn.get(i + 1) && 0 < beltDurability.get(i + 1);
    }

    static void rotateBelt() {
        int durability = beltDurability.removeLast();
        beltDurability.addFirst(durability);
        boolean isOn = isRobotOn.removeLast();
        isRobotOn.addFirst(isOn);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            int durability = Integer.parseInt(st.nextToken());
            beltDurability.add(durability);
        }
        for (int i = 0; i < 2 * n; i++) {
            isRobotOn.add(false);
        }
    }
}
