import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Pair implements Comparable<Pair>{
	int l;
	int r;

	Pair(int l, int r){
		this.l = l;
		this.r = r;
	}

	@Override
	public int compareTo(Pair a){
		if(this.l < a.l)
			return -1;
		else if(this.l > a.l)
			return 1;
		return 0;
	}
}

class Main {
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		Pair[] arr = new Pair[n];
		for(int i=0; i<n; i++){
			String[] temp = br.readLine().split(" ");
			arr[i] = new Pair(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}

		Arrays.sort(arr);

		int[] dp = new int[n];
		
		for(int i=0; i<n; i++){
			if(dp[i] == 0) dp[i] = 1;	// 초기화
			for(int j=0; j<i; j++){
				// 비교하는 수의 값이 더 크다면 dp 값을 비교해 1 더 크다면 dp 업데이트
				if(arr[i].r > arr[j].r){
					if(dp[i] < dp[j] + 1)
						dp[i] = dp[j] + 1;
				}
			}
		}
		
		// 최대 LIS 길이 찾기
		int max = 0;
		for(int i=0; i<n; i++)
			max = max < dp[i] ? dp[i] : max;
		System.out.println(n-max);
	}
}