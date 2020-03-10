import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new ArrayDeque<>();

		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine(), " ");
			switch(st.nextToken()){
				case "push_front":
					deque.addFirst(Integer.parseInt(st.nextToken()));
					break;
				case "push_back":
					deque.addLast(Integer.parseInt(st.nextToken()));
					break;
				case "pop_front":
					if(!deque.isEmpty())
						System.out.println(deque.pollFirst());
					else
						System.out.println(-1);
					break;
				case "pop_back":
					if(!deque.isEmpty())
						System.out.println(deque.pollLast());
					else
						System.out.println(-1);
					break;
				case "size":
					System.out.println(deque.size());
					break;
				case "empty":
					if(!deque.isEmpty())
						System.out.println(0);
					else
						System.out.println(1);
					break;
				case "front":
					if(!deque.isEmpty())
						System.out.println(deque.getFirst());
					else
						System.out.println(-1);
					break;
				case "back":
					if(!deque.isEmpty())
						System.out.println(deque.getLast());
					else
						System.out.println(-1);
					break;
			}
		}
	}
}