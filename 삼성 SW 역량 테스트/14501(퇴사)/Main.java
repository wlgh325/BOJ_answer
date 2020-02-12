import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Consult{
	int t;
	int p;
	
	Consult(int t, int p){
		this.t = t;
		this.p = p;
	}
}

public class Main {
	static int n;
	static Consult[] consult;
	static boolean[] visited;
	static int max = 0;
	static int rr=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		consult = new Consult[n+1];
		for(int i=1; i<=n; i++) {
			String[] temp = br.readLine().split(" ");
			int t = Integer.parseInt(temp[0]);
			int p = Integer.parseInt(temp[1]);
			Consult c = new Consult(t, p);
			consult[i] = c;
		}
		
		// 1개 ~ N개 까지 고를
		for(int i=1; i<=n; i++) {
			visited = new boolean[n+1];
			rr = i;
			comb(1, i);
		}
		bw.write("" + max);
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void comb(int start, int r) {
		if(r==0) {
			solve();
			return;
		}
		
		for(int i=start; i<=n; i++) {
			visited[i] = true;
			comb(i+1, r-1);
			visited[i] = false;
		}
	}
	
	public static void solve() {
		int pay = 0;
		int num = 0;
		
		for(int i=1; i<=n; i++) {
			if(visited[i]) {
				int temp = i;
				// 상담을 하면 참조할 날짜를 증가시킨다.
				i += consult[i].t - 1;
				
				// 상담을 마쳤는데 n일이 넘으면 break하고 pay에 추가하지 않는다.
				if(i > n)
					break;
				
				pay += consult[temp].p;
				num++;
			}
			if(num == rr)
				break;
		}
		
		max = Math.max(pay, max);
	}
}