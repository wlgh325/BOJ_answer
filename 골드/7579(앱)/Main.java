import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Main{
	static int N, M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] memories = new int[N];
		int[] costs = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++)
			memories[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++)
			costs[i] = Integer.parseInt(st.nextToken());

		// dp 배열 초기화
		int[] dp = new int[10001];
		Arrays.fill(dp, -1);
		
		// dp[i]: i cost를 써서 확보할 수 있는 최대 메모리
		for(int i=0; i<N; i++){
			int cost = costs[i];
			// 뒤에서 부터 확인해야 겹치지 않고 값을 update 할 수 있다.
			for(int j=10000; j>=cost; j--){
				if(dp[j-cost] != -1){
					// 이전 j-cost 까지의 최대 값에 현재 mem을 더하는게 더 크다면 update
					if(dp[j-cost] + memories[i] > dp[j])
						dp[j] = dp[j-cost] + memories[i];
				}
			}
			// 메모리 업데이트가 안되어있는 경우 업데이트
			// 단 메모리가 더 큰 경우에만 업데이트 가능
			if(dp[cost] < memories[i]) dp[cost] = memories[i];
		}

		for(int i=0; i<10001; i++){
			if(dp[i] >= M){
				System.out.println(i);
				break;
			}
		}
	}


}