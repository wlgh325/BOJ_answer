import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Collections;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
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