import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[n+1];

		int idx = k;
		int flag = n;
		System.out.print("<");
		while(flag > 1){
			int cnt = 0;	// 몇 번째 사람인지
			System.out.print(idx + ", ");
			
			// 사람 제거
			visited[idx] = true;

			// 남은 사람수
			flag--;
			while(true){
				// 원으로 되어있으므로 n을 넘어가는 경우 처리해준다.
				// 0인 경우는 index는 1부터 시작이므로 1로 해준다.
				idx = ((idx+1) % (n+1)) == 0 ? 1 : (idx+1) % (n+1);

				// 죽지 않은 사람 체크
				if(!visited[idx])
					cnt++;
				// k번째 사람을 찾은 경우
				if(cnt == k)
					break;
			}
		}
		
		System.out.println(idx + ">");		
	}
}