import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

class Main{	
	static int n;
	static int[][] arr;
	static int rr;
	static ArrayList<Integer> result_list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		result_list = new ArrayList<>();
		
		visited = new boolean[n];
		
		// (i,j, stat) -> i와 j와 같은 팀일때 능력치 stat만큼 가짐
		for(int i=0; i<n; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		comb(0, n, n/2);
		
		bw.write(Collections.min(result_list) + "");
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void comb(int start, int n, int r) {
		if(r==0) {
			solve();
			return;
		}
		for(int i=start; i<n; i++) {
			visited[i] = true;
			comb(i+1, n, r-1);
			visited[i] = false;
		}
	}

	public static void solve() {
		int start = 0;
		int link = 0;
		
		ArrayList<Integer> start_team = new ArrayList<>();
		ArrayList<Integer> link_team = new ArrayList<>();
		
		// team 구분
		for(int i=0; i<n; i++) {
			if(visited[i])
				start_team.add(i);
			else
				link_team.add(i);
		}
		
		// 팀의 능력치 합 구하기
		start = calStat(start_team);
		link = calStat(link_team);
		
		result_list.add(Math.abs(start-link));
	}
	
	public static int calStat(ArrayList<Integer> team) {
		int sum = 0;

		for(int i=0; i<team.size(); i++) {
			for(int j=0; j<team.size(); j++) {
				sum += arr[team.get(i)][team.get(j)];
			}
		}
		return sum;
	}
}