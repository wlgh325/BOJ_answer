import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N, K;
	static int[][] arr;
	static int[][] player;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		// 2: 이김, 1: 비김, 0: 짐
		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		player = new int[3][20];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<20; i++)
			player[1][i] = Integer.parseInt(st.nextToken()) - 1;
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<20; i++)
			player[2][i] = Integer.parseInt(st.nextToken()) - 1;

		// 만약 지우가 가능한 모든 손동작을 한 번씩 다 낸 후에도 아직 우승자가 결정되지 않아 지우가 경기를 더 하게 된다면
		//지우는 이전에 냈던 손동작을 다시 내야하므로 답은 0이 된다.
		visited = new boolean[N];

		perm(0);
		System.out.println(0);
	}

	private static void perm(int r){
		if(r == N){
			boolean flag = false;
			int player1 = 0;
			int player2 = 1;
			int other = 2;
			int[] win = new int[3];
			int[] order = new int[3];

			while(true){
				if(win[1] == K || win[2] == K){
					break;
				}
				else if(win[0] == K){
					flag = true;
					break;
				}
				
				if(order[0] >= N)
					break;

				if(arr[player[player1][order[player1]]][player[player2][order[player2]]] == 0){
					order[player1]++;
					order[player2]++;

					win[player2]++;				
					int temp = player1;
					player1 = other;
					other = temp;
				}
				else if(arr[player[player1][order[player1]]][player[player2][order[player2]]] == 2){
					order[player1]++;
					order[player2]++;

					// player1이 이김
					win[player1]++;
					int temp = player2;
					player2 = other;
					other = temp;
				}
				else{
					order[player1]++;
					order[player2]++;

					// 지우(0) == 경희(1) => 경희 승
					// 지우(0) == 민호(2) => 민호 승
					// 경희(1) == 민호(2) => 민호 승
					// player 숫자가 크면 승
					if(player1 < player2){
						win[player2]++;
						int temp = player1;
						player1 = other;
						other = temp;
					}
					else{
						win[player1]++;
						int temp = player2;
						player2 = other;
						other = temp;
					}
				}
			}
			
			if(flag){
				System.out.println(1);
				System.exit(0);
			}
			return;
		}
		for(int i=0; i<N; i++){
			if(!visited[i]){
				visited[i] = true;
				player[0][r] = i;
				perm(r+1);
				visited[i] = false;
			}
		}
	}
}