import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main{
	static int[] arr;
	static int n, m=0;
	static int max;
	static boolean[] visited;

	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);

		String[] temp2 = br.readLine().split(" ");
		
		arr = new int[n];
		visited = new boolean[n];
		for(int i=0; i<n; i++){
			arr[i] = Integer.parseInt(temp2[i]);
		}
		
		comb(0,3);
		bw.write("" + max);
		bw.flush();
		br.close();
		bw.close();
	}

	public static void comb(int start, int r){
		if(r==0){
			int sum = 0;
			for(int i=0; i<n; i++){
				if(visited[i])
					sum += arr[i];
			}

			// 계산해서 m을 넘지 않는지 확인
			// m을 넘지 않고 max보다 크면 값 바꿔줌
			if(sum <= m)
				if(sum > max)
					max = sum;
			return;
		}

		for(int i=start; i<n; i++){
			visited[i] = true;
			comb(i+1, r-1);
			visited[i] = false;
		}
	}
}