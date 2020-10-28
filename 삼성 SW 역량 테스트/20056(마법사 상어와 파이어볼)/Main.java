import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class FireBall{
	int x;
	int y;
	int mass;
	int dir;
	int vel;
	
	FireBall(int mass, int dir, int vel){
		this.mass = mass;
		this.dir = dir;
		this.vel = vel;
	}
	
	FireBall(int x, int y, int mass, int dir, int vel){
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.dir = dir;
		this.vel = vel;
	}
}

class Main {
	static int tot;
	static int N, M, K;
	static ArrayList<FireBall>[][] map;
	static int[] xdir = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] ydir = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		K = Integer.parseInt(temp[2]);
		
		map = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		tot = 0;
		for(int i=0; i<M; i++) {
			temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);
			int mass = Integer.parseInt(temp[2]);
			int vel = Integer.parseInt(temp[3]);
			int dir = Integer.parseInt(temp[4]);
			
			tot += mass;
			FireBall fb = new FireBall(mass, dir, vel);
			map[x][y].add(fb);
		}
		
		int cmd = 0;
		while(cmd < K) {
			move();
			cmd++;
		}
		System.out.println(tot);
	}
	
	public static void move() {
		ArrayList<FireBall> tempList = new ArrayList<>();
		
		// 이동
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j].size() != 0) {
					for(int k=0; k<map[i][j].size(); k++) {
						FireBall fb = map[i][j].get(k);
						
						int dx = i + xdir[fb.dir]*fb.vel % N;
						int dy = j + ydir[fb.dir]*fb.vel % N;
						

						if(dx > N) {
							dx = dx % N;
						}
						else if(dx < 1) {
							dx = N - (Math.abs(dx) % N);
						}
						
						if(dy > N) {
							dy = dy % N;
						}
						else if(dy < 1) {
							dy = N - (Math.abs(dy) % N);
						}
						
						FireBall newFb = new FireBall(dx, dy, fb.mass, fb.dir, fb.vel);
						tempList.add(newFb);
						map[i][j].remove(k--);
					}
				}
			}
		}
		
		// 실제 이동
		for(FireBall fb : tempList) {
 			map[fb.x][fb.y].add(fb); 
		}
		
		// 2개 이상인지 확인
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j].size() > 1) {
					
					// 파이어볼 하나로 합침
					int newMass = 0;
					int newVel = 0;
					int odd = 0;
					int even = 0;
					for(FireBall fb : map[i][j]) {
						newMass += fb.mass;
						newVel += fb.vel;
						
						if(fb.dir % 2 == 0) even++;
						else odd++;
					}
					
					tot -= newMass;
					
					newMass /= 5;
					newVel /= map[i][j].size();
					
					map[i][j].clear();
					if(newMass != 0) {
						tot += newMass * 4;
						// 모두 홀수 or 짝수
						if(even == 0 || odd == 0) {
							// 0, 2, 4, 6
							map[i][j].add(new FireBall(i, j, newMass, 0, newVel));
							map[i][j].add(new FireBall(i, j, newMass, 2, newVel));
							map[i][j].add(new FireBall(i, j, newMass, 4, newVel));
							map[i][j].add(new FireBall(i, j, newMass, 6, newVel));
						}
						else {
							map[i][j].add(new FireBall(i, j, newMass, 1, newVel));
							map[i][j].add(new FireBall(i, j, newMass, 3, newVel));
							map[i][j].add(new FireBall(i, j, newMass, 5, newVel));
							map[i][j].add(new FireBall(i, j, newMass, 7, newVel));
						}
					}
				}
			}
		}
	}
}