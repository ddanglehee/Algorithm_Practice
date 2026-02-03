import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		LinkedList<Character> list = new LinkedList<>();
		ListIterator<Character> iter = list.listIterator();
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			list.clear();
			iter = list.listIterator();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				
				switch(c) {
					case '<':
						if (iter.hasPrevious()) iter.previous();
						break;
					case '>':
						if (iter.hasNext()) iter.next();
						break;
					case '-':
						if (!iter.hasPrevious()) break;
						iter.previous();
						iter.remove();
						break;
					default:
						iter.add(c);
				}

			}
			for (Character c : list) {
				sb.append(c);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}
