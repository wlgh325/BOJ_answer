import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main{
	static int[][] adj;
	static int n, m;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		adj = new int[n][n];
		for(int i=0; i<m; i++){
			String[] temp = br.readLine().split(" ");
			int a = Integer.parseInt(temp[0]) - 1;
			int b = Integer.parseInt(temp[1]) - 1;
			adj[a][b] = adj[b][a] = 1;
		}

		bfs(0);
	}
	private static void bfs(int a){
	   Queue<Integer> q = new LinkedList<>();
	   boolean[] visited = new boolean[n];
	   int cnt = -1;
	   
	   visited[a] = true;
	   q.add(a);
	   while(!q.isEmpty()){
		   int x = q.poll();
		   cnt++;
		   for(int i=0; i<n; i++){
			   if(adj[x][i] != 0 && !visited[i]){
				   q.add(i);
				   visited[i] = true;
				}
			}
	   }

	   System.out.println(cnt);
   }
}