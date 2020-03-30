import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos{
	int x;
	int y;
	int z;
	int cnt;

	Pos(int x, int y, int z, int cnt){
		this.x = x;
		this.y = y;
		this.z = z;
		this.cnt = cnt;
	}
}

class Main{
	static boolean[] visited;	// 조합을 위한 visited
	static int[][][] miro;	// 원본배열
	static int[][][] c_miro;	// 복사한 배열, 이로 miro 이동을 함
	static int[] order;	// 판을 놓는 순서
	// 위, 아래, 왼쪽 오른쪽, 왼2, 오2
	static int[] xdir = {0,0,0,0,-1,1};
	static int[] ydir = {0,0,-1,1,0,0};
	static int[] zdir = {-1,1,0,0,0,0};
	static int min;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		miro = new int[5][5][5];
		c_miro = new int[5][5][5];
		order = new int[5];

		for(int k=0; k<5; k++){
			for(int i=0; i<5; i++){
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<5; j++){
					miro[k][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		min = Integer.MAX_VALUE;
		
		// 판을 놓는 조합 찾기
		visited = new boolean[5];
		perm(0);

		if(min != Integer.MAX_VALUE)
			System.out.println(min);
		else
			System.out.println(-1);
	}

	private static void perm(int r){
		if(r==5){
			// 순서에 따른 미로 만들기
			for(int i=0; i<5; i++)
				System.arraycopy(miro[order[i]], 0, c_miro[i], 0, c_miro[i].length);

			// 탐색
			// 회전시킬 수 있는 모든 경우를 따짐
			for(int i=0; i<4; i++){
				rotate(4);
				for(int j=0; j<4; j++){
					rotate(3);
					for(int k=0; k<4; k++){
						rotate(2);
						for(int d=0; d<4; d++){
							rotate(1);
							for(int f=0; f<4; f++){
								rotate(0);
								// 출발지와 도착지가 1인 경우에만 bfs 실행
								if(c_miro[0][0][0] == 1 && c_miro[4][4][4] == 1)
									bfs(0,0,0);
							}
						}
					}
				}
			}
		}
		for(int i=0; i<5; i++){
			if(!visited[i]){
				visited[i] = true;
				order[i] = r;
				perm(r+1);
				order[i] = -1;
				visited[i] = false;
			}
		}
	}
	private static void bfs(int a, int b, int c){
		Queue<Pos> q = new LinkedList<>();
		boolean[][][] visited = new boolean[5][5][5];
		q.offer(new Pos(a,b,c,0));

		visited[0][0][0] = true;

		while(!q.isEmpty()){
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int z = p.z;
			int cnt = p.cnt;

			// 도착지에 도착
			if(z == 4 && x == 4 && y == 4){
				min = min > cnt ? cnt : min;
				continue;
			}			
			// 모든 방향으로 이동
			for(int j=0; j<6; j++){
				int dx = x + xdir[j];
				int dy = y + ydir[j];
				int dz = z + zdir[j];	
				// 유효한 범위이며 방문하지 않았고 c_miro의 값이 0이 아닌경우에만 이동 가능
				if(isValidPosition(dx, dy, dz) && !visited[dz][dx][dy] && c_miro[dz][dx][dy] != 0){
					visited[dz][dx][dy] = true;
					q.offer(new Pos(dx, dy, dz, cnt+1));
				}
			}
		}	
	}

	private static boolean isValidPosition(int x, int y, int z){
		if(x < 0 || y < 0 || z < 0 || x >= 5 || y >= 5 || z >= 5) return false;
		return true;
	}
	
	// 시계 방향 90도 회전
	private static void rotate(int layer){
		int[][] temp = new int[5][5];
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				temp[i][j] = c_miro[layer][4-j][i];
			}
		}
		System.arraycopy(temp, 0, c_miro[layer], 0, temp.length);
	}
}