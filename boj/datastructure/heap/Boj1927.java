package Day3;

import java.io.*;
import java.util.*;

public class Boj1927 {

    static int n;
    static List<Integer> minHeap = new ArrayList<>();
    static StringBuilder st = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        minHeap.add(-1);
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                remove();
            } else {
                insert(input);
            }
        }

        System.out.println(st);
    }

    // last에 넣고 root까지 올라가면서 비교
    static void insert(int input) {
        minHeap.add(input);

        int currentIndex = minHeap.size() - 1;
        while (true) {
            int parentIndex = currentIndex / 2;

            // parent가 더 작거나 같으면 멈춤
            if (parentIndex == 0 || minHeap.get(parentIndex) <= minHeap.get(currentIndex)) {
                break;
            }
            // current가 더 작으면 swap
            else {
                swap(currentIndex, parentIndex);
                currentIndex = parentIndex;
            }
        }
    }

    static void remove() {
        // heap에 아무것도 안들어있을 경우
        if (minHeap.size() == 1) {
            st.append(0).append("\n");
        }
        // heap의 top을 제거
        else {
            swap(1, minHeap.size() - 1);
            int num = minHeap.remove(minHeap.size() - 1);

            int currentIndex = 1;
            while (true) {
                int leftIndex = currentIndex * 2;
                int rightIndex = currentIndex * 2 + 1;

                if (minHeap.size() <= leftIndex) break;

                int currentValue = minHeap.get(currentIndex);
                int minValue = minHeap.get(leftIndex);
                int minIndex = leftIndex;

                if (rightIndex < minHeap.size() && minHeap.get(rightIndex) < minValue) {
                    minValue = minHeap.get(rightIndex);
                    minIndex = rightIndex;
                }

                if (minValue < currentValue) {
                    swap(currentIndex, minIndex);
                    currentIndex = minIndex;
                } else {
                    break;
                }
            }
            st.append(num).append("\n");
        }
    }

    static void swap(int idx1, int idx2) {
        int tmp = minHeap.get(idx1);
        minHeap.set(idx1, minHeap.get(idx2));
        minHeap.set(idx2, tmp);
    }
}
