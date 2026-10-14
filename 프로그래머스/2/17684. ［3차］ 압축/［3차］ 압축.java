import java.util.*; 

class Solution {
    
    public int[] solution(String msg) {
        List<Integer> answerList = new ArrayList<>();
        
        Map<String, Integer> dic = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
           dic.put(Character.toString(c), c - 'A' + 1);
        }
        
        int s = 0;
        int dicIndex = 27;
        while (s < msg.length()) {
            int curDicIndex = 0, e;
            String substring = "";
            
            for (e = s; e < msg.length(); e++) {
                substring = msg.substring(s, e + 1);
                
                if (dic.get(substring) == null) {
                    break;
                } else {
                    curDicIndex = dic.get(substring);
                }
            }
            
            dic.put(substring, dicIndex++);
            answerList.add(curDicIndex);
            s = e;
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}