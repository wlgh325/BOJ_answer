import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Pos {
	int x;
	int y;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Main {
	static int n;
	static int[][] adj;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 정점의 개수
		n = Integer.parseInt(br.readLine());
		adj = new int[n][n];
		
		// 인접 행렬 입력받기
		String[] temp = null;
		for(int i=0; i<n; i++){
			temp = br.readLine().split(" ");
			for(int j=0; j<n; j++){				
				adj[i][j] = Integer.parseInt(temp[j]);
			}
		}

		// i에서 갈 수 있는 곳 체크
		for(int i=0; i<n; i++)
			bfs(i);

		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				System.out.print(adj[i][j] + " ");
			}
			System.out.println("");
		}
		br.close();
	}

	private static void bfs(int k){
		boolean[] visited;
		visited = new boolean[n];
		Queue<Integer> q = new LinkedList<>();
		q.offer(k);

		while(!q.isEmpty()){
			int cur = q.poll();
			for(int i=0; i<n; i++){
				// 방문하지 않았고, 연결되어 있는 점들을 타고 들어간다.
				if(adj[cur][i] == 1 && !visited[i]){
					q.offer(i);
					visited[i] = true;	// 방문 처리
					adj[k][i] = 1;	// 시작점 k와 연결된 i
				}
			}
		}
	}
}