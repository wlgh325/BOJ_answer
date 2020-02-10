import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

class Main{
	static int n;
	static int[] people;
	static int min = Integer.MAX_VALUE;
	static int[][] adj;
	static boolean[] team;

	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		people = new int[n+1];
		team = new boolean[n+1];
		
		String[] temp = br.readLine().split(" ");
		for(int i=1; i<=n; i++)
			people[i] = Integer.parseInt(temp[i-1]);
	
		
		adj = new int[n+1][n+1];
		for(int i=1; i<=n; i++){			
			String[] temp2 = br.readLine().split(" ");
			for(int j=1; j<temp2.length; j++){
				adj[i][Integer.parseInt(temp2[j])] = 1;
				adj[Integer.parseInt(temp2[j])][i] = 1;
			}
		}
		
		for(int i=1; i<=n/2; i++){
			comb(1,i);
		}

		if(min == Integer.MAX_VALUE)
			bw.write("-1");
		else
			bw.write("" + min);

        bw.flush();
        bw.close();
        br.close();
	}

	public static void comb(int start, int r){
		if(r==0){
			solve();
			return;
		}
		
		for(int i=start; i<=n; i++){
			team[i] = true;
			comb(i+1, r-1);
			team[i] = false;
		}
	}

	public static void solve(){
		if(isConnected(true) && isConnected(false)){
			min = Math.min(min, Math.abs(getNumArea(true) - getNumArea(false)));
		}
		
	}

	public static boolean isConnected(boolean flag){
		boolean result = true;
		boolean[] visited = new boolean[n+1];
		Stack<Integer> stack = new Stack<>();

		for(int i=1; i<=n; i++){
			if(team[i] == flag){
				stack.push(i);
				visited[i] = true;
				break;
			}
		}

		// 탐색
		while(!stack.isEmpty()){
			int a = stack.pop();
			for(int i=1; i<=n; i++){
				// 방문했으면
				if(visited[i])
					continue;
				// team이 아니면
				if(team[i] != flag)
					continue;
				// 연결이 안되어 있으면
				if(adj[a][i] == 0)
					continue;

				stack.push(i);
				visited[i] = true;
			}
		}

		for(int i=1; i<=n; i++){
			// 팀이 아니면
			if(team[i] != flag)
				continue;
			// 같은 팀인데 방문을 안했으면
			if(!visited[i])
				return false;
		}

		return result;
	}

	public static int getNumArea(boolean flag){
		int sum = 0;

		for(int i=1; i<=n; i++){
			if(team[i] == flag)
				sum += people[i];
		}

		return sum;
	}
}