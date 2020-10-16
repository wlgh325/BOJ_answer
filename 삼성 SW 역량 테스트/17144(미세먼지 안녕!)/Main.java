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

	// 상, 하, 좌, 우
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	static int R, C, T;
	static int[][] map, copy;
	static Queue<Pos> q;
	static Pos[] purifier;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		T = Integer.parseInt(temp[2]);
		
		q = new LinkedList<>();
		map = new int[R][C];
		purifier = new Pos[2];
		int idx = 0;
		for(int i=0; i<R; i++) {
			temp = br.readLine().split(" ");
			for(int j=0; j<C; j++) {
				int num = Integer.parseInt(temp[j]);
				map[i][j] = num;
				
				// 미세먼지 이면
				if(map[i][j] > 0) {
					q.offer(new Pos(i,j));
				}
				else if(map[i][j] == -1) {
					purifier[idx++] = new Pos(i,j);
				}
			}
		}
		
		int time = 0;
		while(time < T) {
			copy = new int[R][C];
		
			// 확산
			diffusion();
			
			// 더하기
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					map[i][j] += copy[i][j];
				}
			}
			
			// 공기청정기 작동
			operation();
			
			// queue에 다시 넣기
			enqueue();
			time++;
		}
		
		System.out.println(getSum());
	}
	
	public static int getSum() {
		int sum = 0;
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == -1) continue;
				sum += map[i][j];
			}
		}
		return sum;
	}
	public static void enqueue() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 미세먼지 이면
				if(map[i][j] > 0) {
					q.offer(new Pos(i,j));
				}
			}
		}
	}
	
	public static void operation() {
		// 위쪽 회전
		int x = purifier[0].x;
		int y = purifier[1].y;
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; y+i<C; i++) {
			q.offer(map[x][y+i]);
		}
		
		for(int i=x-1; i>=0; i--) {
			q.offer(map[i][C-1]);
		}
		
		for(int i=C-2; i>=0; i--) {
			q.offer(map[0][i]);
		}
		
		for(int i=1; i<x; i++) {
			q.offer(map[i][0]);
		}
		
		// 실제 회전
		map[x][y+1] = 0;	// 공기 청정기 바로 오른쪽은 미세먼지 0
		for(int i=2; y+i<C; i++) {
			map[x][y+i] = q.poll();
		}
		
		for(int i=x-1; i>=0; i--) {
			map[i][C-1] = q.poll();
		}
		
		for(int i=C-2; i>=0; i--) {
			map[0][i] = q.poll();
		}
		
		for(int i=1; i<x; i++) {
			map[i][0] = q.poll();
		}
		
		q.poll(); // 공기청정기로 사라짐
		
		// 아래쪽 회전
		x++;
		for(int i=1; y+i<C; i++) {
			q.offer(map[x][y+i]);
		}
		
		for(int i=x+1; i<R; i++) {
			q.offer(map[i][C-1]);
		}
		
		for(int i=C-2; i>=0; i--) {
			q.offer(map[R-1][i]);
		}
		
		for(int i=R-2; i>x; i--) {
			q.offer(map[i][0]);
		}
		
		
		// 실제 회전
		map[x][y+1] = 0;	// 공기 청정기 바로 오른쪽은 미세먼지 0
		for(int i=2; y+i<C; i++) {
			map[x][y+i] = q.poll();
		}
		
		for(int i=x+1; i<R; i++) {
			map[i][C-1] = q.poll();
		}
		
		for(int i=C-2; i>=0; i--) {
			map[R-1][i] = q.poll();
		}
		
		for(int i=R-2; i>x; i--) {
			map[i][0] = q.poll();
		}
		q.poll();
	}
	
	public static void diffusion() {
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int amount = map[x][y] / 5;
			
			for(int i=0; i<4; i++) {
				int dx = x + xdir[i];
				int dy = y + ydir[i];
				
				// 벗어나지 않는지? 공기청정기 위치는 아닌지 check
				if(isValidPosition(dx, dy)) {
					copy[dx][dy] += amount;
					map[x][y] -= amount;
				}
			}
		}
	}
	
	public static boolean isValidPosition(int x, int y){
		if(x < 0 || x >= R || y < 0 || y >= C) return false;
		if((x == purifier[0].x && y == purifier[0].y) || (x == purifier[1].x && y == purifier[1].y)) return false;
		return true;
	}
}