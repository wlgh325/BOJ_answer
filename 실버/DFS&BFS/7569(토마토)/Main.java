import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

class Pos{
	int x;
	int y;
	int z;
	int time;
	Pos(int x, int y, int z, int time){
		this.x = x;
		this.y = y;
		this.z = z;
		this.time = time;
	}
}

class Main {
	static int M, N, H;
	static int[][][] box;	//[z축][x축][y축]
	static boolean[][][] visited;
	static int[] xdir = {-1,1,0,0,0,0};
	static int[] ydir = {0,0,-1,1,0,0};
	static int[] zdir = {0,0,0,0,-1,1};
	static ArrayList<Pos> tomatos;	// 처음 토마토 위치를 담는 list
	static int max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		M = Integer.parseInt(temp[0]);
		N = Integer.parseInt(temp[1]);
		H = Integer.parseInt(temp[2]);

		box = new int[H][N][M];
		tomatos = new ArrayList<>();
		for(int k=0; k<H; k++){
			for(int i=0; i<N; i++){
				temp = br.readLine().split(" ");
				for(int j=0; j<M; j++){
					box[k][i][j] = Integer.parseInt(temp[j]);
					if(box[k][i][j] == 1){
						tomatos.add(new Pos(i,j,k,0));
					}
				}
			}
		}

		max = 0;
		visited = new boolean[H][N][M];
		bfs();

		if(max == 0){
			System.out.println(0);
			return;
		}

		if(isGrow())
			System.out.println(max);
		else
			System.out.println(-1);
		br.close();
	}
	
	public static void bfs(){
		Queue<Pos> q = new LinkedList<>();

		for(Pos p : tomatos){
			int x = p.x;
			int y = p.y;
			int z = p.z;
			q.offer(new Pos(x,y,z,0));
			visited[z][x][y] = true;
		}
		
		while(!q.isEmpty()){
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int z = p.z;
			int time = p.time;

			if(max < time)
				max = time;

			for(int i=0; i<6; i++){
				int dx = x + xdir[i];
				int dy = y + ydir[i];
				int dz = z + zdir[i];
				if(isValidPosition(dx,dy,dz) && !visited[dz][dx][dy]){
					if(box[dz][dx][dy] != -1){
						box[dz][dx][dy] = 1;
						visited[dz][dx][dy] = true;
						q.offer(new Pos(dx,dy,dz,time+1));
					}
				}
			}
		}
	}

	public static boolean isGrow(){
		for(int[][] h: box){
			for(int[] r: h){
				for(int a: r){
					if(a == 0)
						return false;
				}
			}
		}
		return true;
	}

	public static boolean isValidPosition(int x, int y, int z){
		if(x < 0 || y < 0 || z < 0 || x >= N || y >= M || z >= H) return false;
		return true;
	}
}