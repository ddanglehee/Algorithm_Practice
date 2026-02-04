import java.util.*;
import java.io.*;

public class Main {
	
	static ArrayList<Integer> primes = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		setPrimes(N);
		
		int r, l, sum, answer;
		r = l = sum = answer = 0;
		
		while (true) {
			if (N <= sum) {
				if (sum == N) answer++;
				sum -= primes.get(l++);
			} else if (r == primes.size()) {
				break;
			} else {
				sum += primes.get(r++);
			}
		}
		
		System.out.print(answer);
	}
	
	private static void setPrimes(int N) {
		boolean[] isPrime = new boolean[N+1];
		
		for (int i = 2; i <= N; i++) {
			isPrime[i] = true;
		}
		
		for (int i = 2; i <= N; i++) {
			if (!isPrime[i]) continue;
			for (int j = i; 0 < i * j && i * j <= N; j++) {
				isPrime[i * j] = false;
			}
			primes.add(i);
		}
	}
}
