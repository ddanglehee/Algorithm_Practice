class PCCP2_3 {
    public int solution(int[] menu, int[] orders, int k) {
        int arrLength = orders.length * menu.length * k + 1;
        int[] arr = new int[arrLength];

        int e = menu[orders[0]];
        arr[0] = 1; arr[e] = -1;
        for (int i = 1; i < orders.length; i++) {
            arr[k * i]++;
            arr[Math.max(e, k * i) + menu[orders[i]]]--;
            e = Math.max(e, k * i) + menu[orders[i]];
        }

        int answer = 1;
        for (int i = 1; i < arrLength; i++) {
            arr[i] += arr[i - 1];
            answer = Math.max(answer, arr[i]);
        }

        return answer;
    }
}
