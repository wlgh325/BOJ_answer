import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

class Pos{
	int x;
	int y;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Main {
	static int R, C, N;
	static char[][] cave;
	static int[] heights;
	static int dir;
	static final int LEFT = 0;
	static final int RIGHT = 1;
	static boolean[][] visited;
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	static ArrayList<Pos> airCluster;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);

		cave = new char[R+1][C+1];
		// '.': 빈칸
		// 'x': 미네랄
		for(int i=R; i>=1; i--){
			temp = br.readLine().split("");
			for(int j=1; j<=C; j++){
				cave[i][j] = temp[j-1].charAt(0);
			}
		}
		
		// 던지는 막대 높이
		N = Integer.parseInt(br.readLine());
		temp = br.readLine().split(" ");
		heights = new int[N];
		for(int i=0; i<N; i++)
			heights[i] = Integer.parseInt(temp[i]);

		// 시뮬레이션
		dir = LEFT;
		for(int i=0; i<N; i++){
			throwStick(i);
			print();
			visited = new boolean[R+1][C+1];
			for(int j=1; j<=C; j++){
				if(!visited[1][j] && cave[1][j] == 'x')
					bfs(j);
			}
			airCluster = new ArrayList<>();
			if(!checkAirCluster()) continue;
			down();
			print();
		}
	}

	public static void down(){
		// 클러스터 모양 유지를 위해 가장 작은 높이만큼 내려가기
		int min = Integer.MAX_VALUE;
		int height = 0;
		for(int i=0; i<airCluster.size(); i++){
			Pos p = airCluster.get(i);
			int x = p.x;
			int y = p.y;

			height = 0;
			// 밑으로 내려가며 높이 확인하기
			for(int j=x-1; j>=1; j--){
				if(cave[j][y] == '.')
					height++;
				else if(cave[j][y] == 'x'){
					if(!visited[j][y]){
						height = Integer.MAX_VALUE;
						break;
					}
					else
						break;
				}
			}
			min = min > height ? height : min;
		}

		// 내리기
		for(int i=0; i<airCluster.size(); i++){
			Pos p = airCluster.get(i);
			int x = p.x;
			int y = p.y;

			cave[x][y] = '.';
			cave[x-min][y] = 'x';
		}
	}

	public static boolean checkAirCluster(){
		boolean flag = false;
		for(int i=1; i<=R; i++){
			for(int j=1; j<=C; j++){
				if(cave[i][j] == 'x' && !visited[i][j]){
					airCluster.add(new Pos(i,j));
					flag = true;
				}
			}
		}
		return flag;
	}

	public static void throwStick(int idx){
		int height = heights[idx];
		if(dir == LEFT){
			for(int i=1; i<=C; i++){
				// 미네랄이 있으면 파괴
				if(cave[height][i] == 'x'){
					cave[height][i] = '.';
					dir = RIGHT;
					break;
				}
			}

			dir = RIGHT;
		}
		else{
			for(int i=C; i>=1; i--){
				// 미네랄이 있으면 파괴
				if(cave[height][i] == 'x'){
					cave[height][i] = '.';
					dir = LEFT;
					break;
				}
			}
			dir = LEFT;
		}
		//print();
	}

	public static void bfs(int idx){
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(1, idx));
		visited[1][idx] = true;

		while(!q.isEmpty()){
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;

			for(int i=0; i<4; i++){
				int dx = x + xdir[i];
				int dy = y + ydir[i];
				if(isValidPosition(dx, dy) && !visited[dx][dy] && cave[dx][dy] == 'x'){
					visited[dx][dy] = true;
					q.offer(new Pos(dx, dy));
				}
			}
		}
	}

	public static boolean isValidPosition(int x, int y){
		if(x < 1 || y < 1 || x > R || y > C) return false;
		return true;
	}

	public static void print(){
		for(int i=R; i>=1; i--){
			for(int j=1; j<=C; j++){
				System.out.print(cave[i][j]);
			}
			System.out.println();
		}
	}
}