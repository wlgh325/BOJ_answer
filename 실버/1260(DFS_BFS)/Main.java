import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Main{	
	static int n,m;
	static int arr[][];
	static boolean[] visited;
	static int start;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
						
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		start = Integer.parseInt(temp[2]);
		arr = new int[n+1][n+1];

		for(int i=0; i<m; i++){
			String[] temp2 = br.readLine().split(" ");
			int a = Integer.parseInt(temp2[0]);
			int b = Integer.parseInt(temp2[1]);
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		visited = new boolean[n+1];
		dfs(start);
		bw.newLine();

		visited = new boolean[n+1];
		bfs(start);
		
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int k) throws IOException {
		visited[k] = true;		
		bw.write(k + " ");
		
		for(int i=1; i<=n; i++){
			if(arr[k][i] == 1 && !visited[i])
				dfs(i);
		}
	}

	public static void bfs(int k) throws IOException{
		Queue<Integer> q = new LinkedList<>();
		
		visited[k] = true;
		bw.write(k + " ");
		q.offer(k);
		while(!q.isEmpty()){	
			int a = q.poll();

			for(int i=1; i<=n; i++){
				int b = arr[a][i];
				if(b != 0 && !visited[i]){
					q.offer(i);
					visited[i] = true;
					bw.write(i + " ");
				}
			}
		}
	}
}