import java.util.*;
import java.io.*;

public class TaskProcess {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayDeque<Integer>[] taskQueues;
    static ArrayDeque<Integer>[] employees;

    public static void main(String args[]) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // 1. 초기화
        employees = new ArrayDeque[(int)Math.pow(2, H + 1)];
        for (int i = 1; i < Math.pow(2, H + 1); i++) {
            employees[i] = new ArrayDeque<>();
        }
        taskQueues = new ArrayDeque[(int)Math.pow(2, H)];
        for (int i = 0; i < Math.pow(2, H); i++) {
            taskQueues[i] = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                taskQueues[i].offer(Integer.parseInt(st.nextToken()));
            }
        }

        // 2. 작업 시작
        int result = 0;
        for (int day = 1; day <= R; day++) {
            for (int i = 1; i < Math.pow(2, H + 1); i++) {
                // 말단 사원이면 taskQueues에서 일 가져옴
                if (Math.pow(2, H) <= i && i < Math.pow(2, H + 1)) {
                    if (taskQueues[i - (int)Math.pow(2, H)].isEmpty()) break;
                    employees[i].offer(taskQueues[i - (int)Math.pow(2, H)].poll());
                }
                // 말단 사원이 아니면 부하 직원으로부터 일 가져옴
                else {
                    if (day % 2 != 0) {
                        if (!employees[i * 2].isEmpty()) employees[i].offer(employees[i * 2].poll());
                    } else {
                        if (!employees[i * 2 + 1].isEmpty()) employees[i].offer(employees[i * 2 + 1].poll());
                    }
                }
            }
            if (!employees[1].isEmpty()) {
                result += employees[1].poll();
            }
        }

        System.out.println(result);
    }
}
