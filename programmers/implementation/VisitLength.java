import java.util.*;

class VisitLength {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static HashMap<Character, Integer> directionMap = new HashMap<>(){{
        put('U', 0);
        put('D', 1);
        put('R', 2);
        put('L', 3);
    }};
    static HashSet<Road> visitedSet = new HashSet<>();
    static int curX = 0;
    static int curY = 0;

    public int solution(String dirs) {

        for (int i = 0; i < dirs.length(); i++) {
            int curDirection = directionMap.get(dirs.charAt(i));
            int tmpX = curX + dx[curDirection];
            int tmpY = curY + dy[curDirection];

            if (-5 <= tmpX && tmpX <= 5 && -5 <= tmpY && tmpY <= 5) {
                visitedSet.add(new Road(curX, curY, tmpX, tmpY));
                visitedSet.add(new Road(tmpX, tmpY, curX, curY));
                curX = tmpX;
                curY = tmpY;
            }
        }


        return visitedSet.size() / 2;
    }
}

class Road {
    int x1, y1;
    int x2, y2;

    Road(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Road)) return false;

        Road road = (Road) o;
        return this.x1 == x1 && this.y1 == y1 && this.x2 == x2 && this.y2 == y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }
}