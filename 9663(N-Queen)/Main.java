import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main{	
	static int[] cols;
	static int n;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		

		n = Integer.parseInt(br.readLine());
		cols = new int[n];
		cnt = 0;
		// 퀸 N 개를 서로 공격할 수 없게 놓는 경우의 수 구하기
		// 퀸은 모든 방향으로 갈 수 있음
		backtracking(0);
		bw.write(cnt + "");
		bw.flush();
		br.close();
		bw.close();
	}
	
	static void backtracking(int row) {
		if(row == n) {
			cnt++;
			return;
		}
		
		for(int c=0; c<n; c++) {
			if(isRightPosition(row, c)) {
				cols[row] = c;
				backtracking(row+1);
			}
		}
	}
	
	static boolean isRightPosition(int x, int y) {
		boolean flag = true;
		for(int i=0; i<x; i++){
			if(cols[i] == y || Math.abs(x-i) == Math.abs(y - cols[i]))
				return false;
		}
		return flag;
	}
}