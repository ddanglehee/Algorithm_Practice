import java.util.*;

class MiningMinerals {

    ArrayList<Integer> pickList = new ArrayList<>(); // 0: 다이아, 1: 철, 2: 돌
    int needPickCount;
    boolean[] visited;
    String[] minerals;
    int[] picks;
    int[] diamond = new int[] {1, 5, 25};
    int[] iron = new int[] {1, 1, 5};
    int answer = 1250;

    public int solution(int[] picks, String[] minerals) {
        this.picks = picks; this.minerals = minerals;
        init();

        for (int start = 0; start < pickList.size(); start++) {
            visited[start] = true;
            int[] selectedPicks = new int[needPickCount];
            selectedPicks[0] = pickList.get(start);
            backtracking(1, selectedPicks);
            visited[start] = false;
        }

        return answer;
    }

    void calculateFatigue(int[] selectedPicks) {
        int curIndex = 0;
        int count = 5;
        int fatigue = 0;

        for (String mineral: minerals) {
            if (mineral.equals("diamond")) {
                fatigue += diamond[selectedPicks[curIndex]];
            } else if (mineral.equals("iron")) {
                fatigue += iron[selectedPicks[curIndex]];
            } else {
                fatigue += 1;
            }

            if (--count == 0) {
                curIndex++;
                count = 5;
            }
            if (needPickCount == curIndex) break;
        }

        answer = Math.min(answer, fatigue);
    }

    void backtracking(int count, int[] selectedPicks) {
        if (count == needPickCount) {
            calculateFatigue(selectedPicks);
            return;
        }

        for (int i = 0; i < pickList.size();i++) {
            if (visited[i]) continue;
            selectedPicks[count] = pickList.get(i);
            visited[i] = true;
            backtracking(count + 1, selectedPicks);
            visited[i] = false;
        }
    }

    void init() {
        for (int pick = 0; pick < 3; pick++) {
            for (int i = 0; i < picks[pick]; i++) {
                pickList.add(pick);
            }
        }

        visited = new boolean[pickList.size()];
        needPickCount = Math.min(pickList.size(), (minerals.length - 1) / 5 + 1);
        if (needPickCount < pickList.size()) {
            while (needPickCount != pickList.size()) {
                pickList.remove(pickList.size() - 1);
            }
        }
    }
}