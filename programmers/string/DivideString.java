class DivideString {

    private int answer = 0;

    public int solution(String s) {
        int start = 0;
        while (true) {
            start = makeSubstring(s, start);
            answer++;
            if (start == s.length()) break;
        }
        return answer;
    }

    private int makeSubstring(String s, int start) {
        char startChar = s.charAt(start);
        int oCount = 0;
        int xCount = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == startChar) {
                oCount++;
            } else {
                xCount++;
            }

            if (oCount == xCount) {
                return i + 1;
            }
        }
        return s.length();
    }
}