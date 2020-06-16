import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos{
	int x;
	int y;
	int dist;
	int key;
	
	Pos(int x, int y, int dist, int key){
		this.x = x;
		this.y = y;
		this.dist = dist;
		this.key = key;
	}
}

class Main {	
	static char[][] miro;
	static int N, M;
	static int min;
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		
		int mc_x = 0;
		int mc_y = 0;
		miro = new char[N][M];
		for(int i=0; i<N; i++) {
			temp = br.readLine().split("");
			for(int j=0; j<M; j++) {
				miro[i][j] = temp[j].charAt(0);
				if(miro[i][j] == '0') {
					mc_x = i;
					mc_y = j;
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		
		bfs(mc_x, mc_y);
		System.out.println(min == Integer.MAX_VALUE ? "-1" : min);
	}
	
	public static void bfs(int a, int b) {
		boolean[][][] visited = new boolean[N][M][1 << 6];
		Queue<Pos> q = new LinkedList<>();
		visited[a][b][0] = true;
		q.offer(new Pos(a,b,0,0));
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int dist = p.dist;
			int key = p.key;
			
			if(miro[x][y] == '1') {
				min = min > dist ? dist : min;
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int dx = x + xdir[i];
				int dy = y + ydir[i];
				// 유효한 위치이며 방문하지 않은 경우
				if(isValidPosition(dx, dy) && !visited[dx][dy][key]) {
					// 벽인 경우 이동 X
					if(miro[dx][dy] == '#')
						continue;
					// 빈 곳 or 도착지 or 민식이가 원래 있던 곳
					if(miro[dx][dy] == '.' || miro[dx][dy] == '1' || miro[dx][dy] == '0') {
						visited[dx][dy][key] = true;
						q.offer(new Pos(dx, dy, dist+1, key));
					}
					// key가 있는 곳
					else if(miro[dx][dy] >= 'a' && miro[dx][dy] <= 'f') {
						int copy = key;	// 다음 방향 탐색때 엉키지 않기 위해 copy
						copy = copy | (1 << (miro[dx][dy] - 'a'));	// key추가
						visited[dx][dy][copy] = true;
						q.offer(new Pos(dx, dy, dist+1, copy));
					}
					else if(miro[dx][dy] >= 'A' && miro[dx][dy] <= 'F') {
						int door = (1 << (miro[dx][dy] - 'A'));	// 어떤 문?
						// key가 있는지 확인
						if((key & door ) == door) {
							visited[dx][dy][key] = true;
							q.offer(new Pos(dx, dy, dist+1, key));							
						}
					}
				}
			}
		}
	}
	
	public static boolean isValidPosition(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= M) return false;
		return true;
	}
}