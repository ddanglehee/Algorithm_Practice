import java.util.*;

class DoHomework {

    ArrayList<String> answerList = new ArrayList<>();
    String curSubject;
    int curTime;
    int remainTime;

    public String[] solution(String[][] plans) {
        sortPlans(plans);

        LinkedList<String[]> stack = new LinkedList<>();

        curTime = timeInMinutes(plans[0][1]);
        startSubject(plans[0][0], plans[0][2]);
        int index = 1;
        while (index < plans.length) {
            String[] next = plans[index];
            int nextStartTime = timeInMinutes(next[1]);

            if (curTime + remainTime < nextStartTime) {
                answerList.add(curSubject);
                // 멈춘 과제 이어서 하기
                if (!stack.isEmpty()) {
                    next = stack.remove(stack.size() - 1);
                    curTime = curTime + remainTime;
                    startSubject(next[0], next[1]);
                    continue;
                }
            } else if (curTime + remainTime == nextStartTime) {
                answerList.add(curSubject);
            } else {
                stack.add(new String[] {curSubject, Integer.toString(curTime + remainTime - nextStartTime)});
            }

            // 새로운 거 시작하기
            curTime = nextStartTime;
            startSubject(next[0], next[2]);
            index++;
        }

        answerList.add(curSubject); // 마지막 하던 작업
        while (!stack.isEmpty()) {
            String[] subject = stack.remove(stack.size() - 1);
            answerList.add(subject[0]);
        }

        return answerList.toArray(new String[answerList.size()]);
    }

    void startSubject(String name, String remain) {
        curSubject = name;
        remainTime = Integer.parseInt(remain);
    }

    void sortPlans(String[][] plans) {
        Arrays.sort(plans, (o1, o2) -> {
            int start1 = timeInMinutes(o1[1]);
            int start2 = timeInMinutes(o2[1]);
            return start1- start2;
        });
    }

    int timeInMinutes(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }
}