import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

class Main{	
	static int n,m;
	static int arr[][];
	static int min = Integer.MAX_VALUE;
	// -1: 왼쪽 아래
	// 0: 아래
	// 1: 오른쪽 아래 방향
	static int[] ydir = {-1, 0, 1};
	static int[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 전에 움직인 방향으로 움직일 수 없다.
		
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		arr = new int[n][m];
		
		for(int i=0; i<n; i++) {
			String[] temp2 = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(temp2[j]);
			}
		}
		
		for(int i=0; i<m; i++) {
			visited = new int[n];
			visited[0] = i;
			dfs(1, i, -1);
		}
		
		bw.write("" + min);
		bw.flush();
		bw.close();
		br.close();
	}
	
	// 완탐해야함
	public static void dfs(int depth, int y, int dir) {
		if(depth == n) {
			int sum = arr[0][visited[0]];
			for(int i=1; i<n; i++) {
				sum += arr[i][visited[i]];
			}
			
			min = min > sum ? sum : min;
			return;
		}
		
		// 전에 이동했던 방향인지도 고려!
		for(int i=0; i<3; i++) {
			int dy = y + ydir[i];
			if(isValidPosition(dy) && dir != i) {
				visited[depth] = dy;
				dfs(depth+1, dy, i);
			}
		}
	}
	
	public static boolean isValidPosition(int y) {
		if(y < 0 || y >= m)
			return false;
		return true;
	}
}