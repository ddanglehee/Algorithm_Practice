import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int bs = (int) Math.sqrt(N);
		int[] bucket = new int[N / bs + 1]; 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			bucket[i / bs] += arr[i];
		}
		
		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			int result = 0;
			for (int i = a; i <= b; ) {
				if (i % bs == 0 && i + bs - 1 <= b) {
					result += bucket[i / bs];
					i += bs;
				} else {
					result += arr[i++];
				}
			} 
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}
