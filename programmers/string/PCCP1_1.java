import java.util.PriorityQueue;

class PCCP1_1 {
    public String solution(String input_string) {
        boolean[] isUsedAlpha = new boolean[26];
        boolean[] isAnswer = new boolean[26];
        PriorityQueue<Character> answerPQ = new PriorityQueue<>();

        isUsedAlpha[input_string.charAt(0) - 'a'] = true;
        for (int i = 1; i < input_string.length(); i++) {
            char cur = input_string.charAt(i);
            if (isUsedAlpha[cur - 'a'] && input_string.charAt(i - 1) != cur && !isAnswer[cur - 'a']) {
                isAnswer[cur - 'a'] = true;
                answerPQ.add(cur);
            }
            isUsedAlpha[cur - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        if (answerPQ.isEmpty()) {
            sb.append('N');
        }
        while (!answerPQ.isEmpty()) {
            sb.append(answerPQ.poll());
        }

        return sb.toString();
    }
}