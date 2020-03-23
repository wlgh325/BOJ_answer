import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		int[] dp = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++){
			dp[i] = 1;
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i=n-1; i>=0; i--){
			for(int j=n-1; j>i; j--){
				if(arr[i] > arr[j]){
					if(dp[i] < dp[j] + 1)
						dp[i] = dp[j] + 1;
				}
			}
		}
		int max = 0;
		for(int i=0; i<n; i++)
			max = max < dp[i] ? dp[i] : max;
		System.out.println(max);
	}
}