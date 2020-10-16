import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

class Pos implements Comparable<Pos>{
	int x;
	int y;
	int dist;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
		this.dist = 0;
	}

	Pos(int x, int y, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	// 오름차순
	@Override
	public int compareTo(Pos p){
		if(this.dist > p.dist){
			return 1;
		}
		else if(this.dist < p.dist){
			return -1;
		}
		else {
			if(this.x != p.x)
				return this.x - p.x;
			else
				return this.y - p.y;
		}
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

	static int N;
	static int[][] map;
	static Shark shark;
	static PriorityQueue<Pos> pq;

	// 상, 하, 좌, 우
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int time = 0;
		N = Integer.parseInt(br.readLine());

		// initialize
		map = new int[N][N];

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
			if(!isPossibleFeed()) break;

			pq = new PriorityQueue<>();
			updateDistance();

			Pos minPos = null;
			if(pq.size() != 0){
				minPos = pq.poll();
			}
			else break;

			int minX = minPos.x;
			int minY = minPos.y;

			// 이동
			time += minPos.dist;
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

	public static void updateDistance(){
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				if(map[i][j] != 0 && map[i][j] < shark.size){
					int dist = getDistance(shark.size, shark.pos, new Pos(i,j));
					pq.offer(new Pos(i, j, dist));
				}
			}
		}
	}

	public static int getDistance(int size, Pos shark, Pos fish){
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int min = Integer.MAX_VALUE;

		q.offer(shark);
		visited[shark.x][shark.y] = true;
		while(!q.isEmpty()){
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int dist = p.dist;

			if(x == fish.x && y == fish.y){
				min = min > dist ? dist : min;
				continue;
			}

			for(int i=0; i<4; i++){
				int dx = x + xdir[i];
				int dy = y + ydir[i];

				if(isValidPosition(dx, dy) && map[dx][dy] <= size && !visited[dx][dy]){
					visited[dx][dy] = true;
					q.offer(new Pos(dx, dy, dist+1));
				}
			}
		}

		return min;
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