class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        Long pNum = Long.parseLong(p);
        for (int i = 0; i <= t.length() - p.length(); i++) {
            String tSubstring = t.substring(i, i + p.length());
            if (pNum >= Long.parseLong(tSubstring)) answer++;
        }
        
        return answer;
    }
}