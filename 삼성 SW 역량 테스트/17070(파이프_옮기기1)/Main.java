import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int N;
	static int[][] home;
	// 0: →
	// 1: ↓
	// 2: ↘
	static int cnt;
	static int[][] xdir = {{0,100,0}, {100,1,1}, {1,1,1}};
	static int[][] ydir = {{1,100,1}, {100,0,0}, {1,1,1}};
	static int[][] xdir2 = {{0,100,1}, {100,1,1}, {0,1,1}};
	static int[][] ydir2 = {{1,100,1}, {100,0,1}, {1,0,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		N = Integer.parseInt(br.readLine());
		home = new int[N][N];
		for(int i=0; i<N; i++){
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<N; j++){
				home[i][j] = Integer.parseInt(temp[j]);
			}
		}

		cnt = 0;
		dfs(0,0,0,1,0);
		System.out.println(cnt);
		br.close();
	}

	public static void dfs(int x, int y, int x2, int y2, int dir){
		if(x2 == N-1 && y2 == N-1){
			cnt++;
			return;
		}

		for(int i=0; i<3; i++){
			int dx1 = x + xdir[dir][i];
			int dy1 = y + ydir[dir][i];
			int dx2 = x2 + xdir2[dir][i];
			int dy2 = y2 + ydir2[dir][i];

			if(isValidation(dx1, dy1, dx2, dy2, i)){
				dfs(dx1, dy1, dx2, dy2, i);
			}
		}
	}

	public static boolean isValidation(int x, int y, int x2, int y2, int dir){
		if(x >= N || x2 >= N || y >= N || y2 >= N) return false;
		if(dir == 0 || dir == 1){
			if(home[x2][y2] == 1)
				return false;
		}
		else if(dir == 2){
			if(home[x2][y2] == 1 || home[x2][y2-1] == 1 || home[x2-1][y2] == 1)
				return false;
		}

		return true;
	}
}