class Solution {
    boolean solution(String s) {
        int pCount = 0;
        int yCount = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'p' || c == 'P') {
                pCount++;
            } else if (c == 'y' || c == 'Y') {
                yCount++;
            }
        }
        
        return pCount == yCount;
    }
}