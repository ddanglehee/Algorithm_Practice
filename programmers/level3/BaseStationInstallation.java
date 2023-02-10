class BaseStationInstallation {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 1;
        int end;
        for (int station: stations) {
            end = station - w;
            if (start < end) {
                answer += (end - start - 1) / (2 * w + 1) + 1;
            }
            start = station + w + 1;

            if (n < start) break;
        }
        if (start <= n) {
            answer += (n - start) / (2 * w + 1) + 1;
        }

        return answer;
    }
}
