import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos{
	int x;
	int y;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Main {
	static int[][] map;
	static int row, col, num;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int tc=1; tc<=T; tc++){
			String[] temp = br.readLine().split(" ");
			row = Integer.parseInt(temp[0]);
			col = Integer.parseInt(temp[1]);
			num = Integer.parseInt(temp[2]);
	
			map = new int[row][col];
			for(int i=0; i<num; i++){
				temp = br.readLine().split(" ");
				int x = Integer.parseInt(temp[0]);
				int y = Integer.parseInt(temp[1]);
				map[x][y] = 1;
			}
	
			int ans = 0;
			visited = new boolean[row][col];
			for(int i=0; i<row; i++){
				for(int j=0; j<col; j++){
					if(map[i][j] != 0 && !visited[i][j]){
						bfs(i,j);
						ans++;
					}
				}
			}
			System.out.println(ans);
		}
		br.close();
	}

	public static void bfs(int startX, int startY){
		Queue<Pos> q = new LinkedList<>();

		// 상,하,좌,우
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		visited[startX][startY] = true;
		q.offer(new Pos(startX, startY));

		while(!q.isEmpty()){
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;

			for(int i=0; i<4; i++){
				int dx = x + xdir[i];
				int dy = y + ydir[i];

				if(isValidPosition(dx, dy) && !visited[dx][dy] && map[dx][dy] != 0){
					q.offer(new Pos(dx, dy));
					visited[dx][dy] = true;
				}
			}
		}
	}

	public static boolean isValidPosition(int x, int y){
		if(x < 0 || y < 0 || x >= row || y >= col) return false;
		return true;
	}
}