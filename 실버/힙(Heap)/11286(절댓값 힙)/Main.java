import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer a, Integer b){
				if(Math.abs(a) > Math.abs(b))
					return 1;
				else if(Math.abs(a) < Math.abs(b))
					return -1;
				else{
					if(a > b)
						return 1;
					else if(a < b)
						return -1;
					else
						return 0;
				}
			}
		});

		for(int i=0; i<num; i++){
			int input = Integer.parseInt(br.readLine());
			if(input == 0){
				if(!pq.isEmpty())
					System.out.println(pq.poll());
				else
					System.out.println(0);
			}
			else{
				pq.offer(input);
			}
		}
	}
}