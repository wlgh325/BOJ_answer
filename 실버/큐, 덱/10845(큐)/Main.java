import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<>();
		
		int value = 0;
		for(int i=0; i<n; i++) {
			String[] temp = br.readLine().split(" ");
			if(temp.length == 1) {
				// front, back, size, empty, pop 명령
				switch(temp[0]){
					case "front":
						if(!q.isEmpty())
							System.out.println(q.peek());
						else
							System.out.println(-1);
						break;
					case "back":
						if(!q.isEmpty())
							System.out.println(value);
						else
							System.out.println(-1);
						break;
					case "size":
						System.out.println(q.size());
						break;
					case "empty":
						if(!q.isEmpty())
							System.out.println(0);
						else
							System.out.println(1);
						break;
				case "pop":
					if(!q.isEmpty())
						System.out.println(q.poll());
					else
						System.out.println(-1);
					break;
				}
			}
			else {
				// push 명령
				value = Integer.parseInt(temp[1]);
				q.offer(value);
			}
		}
	}
}