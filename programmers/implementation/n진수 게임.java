class Solution {

    private char[] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m * t; i++) {
            sb.append(convert(i, n));
        }
        String all = sb.toString();

        sb.setLength(0);
        for (int i = p - 1; sb.toString().length() < t; i += m) {
            sb.append(all.charAt(i));
        }

        return sb.toString();
    }

    private String convert(int number, int n) {
        StringBuilder sb = new StringBuilder();

        int q = number / n;
        int r = number % n;

        if (q < n) {
            if (q != 0) {
                sb.append(num[q]);
            }
            sb.append(num[r]);
        } else {
            sb.append(convert(q, n)).append(num[r]);
        }

        return sb.toString();
    }
}