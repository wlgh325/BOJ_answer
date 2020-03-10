import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int M,N;
	static String[] board;
	static final String[] chess1 = {
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW"
	};
	static final String[] chess2 = {
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB"
	};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new String[N];
		for(int i=0; i<N; i++)
			board[i] = br.readLine();		

		int sum1 = 0;
		int sum2 = 0;
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;

		// 열 sliding 탐색
		for(int j=0; j<=M-8; j++){
			// 행 sliding 탐색
			for(int t=0; t<=N-8; t++){
				// 행 8칸
				for(int i=t; i<t+8; i++){
					String sub = board[i].substring(j, j+8);
					// 1번 체스판 비교
					if(!sub.equals(chess1[i-t])){
						for(int k=j; k<j+8; k++){
							// 열 비교
							if(sub.charAt(k-j) != chess1[i-t].charAt(k-j))
								sum1++;
						}
					}
					// 2번 체스판 비교
					if(!sub.equals(chess2[i-t])){
						for(int k=j; k<j+8; k++){
							// 열 비교
							if(sub.charAt(k-j) != chess2[i-t].charAt(k-j))
								sum2++;
						}
					}
				}
				min1 = min1 > sum1 ? sum1 : min1;
				min2 = min2 > sum2 ? sum2 : min2;
				sum1=0;
				sum2=0;
			}
		}

		int min = min1 > min2 ? min2 : min1;
		System.out.println(min);
		br.close();
	}
}