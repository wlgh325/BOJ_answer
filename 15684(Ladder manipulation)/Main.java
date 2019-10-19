import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Ladder{
	int left_col;
	int right_col;
	int row;

	Ladder(int row, int col){
		this.left_col = col;
		this.right_col = col+1;
		this.row = row;
	}
}

class Main {
	static ArrayList<Ladder> enableAddLadder;
	static int[][] ladderMap;
	static int width;
	static int horizontalNum;
	static int height;
	static boolean flag = false;
	static int rr=0;

	// i번 세로선의 결과가 i가 나오게 해야함
	// 추가해야하는 가로선 개수의 최솟값
	// 만약 3이 넘으면 -1 출력
	// 가장 왼쪽에 있는 세로선 : 1번
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] temp = input.split(" ");
		boolean[] visited;

		width = Integer.parseInt(temp[0]);
		horizontalNum = Integer.parseInt(temp[1]);
		height = Integer.parseInt(temp[2]);

		enableAddLadder = new ArrayList<>();
		ladderMap = new int[height+1][width+1];
		
		// 가로선의 개수가 0일 경우 바로 종료
		if(horizontalNum == 0){
			System.out.println(0);
			return;
		}

		// 사다리 정보 입력 받기
		for(int i=0; i<horizontalNum; i++){
			String input2 = br.readLine();
			String[] temp2 = input2.split(" ");
			int a = Integer.parseInt(temp2[0]);
			int b = Integer.parseInt(temp2[1]);

			// b번 사다리
			// b+1번 사다리 잇기
			// 사다리 왼쪽은 1, 오른쪽은 -1
			ladderMap[a][b] = 1;
			ladderMap[a][b+1] = -1;
		}
		
		checkEnableAdd();
		
		int enableAddLadderNum = enableAddLadder.size();
		visited = new boolean[enableAddLadderNum];

		int count=0;
		// 사다리 타고 내려가기 (사다리 추가 안하는 경우)
		for(int i=1; i<=width; i++){
			int[][] copyLadderMap = new int[height+1][width+1];

			for(int j=1; j<=height; j++){
				System.arraycopy(ladderMap[j], 0, copyLadderMap[j], 0, ladderMap[j].length);
			}
			
			int col = gotoBottom(copyLadderMap, i);
			
			// 같은 번호로 내려가는 경우
			if(col == i){
				count++;
			}
		}
		
		if(count == width){
			System.out.println(0);
			return;
		}

		// 사다리 추가해서 하는 경우
		for(int i=1; i<=3; i++){
			rr=i;
			comb(visited, 0, enableAddLadderNum, i);
		}

		if(!flag)
			System.out.println(-1);
	}

	static void comb(boolean[] visited, int start, int n, int r){
		if(r==0){
			manipulate(visited, n);
		}
		else{
			for(int i=start; i<n; i++){
				visited[i] = true;
				comb(visited, i+1, n, r-1);
				visited[i] = false;
			}
			
		}
	}

	static void manipulate(boolean[] visited, int n){
		int[][] copyLadderMap = new int[height+1][width+1];

		for(int j=1; j<=height; j++){
			System.arraycopy(ladderMap[j], 0, copyLadderMap[j], 0, ladderMap[j].length);
		}

		ArrayList<Ladder> tempLadder = new ArrayList<>();
		int count = 0;

		// 추가할 사다리 골라내기
		for(int i=0; i<n; i++){
			if(visited[i]){
				Ladder ladder = enableAddLadder.get(i);
				tempLadder.add(ladder);
				if(tempLadder.size() == rr)
					break;
			}
		}

		// 놓을 사다리가 2개 이상이면 사다리가 겹치는지 확인
		if(tempLadder.size() >= 2){
			// 추가할 사다리가 연속되는지 확인
			for(int i=0; i< tempLadder.size()-1; i++){
				Ladder ladder = tempLadder.get(i);
				Ladder ladder2 = tempLadder.get(i+1);

				if(ladder.row != ladder2.row){
					continue;
				}
				else{
					if(ladder.right_col != ladder2.left_col)
						continue;
					else
						return;
				}
			}
		}
		
		// 사다리 추가하여 Map 그리기
		for(int i=0; i<tempLadder.size(); i++){
			Ladder t = tempLadder.get(i);
			copyLadderMap[t.row][t.left_col] = 1;
			copyLadderMap[t.row][t.right_col] = -1;
		}
		
		// 사다리 타고 내려가기
		for(int i=1; i<=width; i++){
			int col = gotoBottom(copyLadderMap, i);
			
			// 같은 번호로 내려가는 경우
			if(col == i){
				count++;
			}
			else
				return;
		}
		
		if(count == width){
			System.out.println(tempLadder.size());
			System.exit(0);
		}
	}

	static int gotoBottom(int[][] ladderMap, int col){
		for(int i=1; i<=height; i++){
			if(ladderMap[i][col] == 0){
				continue;
			}
			else if(ladderMap[i][col] == 1){
				col++;
			}
			else if(ladderMap[i][col] == -1){
				col--;
			}
		}
			
		return col;
	}

	static ArrayList<Ladder> deepCopy(ArrayList<Ladder> source){
		ArrayList<Ladder> destHorizontalInfo = new ArrayList<>();

		for(int i=0; i<source.size(); i++){
			Ladder ladder = source.get(i);
			destHorizontalInfo.add(new Ladder(ladder.row, ladder.left_col));
		}
		
		return destHorizontalInfo;
	}

	static void checkEnableAdd(){
		for(int i=1; i<=height; i++){
			for(int j=1; j<width; j++){
				if(j==1){
					if(ladderMap[i][j] != 1 && ladderMap[i][j+1] != 1)
						enableAddLadder.add(new Ladder(i, 1));
				}
				else{
					if (ladderMap[i][j-1] != 1 && ladderMap[i][j] != 1 && ladderMap[i][j+1] != 1)
						enableAddLadder.add(new Ladder(i, j));
				}
				
			}
		}

	}

}
