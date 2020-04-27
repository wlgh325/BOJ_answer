import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int N,M;
	static int[][] map;
	static int max;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		max = Integer.MIN_VALUE;
		// ㅡ
		for(int i=0; i<N; i++){
			for(int j=0; j<M-3; j++){
				int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
				max = max < sum ? sum : max;
			}
		}
		
		// ㅣ
		for(int i=0; i<M; i++){
			for(int j=0; j<N-3; j++){
				int sum = map[j][i] + map[j+1][i] + map[j+2][i] + map[j+3][i];
				max = max < sum ? sum : max;
			}
		}
		
		// ㅁ
		for(int i=0; i<N-1; i++){
			for(int j=0; j<M-1; j++){
				int sum = map[i][j] + map[i][j+1] + map[i+1][j] + map[i+1][j+1];
				max = max < sum ? sum : max;
			}
		}
		
		// ㄴ
		for(int i=0; i<N-2; i++){
			for(int j=0; j<M-1; j++){
				int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+2][j+1];
				max = max < sum ? sum : max;
			}
		}
		
		// J
		for(int i=1; i<N; i++){
			for(int j=0; j<M-2; j++){
				int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i-1][j+2];
				max = max < sum ? sum : max;
			}
		}
		// ㄱ
		for(int i=0; i<N-2; i++){
			for(int j=0; j<M-1; j++){
				int sum = map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+2][j+1];
				max = max < sum ? sum : max;
			}
		}

		// 「
		for(int i=0; i<N-1; i++){
			for(int j=0; j<M-2; j++){
				int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j];
				max = max < sum ? sum : max;
			}
		}
		
		// ㅜ
		for(int i=0; i<N-1; i++){
			for(int j=0; j<M-2; j++){
				int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+1];
				max = max < sum ? sum : max;
			}
		}
		// ㅏ
		for(int i=0; i<N-2; i++){
			for(int j=0; j<M-1; j++){
				int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+1][j+1];
				max = max < sum ? sum : max;
			}
		}
		// ㅗ
		for(int i=1; i<N; i++){
			for(int j=0; j<M-2; j++){
				int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i-1][j+1];
				max = max < sum ? sum : max;
			}
		}
		// ㅓ
		for(int i=1; i<N-1; i++){
			for(int j=0; j<M-1; j++){
				int sum = map[i][j] + map[i][j+1] + map[i-1][j+1] + map[i+1][j+1];
				max = max < sum ? sum : max;
			}
		}
		// 번개모양
		for(int i=0; i<N-2; i++){
			for(int j=0; j<M-1; j++){
				int sum = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+2][j+1];
				max = max < sum ? sum : max;
			}
		}

		// 번개모양 회전
		for(int i=1; i<N; i++){
			for(int j=0; j<M-2; j++){
				int sum = map[i][j] + map[i][j+1] + map[i-1][j+1] + map[i-1][j+2];
				max = max < sum ? sum : max;
			}
		}

		// 번개 모양 대칭
		for(int i=0; i<N-2; i++){
			for(int j=1; j<M; j++){
				int sum = map[i][j] + map[i+1][j] + map[i+1][j-1] + map[i+2][j-1];
				max = max < sum ? sum : max;
			}
		}

		for(int i=0; i<N-1; i++){
			for(int j=0; j<M-2; j++){
				int sum = map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+1][j+2];
				max = max < sum ? sum : max;
			}
		}

		// ㄴ 대칭
		for(int i=0; i<N-2; i++){
			for(int j=1; j<M; j++){
				int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+2][j-1];
				max = max < sum ? sum : max;
			}
		}

		for(int i=0; i<N-1; i++){
			for(int j=0; j<M-2; j++){
				int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+2];
				max = max < sum ? sum : max;
			}
		}

		for(int i=0; i<N-2; i++){
			for(int j=0; j<M-1; j++){
				int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i][j+1];
				max = max < sum ? sum : max;
			}
		}

		for(int i=0; i<N-1; i++){
			for(int j=0; j<M-2; j++){
				int sum = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+1][j+2];
				max = max < sum ? sum : max;
			}
		}
		System.out.println(max);
        br.close();
	}
}