import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	static int[][] map;
	static int N;
	static int min;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(temp[j-1]);
			}
		}
		
		min = Integer.MAX_VALUE;
		// (x,y) 정하기
		// d1, d2 정하기 길이는 1이상
		// x + d1 + d2 <= N check
		// 1 <= y-d1
		// y+d2 <= N
		// 모든 경우 다 검사
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(int k=1; k<=N; k++) {
					for(int q=1; q<=N; q++) {
						if(i + k + q > N) continue;
						if(1 > j - k) continue;
						if(j + q > N) continue;
						
						divide(i, j, k, q);
					}
				}
			}
		}
		
		System.out.println(min);
	}
	
	public static void divide(int x, int y, int d1, int d2) {
		int[][] check = new int[N+1][N+1];

		// 경계선 나누기
		for(int i=0; i<=d1; i++) {
			check[x+i][y-i] = 5; 
		}
		
		for(int i=0; i<=d2; i++) {
			check[x+i][y+i] = 5;
		}
		
		for(int i=0; i<=d2; i++) {
			check[x+d1+i][y-d1+i] = 5;
		}
		
		for(int i=0; i<=d1; i++) {
			check[x+d2+i][y+d2-i] = 5;
		}
		
		// 경계선 안쪽 채우기
		boolean start = false;
		for(int i=x+1; i<x+d1+d2; i++) {
			for(int j=1; j<=N; j++) {
				if(start) {
					// 오른쪽 경계선 도달
					if(check[i][j] == 5) {
						start = false;
						break;
					}
					check[i][j] = 5;
				}
				
				// 왼쪽 경계선
				if(check[i][j] == 5) {
					start = true;
				}
			}
		}
		
		// 1번 선거구
		for(int i=1; i<x+d1; i++) {
			for(int j=1; j<=y; j++) {
				if(check[i][j] == 5) continue;
				check[i][j] = 1;
			}
		}
		
		// 2번 선거구
		for(int i=1; i<=x+d2; i++) {
			for(int j=y+1; j<=N; j++) {
				if(check[i][j] == 5) continue;
				check[i][j] = 2;
			}
		}
		
		// 3번 선거구
		for(int i=x+d1; i<=N; i++) {
			for(int j=1; j<y-d1+d2; j++) {
				if(check[i][j] == 5) continue;
				check[i][j] = 3;
			}
		}
		
		// 4번 선거구
		for(int i=x+d2+1; i<=N; i++) {
			for(int j=y-d1+d2; j<=N; j++) {
				if(check[i][j] == 5) continue;
				check[i][j] = 4;
			}
		}
		
		int diff = getDiffPopulation(check);	// 최소 인구와 최대 인구 차이 구하기
		min = min > diff ? diff : min;	// update
	}
	
	public static int getDiffPopulation(int[][] check) {
		int[] sum = new int[5];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				sum[check[i][j]-1] += map[i][j];
			}
		}
		
		Arrays.sort(sum);
		return sum[4] - sum[0];
	}
}