import java.util.*;
import java.io.*;

public class SantaPresentFactory2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static private int n, m;
    static private Belt[] belts;
    static private Gift[] gifts;
    static private StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int q = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 100) {
                cmd100(st);
            } else if (cmd == 200) {
                cmd200(st);
            } else if (cmd == 300) {
                cmd300(st);
            } else if (cmd == 400) {
                cmd400(st);
            } else if (cmd == 500) {
                cmd500(st);
            } else {
                cmd600(st);
            }
            System.out.println(cmd);
            for (int j=1; j<=n; j++) {
                System.out.println((belts[j].front == null ? null : belts[j].front.num) + " " + (belts[j].rear == null ? null : belts[j].rear.num) + " " + belts[j].count);
            }
            System.out.println("---");
        }

        System.out.print(sb);
    }

    static void cmd100(StringTokenizer st) {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        belts = new Belt[n + 1];
        gifts = new Gift[m + 1];
        for (int i = 1; i <= n; i++) {
            belts[i] = new Belt();
        }
        for (int i = 1; i <= m; i++) {
            Gift gift = new Gift(i);
            gifts[i] = gift;
            Belt belt = belts[Integer.parseInt(st.nextToken())];

            if (belt.count == 0) {
                belt.front = gift;
            } else {
                gift.pre = belt.rear;
                belt.rear.post = gift;
            }
            belt.rear = gift;
            belt.count++;
        }
    }

    static void cmd200(StringTokenizer st) {
        int m_src = Integer.parseInt(st.nextToken());
        Belt srcBelt = belts[m_src];
        int m_dst = Integer.parseInt(st.nextToken());
        Belt dstBelt = belts[m_dst];

        if (srcBelt.count == 0) {
            sb.append(dstBelt.count).append("\n");
            return;
        }

        if (dstBelt.count == 0) {
            dstBelt.front = srcBelt.front;
            dstBelt.rear = srcBelt.rear;
        } else {
            srcBelt.rear.post = dstBelt.front;
            dstBelt.front.pre = srcBelt.rear;
            dstBelt.front = srcBelt.front;
        }
        dstBelt.count += srcBelt.count;
        srcBelt.front = null;
        srcBelt.rear = null;
        srcBelt.count = 0;
        sb.append(dstBelt.count).append("\n");
    }

    static void cmd300(StringTokenizer st) {
        int m_src = Integer.parseInt(st.nextToken());
        Belt srcBelt = belts[m_src];
        int m_dst = Integer.parseInt(st.nextToken());
        Belt dstBelt = belts[m_dst];


        if (srcBelt.count == 0 && dstBelt.count == 0) {
            sb.append(0).append("\n");
            return;
        }

        if (srcBelt.count == 0) {
            moveOne(dstBelt, srcBelt);
        } else if (dstBelt.count == 0) {
            moveOne(srcBelt, dstBelt);
        } else {
            Gift srcFront = srcBelt.front;
            Gift srcPost = srcFront.post;
            Gift dstFront = dstBelt.front;
            Gift dstPost = dstFront.post;

            srcFront.post = dstPost;
            if (dstPost != null) dstPost.pre = srcFront;
            dstFront.post = srcPost;
            if (srcPost != null) srcPost.pre = dstFront;

            srcBelt.front = dstFront;
            dstBelt.front = srcFront;
            if (srcBelt.count == 1) srcBelt.rear = srcBelt.front;
            if (dstBelt.count == 1) dstBelt.rear = dstBelt.front;
        }
        sb.append(dstBelt.count).append("\n");
    }

    static void moveOne(Belt from, Belt to) {
        to.front = from.front;
        if (from.front.post != null) from.front.post.pre = null;
        from.front = from.front.post;
        to.front.post = null;
        to.rear = to.front;
        to.count++;
        from.count--;
    }

    static void cmd400(StringTokenizer st) {
        int m_src = Integer.parseInt(st.nextToken());
        Belt srcBelt = belts[m_src];



        int moveCount = srcBelt.count / 2;
        Gift moveFront = srcBelt.front;
        Gift moveRear = srcBelt.front;
        for (int i = 1; i < moveCount; i++) {
            moveRear = moveRear.post;
        }
        int m_dst = Integer.parseInt(st.nextToken());
        Belt dstBelt = belts[m_dst];
        if (srcBelt.count <= 1) {
            sb.append(dstBelt.count).append("\n");
            return;
        }

        moveRear.post.pre = null;
        srcBelt.front = moveRear.post;

        if (dstBelt.count == 0) {
            dstBelt.rear = moveRear;
            moveRear.post = null;
        } else {
            dstBelt.front.pre = moveRear;
            moveRear.post = dstBelt.front;
        }
        dstBelt.front = moveFront;

        dstBelt.count += moveCount;
        srcBelt.count -= moveCount;
        sb.append(dstBelt.count).append("\n");
    }

    static void cmd500(StringTokenizer st) {
        int p_num = Integer.parseInt(st.nextToken());
        Gift gift = gifts[p_num];
        int a = gift.pre == null ? -1 : gift.pre.num;
        int b = gift.post == null ? -1 : gift.post.num;

        sb.append(a + 2 * b).append("\n");
    }

    static void cmd600(StringTokenizer st) {
        int b_num = Integer.parseInt(st.nextToken());
        Belt belt = belts[b_num];
        int a = belt.front == null ? -1 : belt.front.num;
        int b = belt.rear == null ? -1 : belt.rear.num;
        int c = belt.count;

        sb.append(a + 2 * b + 3 * c).append("\n");
    }

    static public class Belt {
        Gift front = null;
        Gift rear = null;
        int count = 0;
    }

    static public class Gift {
        int num;
        Gift pre = null;
        Gift post = null;

        public Gift(int num) {
            this.num = num;
        }
    }
}