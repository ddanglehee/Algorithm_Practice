class PairNumber {
    public String solution(String X, String Y) {
        String answer = "";
        int[] count = new int[10];
        int[] commonCount = new int[10];

        for (int i = 0; i < X.length(); i++) {
            int number = X.charAt(i) - '0';
            count[number]++;
        }

        for (int i = 0; i < Y.length(); i++) {
            int number = Y.charAt(i) - '0';
            if (count[number] != 0) {
                count[number]--;
                commonCount[number]++;
            }
        }

        for (int i = 9; 0 <= i; i--) {
            answer += Integer.toString(i).repeat(commonCount[i]);
        }

        if (answer.equals("")) {
            return "-1";
        } else {
            if (answer.startsWith("0")) answer = "0";
            return answer;
        }
    }
}