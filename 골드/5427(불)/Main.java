import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos{
	int x;
	int y;
	int time;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}

	Pos(int x, int y, int time){
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

class Main{
	static int R, C;
	// 위, 아래, 왼쪽, 오른쪽
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	static int min;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String[] temp = br.readLine().split(" ");
			R = Integer.parseInt(temp[1]);
			C = Integer.parseInt(temp[0]);
			Pos person = null;	// 초기 상근이 위치
			Queue<Pos> fires = new LinkedList<>();	// 초기 불의 위치

			// 불과 상근이 방문 여부 체크
			// 빌딩 공간을 둘러싸는 박스 형태로 위,아래,왼쪽,오른쪽으로 한 칸씩 더 크게 만든다.
			int[][] visited = new int[R+2][C+2];
			
			// 입력받기
			for(int i=1; i<R+1; i++){
				String s = br.readLine();
				for(int j=1; j<C+1; j++){
					char c = s.charAt(j-1);
					if(c == '*'){
						fires.offer(new Pos(i,j));
						visited[i][j] = -1;
					}
					else if(c == '@')
						person = new Pos(i,j,0);
					else if(c == '#')
						visited[i][j] = -1;
				}
			}

			min = Integer.MAX_VALUE;
			bfs(person, fires, visited);	// bfs 탐색

			// MAX_VALUE 그대로 라면 끝에 도달하지 못한 것이므로 IMPOSSIBLE 출력
			if(min != Integer.MAX_VALUE)
				System.out.println(min);
			else
				System.out.println("IMPOSSIBLE");
		}
	}

	private static void bfs(Pos person, Queue<Pos> fires, int[][] visited){
		Queue<Pos> q = new LinkedList<>();
		
		// 초기화
		visited[person.x][person.y] = 1;
		q.offer(person);
		
		// 상근이의 위치를 담은 queue가 빌때까지 수행
		while(!q.isEmpty()) {
			// 불 먼저 퍼뜨리기
			// 시간 별로 퍼트리기 위해 초기 담겨 있던 불의 개수만큼만 진행하고
			// 새로 이동한 불은 다음 반복때 퍼트린다.
			for(int i=0, end=fires.size(); i<end; i++){
				Pos f = fires.poll();
				int fx = f.x;
				int fy = f.y;
				
				for(int j=0; j<4; j++){
					int dfx = fx + xdir[j];
					int dfy = fy + ydir[j];
					// 위치가 유효하고 불이 번지지 않았다면 이동(-1)
					if(dfx > 0 && dfy > 0 && dfx < R+1 && dfy < C+1 && visited[dfx][dfy] != -1){
						visited[dfx][dfy] = -1;
						fires.offer(new Pos(dfx, dfy));
					}
				}
			}

			// 상근이 이동
			for(int i=0, end=q.size(); i<end; i++) {
				Pos p = q.poll();
				int x = p.x;
				int y = p.y;
				int time = p.time;			
				
				// 끝에 도달한 경우 min값 update
				if(x == 0 || y == 0 || x == R+1 || y == C+1){
					min = min > time ? time : min;
					continue;
				}

				for(int j=0; j<4; j++){
					int dx = x + xdir[j];
					int dy = y + ydir[j];
					// 범위가 유효하고 불이 퍼지지 않았고 상근이가 방문한 곳이 아니라면 방문 처리(1)
					if(dx >= 0 && dy >= 0 && dx <= R+1 && dy <= C+1){
						if(visited[dx][dy] != -1 && visited[dx][dy] != 1) {
							q.offer(new Pos(dx, dy, time+1));
							visited[dx][dy] = 1;	
						}
					}
				}
			}
		}
	}
}