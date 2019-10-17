import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class CCTV{
	int x;
	int y;
	int num;
	int dir;
	
	CCTV(int x, int y, int num){
		this.x = x;
		this.y = y;
		this.num = num;
	}

	CCTV(CCTV cctv){
		this.x = cctv.x;
		this.y = cctv.y;
		this.num = cctv.num;
		this.dir = cctv.dir;
	}
}

class Main {
	// 사각지대 : CCTV가 감시할 수 없는 영역
	// cctv 회전 가능 (90도)
	
	// 사각지대 최소 값 구하기 ( 0의 개수가 최소 )
	
	
	/*	
		0: 빈칸
		6: 벽
		1~5: cctv
	*/
	static int[][] map;	// 사무실
	static ArrayList<Integer> sumList;
	static ArrayList<CCTV> arrList;
	static int height;
	static int width;
	static boolean[] visited;
	static ArrayList<CCTV> comb;

	// 모든 경우 따져서 0의 개수가 최소가 되는 경우 구하기
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] temp = input.split(" ");
		height = Integer.parseInt(temp[0]);
		width = Integer.parseInt(temp[1]);

		map = new int[height][width];
		arrList = new ArrayList<>();
		
		sumList = new ArrayList<>();
		comb = new ArrayList<>();

		for(int i=0; i<height; i++){
			String input2 = br.readLine();
			String[] temp2 = input2.split(" ");
			for(int j=0; j<width; j++){
				int temp_num = Integer.parseInt(temp2[j]);
				if(temp_num !=0 && temp_num != 6){
					CCTV cctv = new CCTV(i,j,temp_num);
					arrList.add(cctv);
				}
				map[i][j] = temp_num;
			}
		}

		int cctvNum = arrList.size();
		visited = new boolean[cctvNum];

		// dir을 하나씩 바꿔가면서 해야함
		dfs(arrList, 0, cctvNum, cctvNum);
	}

	static void dfs(ArrayList<CCTV> arrList, int start, int N, int r){
		int k = 0;

		if(r==0){
			start(arrList, k, N);
		}
		else{
			for(int i=start; i<N; i++){
				visited[i] = true;
				dfs(arrList, i+1, N, r-1);
				arrList.get(start).dir +=1;
				visited[i] = false;
				k++;
			}
		}
		
	}

	static void start(ArrayList<CCTV> arrList, int i, int N){
		ArrayList<CCTV> copyList = new ArrayList<>();;
		int[][] tempMap;

		for(int j=0; j< arrList.size(); j++){
			copyList.add(new CCTV(arrList.get(j)));
		}
		
		for(int k=0; k<4; k++){
			tempMap = deepCopy(map);
			for(int j=0; j<N; j++){
				surveillance(tempMap, copyList.get(j));
			}
			calCulate(tempMap);

			copyList.get(i).dir += 1;
		}
	}
	
	static int[][] deepCopy(int[][] original2){
		if(original2 == null) return null;
		int[][] result = new int[original2.length][original2[0].length];
		 
		for(int i=0; i<original2.length; i++){
			System.arraycopy(original2[i], 0, result[i], 0, original2[0].length);
		}
		 
		return result;
	}

	static void calCulate(int[][] map){
		int sum=0;
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				if(map[i][j] ==0)
					sum+=1;
			}
		}

		sumList.add(sum);
	}

	static void surveillance(int[][] map, CCTV cctv){
		switch(cctv.num){
			case 1:
				surveillance1(map, cctv);
				break;
			case 2:
				surveillance2(map,cctv);
				break;
			case 3:
				surveillance3(map, cctv);
				break;
			case 4:
				surveillance4(map, cctv);
				break;
			case 5:
				surveillance5(map, cctv);
		}
	}

	// 0: 북
	// 1: 동
	// 2: 남
	// 3: 서
	static void surveillance1(int[][] map, CCTV cctv){
		switch(cctv.dir){
			case 0:
				surveillanceNorth(map, cctv);
				break;
			case 1:
				surveillanceEast(map, cctv);
				break;
			case 2:
				surveillanceSouth(map, cctv);
				break;
			case 3:
				surveillanceWest(map, cctv);

		}
	}

	// 0: 가로
	// 1: 세로
	static void surveillance2(int[][] map, CCTV cctv){
		switch(cctv.dir){
			case 0:
				surveillanceEast(map, cctv);
				surveillanceWest(map, cctv);
				break;
			case 1:
				surveillanceNorth(map, cctv);
				surveillance1South(map, cctv);
		}
	}

	// 0: 북, 동
	// 1: 동, 남
	// 2: 남, 서
	// 3: 서, 북
	static void surveillance3(int[][] map, CCTV cctv){
		switch(cctv.dir){
			case 0:
				surveillanceNorth(map, cctv);
				surveillanceEast(map, cctv);
				break;
			case 1:
				surveillanceEast(map, cctv);
				surveillance1South(map, cctv);
				break;
			case 2:
				surveillanceNorth(map,cctv);
				surveillanceWest(map, cctv);
				break;
			case 3:
				surveillanceWest(map,cctv);
				surveillanceNorth(map, cctv);
		}
	}

	static void surveillance4(int[][] map, CCTV cctv){

	}

	static void surveillance5(int[][] map, CCTV cctv){

	}

	static boolean isWall(int i){
		if(i == 6)
			return true;
		return false;
	}

	static void surveillanceNorth(int[][] map, CCTV cctv){
		for(int i=1; cctv.x - i>=0; i++){
			if(isWall(map[cctv.x-i][cctv.y]))
				break;
			map[cctv.x-i][cctv.y] = 7;
		}
	}

	static void surveillanceEast(int[][] map, CCTV cctv){
		for(int i=1; cctv.y + i <width; i++){
			if(isWall(map[cctv.x][cctv.y +i])){
				break;
			}
			map[cctv.x][cctv.y + i] = 7;
		}
	}

	static void surveillanceSouth(int[][] map, CCTV cctv){
		for(int i=1; cctv.x +i <height; i++){
			if(isWall(map[cctv.x +i][cctv.y]))
				break;
			map[cctv.x+i][cctv.y] = 7;
		}
	}

	static void surveillanceWest(int[][] map,CCTV cctv){
		for(int i=1; cctv.y - i>=0; i++){
			if(isWall(map[cctv.x][cctv.y -i]))
				break;
			map[cctv.x][cctv.y - i] = 7;
		}
	}
}
