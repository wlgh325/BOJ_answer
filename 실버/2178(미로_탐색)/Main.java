import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos {
	int x;
	int y;
	int dist;

	Pos(int x, int y, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

class Main{	
	static int n,m;
	static int arr[][];
	static boolean[][] visited;
	static int start;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		arr = new int[n][m];
		for(int i=0; i<n; i++){
			String[] temp2 = br.readLine().split("");
			for(int j=0; j<m; j++){
				arr[i][j] = Integer.parseInt(temp2[j]);
			}
		}
		
		visited = new boolean[n][m];
		visited[0][0] = true;
		System.out.println(bfs(0, 0));

		br.close();
	}

	public static int bfs(int x, int y) throws IOException {
		Queue<Pos> q = new LinkedList<>();

		// 위, 아래, 왼, 오
		int[] xdir = {-1, 1, 0, 0};
		int[] ydir = {0,0,-1,1};

		q.offer(new Pos(x,y, 1));
		visited[x][y] = true;
		while(!q.isEmpty()){
			Pos p = q.poll();
			int a = p.x;
			int b = p.y;
			int dist = p.dist;

			if(a == n-1 && b == m-1)
				return dist;

			for(int i=0; i<4; i++){
				int dx = a + xdir[i];
				int dy = b + ydir[i];

				if(isValidPosition(dx, dy) && !visited[dx][dy]){
					q.offer(new Pos(dx,dy, dist+1));
					visited[dx][dy] = true;
				}
			}
		}
		return 0;
	}

	public static boolean isValidPosition(int x, int y){
		if(x < 0 || x >= n || y < 0 || y >= m || arr[x][y] == 0)
			return false;
		return true;
	}
}