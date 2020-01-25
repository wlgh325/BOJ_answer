import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Main {
	static ArrayList<String> list;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int k;
	static final int NUM = 6;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Test case 마다 한 줄 띄기
		// 사전 순 출력
		// 입력의 마지막 줄은 '0'
		
		int check = 0;
		while(true){
			String[] temp = br.readLine().split(" ");
						
			k = Integer.parseInt(temp[0]);
			// 0 입력 시 종료
			if(k == 0)
				break;

			list = new ArrayList<>();
			boolean[] visited = new boolean[k];

			for(int i=1; i<=k; i++){
				list.add(temp[i]);
			}
			
			// 첫 테스트 케이스에서 줄 바꿈 방지
			if(check != 0)
				bw.newLine();
			
			comb(visited, 0, NUM);
			check++;
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	static void comb(boolean[] visited, int start, int r) throws IOException {
		if(r==0){
			solve(visited);
		}

		for(int i=start; i<k; i++){
			visited[i] = true;
			comb(visited, i+1, r-1);
			visited[i] = false;
		}
	}

	static void solve(boolean[] visited) throws IOException {
		int cnt = 0;
		for(int i=0; i<k; i++){
			if(visited[i]){

				// 마지막 수에서 띄어지는 것 방지
				if(cnt != 5)
					bw.write(list.get(i) + " ");
				else
					bw.write(list.get(i));

				cnt++;
			}
			if(cnt == NUM)
				break;
		}
		bw.newLine();
	}
}

