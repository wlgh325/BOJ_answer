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

class Shark{
	Pos pos;
	int feed;
	int size;

	Shark(Pos pos, int feed){
		this.pos = pos;
		this.feed = feed;
		this.size = 2;
	}
}

class Main {

	static int N, minDist, minX, minY;
	static int[][] map, check;
	static Shark shark;
	// 상, 하, 좌, 우
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int time = 0;
		N = Integer.parseInt(br.readLine());

		// initialize
		map = new int[N][N];
		check = new int[N][N];
		
		for(int i=0; i<N; i++){
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<N; j++){
				int num = Integer.parseInt(temp[j]);
				
				// 아기 상어
				if(num == 9)	shark = new Shark(new Pos(i, j), 0);
				else if(num != 0)	map[i][j] = num;
			}
		}

		while(true){
			init();
			if(!isPossibleFeed()) break;

			findCloseFish();

			// 이동
			time += minDist;
			shark.pos.x = minX;
			shark.pos.y = minY;

			// 먹고 제거
			shark.feed++;
			map[minX][minY] = 0;
			if(shark.feed == shark.size){
				shark.feed = 0;
				shark.size++;
			}
		}

		System.out.println(time);
	}

	public static void init() {
		minDist = Integer.MAX_VALUE;
		minX = Integer.MAX_VALUE;
		minY = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				check[i][j] = -1;
			}
		}
	}

	public static void findCloseFish(){
		Queue<Pos> q = new LinkedList<>();
		int size = shark.size;
		check[shark.pos.x][shark.pos.y] = 0;

		q.offer(shark.pos);
		
		while(!q.isEmpty()){
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;

			for(int i=0; i<4; i++){
				int dx = x + xdir[i];
				int dy = y + ydir[i];
				
				// 이동 가능한 곳이고, 방문하지 않은 곳인 경우 이동
				if(isValidPosition(dx, dy) && map[dx][dy] <= size && check[dx][dy] == -1){
					check[dx][dy] = check[x][y] + 1;

					// 먹을 수 있는지 확인
					if(map[dx][dy] != 0 && map[dx][dy] < size) {
						// 그 중에서 가장 가까운건지 확인
						if(minDist > check[dx][dy]) {
							minX = dx;
							minY = dy;
							minDist = check[dx][dy];
						}
						else if(minDist == check[dx][dy]) {
							if(minX == dx) {
								// 같은 높이면 더 왼쪽
								if(minY > dy) {
									minX = dx;
									minY = dy;
								}
							}
							else if(minX > dx) {
								// 더 위에 있는 거
								minX = dx;
								minY = dy;
							}
						}
					}
					q.offer(new Pos(dx, dy));
				}
			}
		}
	}

	public static boolean isPossibleFeed(){
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		visited[shark.pos.x][shark.pos.y] = true;
		q.offer(new Pos(shark.pos.x, shark.pos.y));
		while(!q.isEmpty()){
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;

			for(int i=0; i<4; i++){
				int dx = x + xdir[i];
				int dy = y + ydir[i];
				if(isValidPosition(dx, dy) && !visited[dx][dy]){
					if(map[dx][dy] == shark.size || map[dx][dy] == 0){
						q.offer(new Pos(dx, dy));
						visited[dx][dy] = true;
					}
					else if(map[dx][dy] < shark.size){
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isValidPosition(int x, int y){
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
}