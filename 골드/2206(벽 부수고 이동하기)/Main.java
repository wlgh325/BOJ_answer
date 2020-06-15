import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos{
	int x;
	int y;
	int isBreak;
	
	Pos(int x, int y, int isBreak){
		this.x = x;
		this.y = y;
		this.isBreak = isBreak;
	}
}

class Main {
	static int N, M;
	static int[][] map;
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	static int[][][] value;
	static int min;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		map = new int[N][M];
		for(int i=0; i<N; i++){
			String str = br.readLine();
			for(int j=0; j<M; j++){
				map[i][j] = str.charAt(j) - '0';
			}
		}

		value = new int[N][M][2];		
		min = Integer.MAX_VALUE;
		bfs(0,0);

		System.out.println(min != Integer.MAX_VALUE ? min : "-1");
		br.close();
	}

	public static void bfs(int a, int b){
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(a,b,0));

		value[a][b][0] = 1;
		value[a][b][1] = 1;
		while(!q.isEmpty()){
			Pos p = q.poll();
			int x=  p.x;
			int y = p.y;
			int isBreak = p.isBreak;

			if(x == N-1 && y == M-1){
				min = min > value[x][y][isBreak] ? value[x][y][isBreak] : min;
				continue;
			}

			for(int i=0; i<4; i++){
				int dx = x + xdir[i];
				int dy = y + ydir[i];
				if(isValidPosition(dx, dy) && value[dx][dy][isBreak] == 0) {
					// 벽이 있지만 아직 뚫지 않았다면
					if(map[dx][dy] == 1 && isBreak == 0){
						value[dx][dy][1] = value[x][y][0] + 1;
						q.offer(new Pos(dx, dy, 1));
					}
					else{
						// 벽이 없고 방문하지 않은 경우
						if(map[dx][dy] == 0){
							value[dx][dy][isBreak] = value[x][y][isBreak] + 1;
							q.offer(new Pos(dx, dy, isBreak));
						}
					}
				}
			}
		}
	}

	public static boolean isValidPosition(int x, int y){
		if(x < 0 || y < 0 || x >= N || y >= M) return false;
		return true;
	}
}