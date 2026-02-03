import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		int[] tree = new int[N + 1];
		for (int i = 1; i <= Q; i++) {
			int k = Integer.parseInt(br.readLine());
			
			int tmp = k;
			int result = 0;
			while (0 < tmp) {
				if (tree[tmp] != 0) {
					result = tmp;
				}
				tmp /= 2;
			}
			
			if (result == 0) {
				tree[k] = i;
			}
			
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}
