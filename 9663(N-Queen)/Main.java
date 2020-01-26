import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main{	
	static int[][] map;
	static int n;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		cnt = 0;
		// 퀸 N 개를 서로 공격할 수 없게 놓는 경우의 수 구하기
		// 퀸은 모든 방향으로 갈 수 있음
		backtracking(0);
		bw.write(cnt + "");
		bw.flush();
		br.close();
		bw.close();
	}
	
	static void backtracking(int num) {
		if(num == n) {
			cnt++;
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(isRightPosition(num, i)) {
				map[num][i] = 1;
				backtracking(num+1);
				map[num][i] = 0;
			}
		}
	}
	
	static boolean isRightPosition(int x, int y) {
		boolean flag = true;
		
		
		// 왼쪽, 오른쪽 check
		for(int i=0; i<n; i++) {
			if(map[x][i] == 1)
				return false;
		}
		
		// 위 아래 check
		for(int i=0; i<n; i++) {
			if(map[i][y] == 1)
				return false;
		}
		
		int tempX = x;
		int tempY = y;
		// 왼쪽위 check
		while(true) {
			if(tempX > 0 && tempY > 0) {
				if(map[--tempX][--tempY] == 1)
					return false;	
			}
			else
				break;
			
		}
		
		tempX = x;
		tempY = y;
		// 오른쪽 아래
		while(true) {
			if(tempX < n -1 && tempY < n - 1) {
				if(map[++tempX][++tempY] == 1)
					return false;
			}
			else
				break;
		}
		
		// 오른쪽 위
		tempX = x;
		tempY = y;
		while(true) {
			if(tempX > 0 && tempY < n -1) {
				if(map[--tempX][++tempY] == 1)
					return false;
			}
			else
				break;
		}
		
		// 왼쪽 아래
		tempX = x;
		tempY = y;
		while(true) {
			if(tempX < n-1 && tempY > 0) {
				if(map[++tempX][--tempY] == 1)
					return false;
			}
			else
				break;
		}
		
		return flag;
	}
}