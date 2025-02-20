class Solution {
    public int solution(int n, int w, int num) {
        int maxH = (n - 1) / w;
        int targetH = (num - 1) / w;
        int answer = maxH - targetH + 1;

        int targetC;
        if (targetH % 2 == 0) {
            targetC = (num + w - 1) % w;
        } else {
            targetC = w - (num % w);
        }

        if ((maxH % 2 == 0 && ((n + w - 1) % w < targetC && targetC < w))
                || (maxH % 2 == 1 && (0 <= targetC && ((n % w) != 0 && targetC < (w - n % w))))) {
            answer--;
        }

        return answer;
    }
}