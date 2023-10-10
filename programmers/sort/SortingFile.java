import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        File[] answer = new File[files.length];

        for (int i = 0; i < files.length; i++) {
            answer[i] = new File(files[i]);
        }

        Arrays.sort(answer);
        for (int i = 0; i < files.length; i++) {
            files[i] = answer[i].toString();
        }
        return files;
    }

    class File implements Comparable<File> {
        String head = "";
        String number = "";
        String tail = "";

        public File(String file) {
            int i = 0;
            StringBuilder sb = new StringBuilder();
            for (; i < file.length(); i++) {
                char c = file.charAt(i);
                if (Character.isDigit(c)) {
                    head = sb.toString();
                    sb.setLength(0);
                    break;
                }
                sb.append(c);
            }

            for (; i < file.length(); i++) {
                char c = file.charAt(i);
                if (0 < sb.length() && 99999 < Integer.parseInt(sb.toString())) {
                    i--;
                    sb.deleteCharAt(sb.length() - 1);
                    number = sb.toString();
                    sb.setLength(0);
                    break;
                }
                if (!Character.isDigit(c)) {
                    number = sb.toString();
                    sb.setLength(0);
                    break;
                }
                sb.append(c);
            }
            if (sb.length() != 0) number = sb.toString();

            if (i < file.length()) {
                tail = file.substring(i);
            }
        }

        @Override
        public String toString() {
            return head + number + tail;
        }

        @Override
        public int compareTo(File o) {
            String lowerHead = this.head.toLowerCase();
            String cmpLowerHead = o.head.toLowerCase();

            if (lowerHead.equals(cmpLowerHead)) {
                int numInt = Integer.parseInt(this.number);
                int cmpNumInt = Integer.parseInt(o.number);

                if (numInt == cmpNumInt) {
                    return 0;
                } else if (numInt < cmpNumInt) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (lowerHead.compareTo(cmpLowerHead) < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}