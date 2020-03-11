import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Pos {
	int x;
	int y;
	int time;

	Pos(int x, int y, int time){
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

class Main {
	static int width, height;
	static int[][] map;
	static int max;
	static boolean[][] visited;
	static ArrayList<Pos> tomatos;
	// 상,하,좌,우
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 상자의 가로 세로크기 입력 받기
		String[] temp = br.readLine().split(" ");
		width = Integer.parseInt(temp[0]);
		height = Integer.parseInt(temp[1]);

		tomatos = new ArrayList<>();
		map = new int[height][width];
	
		// 상자에 담긴 토마토 정보 입력 받기
		for(int i=0; i<height; i++){
			temp = br.readLine().split(" ");
			for(int j=0; j<width; j++){
				int t = Integer.parseInt(temp[j]);
				map[i][j] = t;
				if(t == 1)
					tomatos.add(new Pos(i,j,0));
			}
		}

		max = 0;
		visited = new boolean[height][width];
		bfs();

		// 이미 모두 익어있는 경우 max 값이 1이 된다.
		if(max == 1){
			System.out.println(0);
			return;
		}

		// 토마토가 모두 익었는지 검사
		if(isGrow())
			System.out.println(max);
		else
			System.out.println(-1);
		br.close();
	}

	private static void bfs(){
		Queue<Pos> q = new LinkedList<>();

		// 익은 토마토 위치 모두 queue에 넣고 시작
		// 동시에 퍼져나가기 위해서
		for(Pos p : tomatos){
			int x = p.x;
			int y = p.y;
			q.add(new Pos(x, y, 0));
			visited[x][y] = true;
		}
				
		while(!q.isEmpty()){
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int t = p.time;

			// 저장된 시간이 max보다 크다면 update
			if(max < t)
				max = t;

			for(int i=0; i<4; i++){
				int dx = x + xdir[i];
				int dy = y + ydir[i];
				// 유효한 위치 && 방문하지 않은 곳
				if(isValidPosition(dx, dy) && !visited[dx][dy]){
					// 빈 곳이 아니라면 방문
					if(map[dx][dy] != -1){
						map[dx][dy] = 1;
						visited[dx][dy] = true;
						q.add(new Pos(dx, dy, t+1));
					}
				}
			}
		}
	}

	private static boolean isGrow(){
		for(int[] a: map){
			for(int num : a){
				if(num == 0)
					return false;
			}
		}
		return true;
	}

	private static boolean isValidPosition(int x, int y){
		if(x < 0 || x >= height || y < 0 || y >= width) return false;
		return true;
	}
}