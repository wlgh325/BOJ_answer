import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Info{
	int effect;
	int element;
	
	Info(int effect, int element){
		this.effect = effect;
		this.element = element;
	}
}

class Main {
	/**
	 * [재료번호][][][]  
	 * [][dir][][]:
	 * [][][4][4]: 
	 */
	static Info[][][][] ingredients;
	static int num;
	static final int W = 0;
	static final int R = 1;
	static final int B = 2;
	static final int G = 3;
	static final int Y = 4;
	static boolean[] visited;
	static int max;
	static Info[][] gama;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		String[] temp;

		// 재료 정보 입력 받기
		ingredients = new Info[num][4][4][4];
		for(int i=0; i<num; i++){
			// 효능
			for(int j=0; j<4; j++){
				temp = br.readLine().split(" ");
				for(int k=0; k<4; k++){
					ingredients[i][0][j][k] = new Info(Integer.parseInt(temp[k]), 0); 
				}
			}

			// 원소
			for(int j=0; j<4; j++){
				temp = br.readLine().split(" ");
				for(int k=0; k<4; k++){
					String str = temp[k];
					switch(str){
						case "W":
							ingredients[i][0][j][k].element = W;
							break;
						case "R":
							ingredients[i][0][j][k].element = R;
							break;
						case "B":
							ingredients[i][0][j][k].element = B;
							break;
						case "G":
							ingredients[i][0][j][k].element = G;
							break;
						case "Y":
							ingredients[i][0][j][k].element = Y;
							break;
					}
				}
			}
			
			// 회전 상태 미리 구하기
			for(int j=1; j<4; j++)
				rotate(i, j);
		}

		visited = new boolean[num*4];

		max = 0;
		// 가마의 상태를 담을 변수 초기화
		gama = new Info[5][5];
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				gama[i][j] = new Info(0,0);
			}
		}
		
		dfs(0, gama);
		System.out.println(max);
	}
	
	public static void dfs(int r, Info[][] gama){
		if(r == 3){
			// 합 구하기
			int[] sum = new int[4];
			for(int i=0; i<5; i++){
				for(int j=0; j<5; j++){
					// 흰색이 아니면 더하기
					if(gama[i][j].element != 0)
						sum[gama[i][j].element-1] += gama[i][j].effect;
				}
			}

			int quality = 7*sum[0] + 5*sum[1] + 3*sum[2] + 2*sum[3];
			max = max < quality ? quality : max;
			return;
		}

		for(int i=0; i<num; i++){
			if(!visited[i]){
				visited[i] = true;
				// 배치
				for(int j=0; j<=1; j++) {
					for(int k=0; k<=1; k++) {
						for(int q=0; q<4; q++) {
							// cal(재료번호, x시작위치, y시작위치, dir, gama 합)
							Info[][] temp = cal(i, j, k, q, gama);
							dfs(r+1, temp);							
						}
					}
				}
				visited[i] = false;
			}
		}
	}

	public static Info[][] cal(int ingNum, int x, int y, int dir, Info[][] gama){
		Info[][] temp = new Info[5][5];
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				temp[i][j] = new Info(gama[i][j].effect, gama[i][j].element);
			}
		}
		
		// 값 더하기
		for(int j=x; j<x+4; j++){
			for(int k=y; k<y+4; k++){
				temp[j][k].effect = gama[j][k].effect + ingredients[ingNum][dir][j-x][k-y].effect;
				// 더해서 음수인 경우 0
				if(temp[j][k].effect < 0)
					temp[j][k].effect = 0;
				else if(temp[j][k].effect > 9)
					temp[j][k].effect = 9;	// 더해서 9 초과인 경우 9
				
				// 흰색이 아니면 원소 바꾸기
				if(ingredients[ingNum][dir][j-x][k-y].element != 0)
					temp[j][k].element = ingredients[ingNum][dir][j-x][k-y].element;
			}
		}
		return temp;
	}
	
	public static void rotate(int ingNum, int dir){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				ingredients[ingNum][dir][i][j] = ingredients[ingNum][dir-1][3-j][i];
			}
		}		
	}
}