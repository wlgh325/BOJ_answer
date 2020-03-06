import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.Collections;

class Pos implements Comparable<Pos>{
	int x;
	int y;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Pos p){
		if(this.y < p.y)
			return -1;
		else if(this.y > p.y)
			return 1;
		return 0;
	}
}

class Main {
	static int height, width, D;
	static int[][] map;
	static int max;
	static int enemy = 0;
	static List<Integer> archers;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 행의 수, 열의 수, 공격 제한 거리 입력 받기
		String[] temp = br.readLine().split(" ");
		height = Integer.parseInt(temp[0]);
		width = Integer.parseInt(temp[1]);
		D = Integer.parseInt(temp[2]);
		map = new int[height][width];

		// 격자판의 상태 입력 받기
		for(int i=0; i<height; i++){
			temp = br.readLine().split(" ");
			for(int j=0; j<width; j++){
				int t = Integer.parseInt(temp[j]);
				map[i][j] = t;
				if(t == 1)
					enemy++;
			}
		}
		max = 0;
		archers = new ArrayList<>();

		// 궁수를 놓는 모든 조합 찾기
		comb(0,width,3);
		System.out.println(max);
		br.close();
	}
	
	private static void comb(int start, int N, int r){
		if(r==0){
			int kill = solve();
			max = max < kill ? kill : max;
			return;
		}

		for(int i=start; i<N; i++){
			archers.add(i);
			comb(i+1, N, r-1);
			archers.remove(archers.size()-1);
		}
	}

	private static int solve(){
		int run = 0;
		int[][] copy = deepCopy(map);		
		int kill = 0;
		List<Pos> killed, killed2, killed3;

		// 모든 적이 없어질때까지 턴 반복
		while(run + kill != enemy){
			killed = new ArrayList<>();
			killed2 = new ArrayList<>();
			killed3 = new ArrayList<>();

			boolean flag;	// 적 발견시 dist를 증가시켜 탐색하지 않고 while문 탈출을 위한 flag
			// 궁수들의 적 탐색
			for(int i=0; i<archers.size(); i++){
				int idx = archers.get(i);
				flag = false;

				int dist = 1;
				// 거리를 늘려가며 적을 찾거나 거리가 D를 넘기 전까지 반복하며 탐색
				while(true){
					int temp = 0;
					// 거리가 작은 것, 왼쪽 아래부터 탐색
					for(int j=height-1; j>=0; j--){
						for(int k=0; k<width; k++){
							// 거리 1부터 D까지
							if(getDistance(height, idx, j, k) == dist){
								temp++;	// dist내에서 찾을 수 있는 곳의 개수 check
								// 적이 있다면 제거하기 위한 리스트에 넣기
								if(copy[j][k] == 1){
									switch(i){
										// 1번 궁수
										case 0:
											killed.add(new Pos(j,k));
											break;
										// 2번 궁수
										case 1:
											killed2.add(new Pos(j,k));
											break;
										// 3번 궁수
										case 2:
											killed3.add(new Pos(j,k));
									}
									flag = true;	// 적 발견시 true로 설정
								}
							}
						}
						
						// 거리 dist에서 탐색 해야할 수 = dist*2-1 (1,3,5,7,...)
						// 적을 찾아도 같은 거리내의 모든 적을 찾기 => 가장 왼쪽의 적을 찾기 위해서
						if(temp == dist*2-1)
							break;
						
					}
					dist++;
					// 적을 찾은 경우 다른 궁수 탐색
					if(flag)
						break;
					// D보다 작은 거리만 탐색
					if(dist > D)
						break;
				}
			}
			
			// 행 기준 정렬
			Collections.sort(killed);
			Collections.sort(killed2);
			Collections.sort(killed3);

			// 각 궁수가 찾은 가장 왼쪽 적 제거
			if(killed.size() != 0){
				Pos p1 = killed.get(0);
				if(copy[p1.x][p1.y] == 1){
					copy[p1.x][p1.y] = 0;
					kill++;
				}
			}
			if(killed2.size() != 0){
				Pos p2 = killed2.get(0);
				if(copy[p2.x][p2.y] == 1){
					copy[p2.x][p2.y] = 0;
					kill++;
				}
			}
			if(killed3.size() != 0){
				Pos p3 = killed3.get(0);
				if(copy[p3.x][p3.y] == 1){
					copy[p3.x][p3.y] = 0;
					kill++;
				}
			}

			// 맨 밑에줄에 있는 적은 제외 시키기
			for(int i=0; i<width; i++){
				if(copy[height-1][i] == 1){
					copy[height-1][i] = 0;
					run++;
				}
			}

			// 밑으로 하나씩 이동 시키기
			for(int i=height-2; i>=0; i--){
				for(int j=0; j<width; j++){
					if(copy[i][j] == 1){
						copy[i+1][j] = 1;
						copy[i][j] = 0;
					}
				}
			}			
		}
		return kill;	// 죽인 적의 수 return
	}

	// 배열 복사
	private static int[][] deepCopy(int[][] src){
		int[][] dest = new int[src.length][src[0].length];
		for(int i=0; i<src.length; i++)
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		return dest;
	}

	// 두 점의 거리 구하기
	private static int getDistance(int x, int y, int x2, int y2){
		return Math.abs(x-x2) + Math.abs(y-y2);
	}
}