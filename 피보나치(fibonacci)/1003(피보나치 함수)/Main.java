import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] dp = new int[41][2];
		int max = 1;
		dp[0][0] = 1;
		dp[0][1] = 0;

		dp[1][0] = 0;
		dp[1][1] = 1;

		for(int tc=1; tc<=T; tc++){
			int n = Integer.parseInt(br.readLine());
			if(n == 0){
				System.out.println(dp[0][0] + " " + dp[0][1]);
				continue;
			}
			else if(n == 1){
				System.out.println(dp[1][0] + " " + dp[1][1]);
				continue;
			}
			else{
				if(dp[n][0] != 0){
					System.out.println(dp[n][0] + " " + dp[n][1]);
				}
				else{
					// 이미 max까지는 dp 값이 구해져 있는 상태
					for(int i=max+1; i<=n; i++){
						dp[i][0] = dp[i-2][0] + dp[i-1][0];
						dp[i][1] = dp[i-2][1] + dp[i-1][1];
					}
					System.out.println(dp[n][0] + " " + dp[n][1]);
					max = n;
				}
			}
		}
	}
}