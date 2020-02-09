import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Counseling{
	int t;
	int p;
	
	Counseling(int t, int p){
		this.t = t;
		this.p = p;
	}
}

class Main{	
	static Counseling[] arr;
	static Set<Integer> set;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[n];
		
		set = new HashSet<>();
		arr = new Counseling[n];
		
		// 상담 일정표 받기
		for(int i=0; i<n; i++) {
			String[] temp = br.readLine().split(" ");
			arr[i] =  new Counseling(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}
		
		for(int i=1; i<=n; i++) {
			comb(visited, 0, n, i, i);
		}
		Object[] arr = set.toArray();
		Arrays.sort(arr);
		
		bw.write(String.valueOf(arr[arr.length-1]));
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void comb(boolean[] visited, int start, int n, int r, int cnt) {
		if(r==0) {
			solve(visited, n, cnt);
			return;
		}
		for(int i=start; i<n; i++) {
			visited[i] = true;
			comb(visited, i+1, n, r-1, cnt);
			visited[i] = false;
		}
	}
	
	public static void solve(boolean[] visited, int n, int r) {
		int sum = 0;
		int cnt = 0;
		
		for(int i=0; i<n; i++) {
			if(visited[i]) {
				if(arr[i].t + i > n)
					break;
				else {
					sum += arr[i].p;
					i += arr[i].t;
					i--;
				}
				cnt++;
			}
			
			if(r == cnt) break;
		}
		
		set.add(sum);
	}
}