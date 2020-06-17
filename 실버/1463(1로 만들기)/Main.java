import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int min;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		dfs(N, 0);
		System.out.println(min);
	}
	
	public static void dfs(int N, int cnt){
		if(N == 1){
			min = min > cnt ? cnt : min;
			return;
		}

		// 가지치기
		// 이미 cnt가 min보다 크거나 같은 경우 더 진행할 필요가 없음
		if(cnt >= min) return;

		// 2로 나누어 떨어지는 경우 2로 나눠보기
		if(N % 2 == 0)
			dfs(N/2, cnt+1);
		// 3으로 나누어 떨어지는 경우 3으로 나눠보기
		if(N % 3 == 0)
			dfs(N/3, cnt+1);
		dfs(N-1, cnt+1);	// 1 빼보기
	}
}