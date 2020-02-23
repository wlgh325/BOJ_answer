import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{	
	static int n,m;
	static int fuel[][];
	// 0: ↘
	// 1: ↙
	// 2: ↓
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 전에 움직인 방향으로 움직일 수 없다.
		
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		fuel = new int[n][m];
		dp = new int[3][n][m];

		for(int i=0; i<n; i++) {
			String[] temp2 = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				fuel[i][j] = Integer.parseInt(temp2[j]);
			}
		}
		
		// initialize
		for(int i=0; i<m; i++){
			dp[0][0][i] = fuel[0][i];
			dp[1][0][i] = fuel[0][i];
			dp[2][0][i] = fuel[0][i];
		}

		// 있을 수 없는 값들 큰 값으로 초기화
		for(int i=0; i<n; i++){
			// 맨 왼쪽 끝인데 왼쪽 대각선 방향에서 오는 경우
			dp[0][i][0] = Integer.MAX_VALUE;
			// 맨 오른쪽 끝인데 오른쪽 대각선 방향에서 오는 경우
			dp[2][i][m-1] = Integer.MAX_VALUE;
		}

		for(int i=1; i<n; i++){
			for(int j=0; j<m; j++){
				// 왼쪽 방향, 오른쪽 방향 모두에서 올 수 있는 경우
				if(isValidPosition(j-1) && isValidPosition(j+1)){
					dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + fuel[i][j];
					dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + fuel[i][j];
					dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + fuel[i][j];
				}
				else if(!isValidPosition(j-1) && isValidPosition(j+1)){
					// 오른쪽 끝에 있는 경우
					dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + fuel[i][j];
					dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + fuel[i][j];
				}
				else if(isValidPosition(j-1) && !isValidPosition(j+1) ){
					// 왼쪽 끝에 있는 경우
					dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + fuel[i][j];
					dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + fuel[i][j];
				}
			}
		}

		int min = Integer.MAX_VALUE;
		// 마지막 행에서 최소값 찾기
		for(int i=0; i<m; i++){
			for(int j=0; j<3; j++){
				min = min > dp[j][n-1][i] ? dp[j][n-1][i] : min;
			}
		}
		System.out.println(min);
		br.close();
	}
	
	
	public static boolean isValidPosition(int y) {
		if(y < 0 || y >= m)
			return false;
		return true;
	}
}