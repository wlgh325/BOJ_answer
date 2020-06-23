import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int N;
	static int[][] paper;
	static int w=0;
	static int b=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		paper = new int[N][N];
		for(int i=0; i<N; i++){
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<N; j++){
				paper[i][j] = temp[j].charAt(0) - '0';
			}
		}
		
		if(check(0, N, 0, N)){
			if(paper[0][0] == 1){
				System.out.println("1");
				System.out.println("0");
			}
			else{
				System.out.println("0");
				System.out.println("1");
			}
		}
		else{
			make(0, N/2, 0, N/2, N/2);	// 1사분면
			make(N/2, N, 0, N/2, N/2);	// 2사분면
			make(0, N/2, N/2, N, N/2);	// 3사분면
			make(N/2, N, N/2, N, N/2);	// 4사분면
			System.out.println(w);
			System.out.println(b);
		}

		br.close();
	}
	
	public static void make(int startX, int endX, int startY, int endY, int len){
		if(!check(startX, endX, startY, endY)){
			make(startX, startX + len/2, startY, startY + len/2, len/2);	// 1사분면
			make(startX + len/2, startX + len, startY, startY + len/2, len/2);	// 2사분면
			make(startX, startX + len/2, startY + len/2, startY + len, len/2);	// 3사분면
			make(startX + len/2, startX + len, startY + len/2, startY + len, len/2);	// 4사분면
		}
		else{
			if(paper[startY][startX] == 1){
				b++;
			}
			else
				w++;
		}
	}

	public static boolean check(int startX, int endX, int startY, int endY){
	 	int color = paper[startY][startX];
 
		for(int i=startY; i<endY; i++){
			for(int j=startX; j<endX; j++){
				if(paper[i][j] != color){
					return false;
				}	
			}
		}
		return true;
	}
}