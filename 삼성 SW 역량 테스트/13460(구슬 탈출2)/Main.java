import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
	Pos blue;
	Pos red;
	int cnt;
	Pair(Pos blue, Pos red, int cnt){
		this.blue = blue;
		this.red = red;
		this.cnt = cnt;
	}
}

class Pos{
	int x;
	int y;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Main {
	static String board[][];
	static int N,M;
	static Pos hole;
	static Pair p;
	static int min;
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	static boolean bflag, bbflag, rflag, rrflag;
	static int drx, dry, dbx, dby;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		Pos red = null;
		Pos blue = null;
		min = Integer.MAX_VALUE;

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		board = new String[N][M];

		for(int i=0; i<N; i++){
			temp = br.readLine().split("");
			for(int j=0; j<M; j++){
				board[i][j] = temp[j];
				if(board[i][j].equals("R"))
					red = new Pos(i,j);
				else if(board[i][j].equals("B"))
					blue = new Pos(i,j);
				else if(board[i][j].equals("O"))
					hole = new Pos(i,j);
			}
		}

		p = new Pair(blue, red, 0);
		bfs();

		if(min > 10)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	public static void bfs(){
		Queue<Pair> q = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		visited[p.blue.x][p.blue.y][p.red.x][p.red.y] = true;

		q.offer(p);

		while(!q.isEmpty()){
			Pair pp = q.poll();

			int rx = pp.red.x;
			int ry = pp.red.y;
			int bx = pp.blue.x;
			int by = pp.blue.y;
			int cnt = pp.cnt+1;
			
			for(int i=0; i<4; i++){
				drx = rx;
				dry = ry;
				dbx = bx;
				dby = by;

				bflag = false;
				rflag = false;
				rrflag = false;
				bbflag = false;

				// 누가 먼저 움직일지 정한다.
				boolean redFirst = true;
				if(i==0){
					// 위
					if(drx > dbx)
						redFirst = false;
					
				}
				else if(i==1){
					// 아래
					if(drx < dbx)
						redFirst = false;
				}
				else if(i==2){
					// 왼쪽
					if(dry > dby)
						redFirst = false;
				}
				else{
					// 오른쪽
					if(dry < dby)
						redFirst = false;
				}

				// 한 칸씩 움직이기
				while(true){
					if(redFirst){
						// 빨강 구슬 먼저 움직이기
						move(i, true);
						move(i, false);
					}
					else{
						// 파랑 구슬 먼저 움직이기
						move(i, false);
						move(i, true);
					}
					// 두 구슬 모두 더 이상 움직일 수 없는 경우
					if((bbflag || bflag) && (rrflag || rflag))
						break;
				}

				// 파랑색이 구멍에 빠진 경우 실패
				if(bflag)
					continue;
				// 빨강색이 구멍에 빠진 경우 min update
				if(rflag){
					min = min > cnt ? cnt : min;
					continue;
				}

				// 방문하지 않았다면 queue에 넣고 방문 처리
				if(!visited[dbx][dby][drx][dry]){
					q.offer(new Pair(new Pos(dbx, dby), new Pos(drx, dry), cnt));
					visited[dbx][dby][drx][dry] = true;
				}				
			}
		}
		return;
	}

	public static void move(int i, boolean isRed){
		if(isRed){
			drx += xdir[i];
			dry += ydir[i];
			if(drx == hole.x && dry == hole.y)
				rflag = true;
			if(!isValidPosition(drx, dry) || (drx == dbx && dry == dby) || board[drx][dry].equals("#")){
				// 이전 정상위치로 돌리기
				drx -= xdir[i];
				dry -= ydir[i];
				rrflag = true;
			}
		}
		else{
			dbx += xdir[i];
			dby += ydir[i];
			if(dbx == hole.x && dby == hole.y)
				bflag = true;
			if(!isValidPosition(dbx, dby) || (drx == dbx && dry == dby) || board[dbx][dby].equals("#")){
				// 이전 정상위치로 돌리기
				dbx -= xdir[i];
				dby -= ydir[i];
				bbflag = true;							
			}
		}
	}

	public static boolean isValidPosition(int x, int y){
		if(x <= 0 || y <= 0 || x >= N-1 || y >= M-1) return false;
		return true;
	}
}