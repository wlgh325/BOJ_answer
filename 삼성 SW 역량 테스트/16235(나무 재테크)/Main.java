import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class Info{
	Deque<Integer> list;
	Queue<Integer> deadList;
	int energy;

	Info(int energy){
		this.list = new LinkedList<>();
		this.deadList = new LinkedList<>();
		this.energy = energy;
	}
}

class Main {
	static int N;
	static int[][] A;
	static Info[] infos;
	static Info[][] map;
	static int cnt;
	// 좌상, 상, 우상, 좌, 우, 좌하, 하, 우하
	static int[] xdir = {-1,-1,-1,0,0,1,1,1};
	static int[] ydir = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);	// 땅 크기 NxN
		int M = Integer.parseInt(temp[1]);	// 초기 나무 정보 M개
		int K = Integer.parseInt(temp[2]);

		// 겨울에 각 칸에 추가되는 양분 정보
		A = new int[N][N];
		map = new Info[N][N];
		for(int i=0; i<N; i++){
			temp = br.readLine().split(" ");
			for(int j=0; j<N; j++){
				A[i][j] = Integer.parseInt(temp[j]);
				map[i][j] = new Info(5);	// initialize
			}
		}

		cnt = M;
		// 초기 심어있는 나무들
		for(int i=0; i<M; i++){
			temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0]) - 1;
			int y = Integer.parseInt(temp[1]) - 1;
			int age = Integer.parseInt(temp[2]);
			map[x][y].list.add(age);
		}
		
		int year = 0;
		while(year != K){
			spring();
			summer();
			fall();
			winter();
			year++;
		}
		System.out.println(cnt);
	}

	public static void spring(){
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				if(!map[i][j].list.isEmpty()){
					int t = 0;
					int size = map[i][j].list.size();
					while(t < size){
						int age = map[i][j].list.poll();
						// 양분이 자신의 나이보다 많거나 같아야 먹고 자랄 수 있음
						if(map[i][j].energy >= age){
							map[i][j].energy -= age;
							map[i][j].list.offer(age+1);
						}
						else{ // 그렇지 않은 경우 나무 DEAD
							map[i][j].deadList.add(age);
							cnt--;
						}
						t++;
					}
				}
			}
		}
	}

	public static void summer(){
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				// 봄에 죽은 나무가 있다면 양분으로 바꿈
				int sum = 0;
				while(!map[i][j].deadList.isEmpty())
					sum += map[i][j].deadList.poll() / 2;
				map[i][j].energy += sum;
			}
		}
	}

	public static void fall(){
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				if(!map[i][j].list.isEmpty()){
					int size = map[i][j].list.size();
					int t = 0;
					while(t < size){
						int age = map[i][j].list.poll();
						// 나무의 나이가 5의 배수여야 번식 가능
						if(age % 5 == 0){
							// 8방향의 칸에 나무 심기
							for(int q=0; q<8; q++){
								int dx = i + xdir[q];
								int dy = j + ydir[q];
								// 심을 수 있는 위치라면 심기
								if(isValidPosition(dx, dy)){
									map[dx][dy].list.addFirst(1);
									cnt++;
								}
							}
						}
						map[i][j].list.offer(age);
						t++;
					}
				}
			}
		}
	}

	public static void winter(){
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				map[i][j].energy += A[i][j];
			}
		}
	}

	public static boolean isValidPosition(int x, int y){
		if(x < 0 || y < 0 || x >= N || y >= N) return false;
		return true;
	}
}