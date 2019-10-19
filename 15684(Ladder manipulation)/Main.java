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
	static ArrayList<Ladder> horizontalInfo;
	static ArrayList<Ladder> enableAddLadder;
	static int[][] ladderMap;
	static int width;
	static int horizontalNum;
	static int height;
	static int manipulateNum = -1;

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

		horizontalInfo = new ArrayList<>();
		enableAddLadder = new ArrayList<>();
		ladderMap = new int[height][width];
		
		if(horizontalNum == 0){
			System.out.println(0);
			return;
		}

		// 사다리 정보 입력 받기
		for(int i=0; i<horizontalNum; i++){
			String input2 = br.readLine();
			String[] temp2 = input2.split(" ");
			int a = Integer.parseInt(temp2[0]) - 1;
			int b = Integer.parseInt(temp2[1]) - 1;

			// b번 사다리
			// b+1번 사다리 잇기
			ladderMap[a][b] = 1;
			ladderMap[a][b+1] = 1;

			horizontalInfo.add(new Ladder(a,b));
		}
		
		checkEnableAdd();
		
		int enableAddLadderNum = enableAddLadder.size();
		visited = new boolean[enableAddLadderNum];

		for(int i=1; i<=enableAddLadderNum; i++){

			comb(horizontalInfo, visited, 0, enableAddLadderNum, i);
			//System.out.println(i);
		}

		System.out.println(manipulateNum);
	}

	static void comb(ArrayList<Ladder> horizontalInfo, boolean[] visited, int start, int n, int r){
		if(r==0){
			manipulate(horizontalInfo, visited, n);
		}
		else{
			for(int i=start; i<n; i++){
				visited[i] = true;
				comb(horizontalInfo, visited, i+1, n, r-1);
				visited[i] = false;
			}
			
		}
	}

	static void manipulate(ArrayList<Ladder> horizontalInfo, boolean[] visited, int n){
		ArrayList<Ladder> copyHorizontalInfo = deepCopy(horizontalInfo);
		ArrayList<Ladder> tempLadder = new ArrayList<>();
		int count = 0;

		// 추가할 사다리 골라내기
		for(int i=0; i<n; i++){
			if(visited[i]){
				Ladder ladder = enableAddLadder.get(i);
				tempLadder.add(ladder);
			}
		}

		// 추가할 사다리가 연속되는지 확인
		for(int i=0; i< tempLadder.size()-1; i++){
			Ladder ladder = tempLadder.get(i);
			Ladder ladder2 = tempLadder.get(i+1);

			if(ladder.row != ladder2.row){
				copyHorizontalInfo.add(new Ladder(ladder.row, ladder.left_col));
			}
			else{
				if(ladder.right_col != ladder2.left_col){	
					copyHorizontalInfo.add(new Ladder(ladder.row, ladder.left_col));
				}
				else
					return;
			}
			
		}

		Ladder ladder = tempLadder.get(tempLadder.size()-1);
		copyHorizontalInfo.add(new Ladder(ladder.row, ladder.left_col));

		// 사다리 타고 내려가기
		for(int i=0; i<width; i++){
			int col = gotoBottom(i, copyHorizontalInfo);
			
			// 같은 번호로 내려가는 경우
			if(col == i){
				count++;
			}
		}
		
		if(count == width){
			if(tempLadder.size() <=3 )
				manipulateNum = tempLadder.size();
		}
	}

	static int gotoBottom(int col, ArrayList<Ladder> copyHorizontalInfo){
		for(int i=0; i<height; i++){
			if(containLadder(copyHorizontalInfo, new Ladder(i, col))){
				col++;
			}
			else if(containLadder(copyHorizontalInfo, new Ladder(i, col-1))){
				col--;
			}
		}
		
		return col;
	}

	static boolean containLadder(ArrayList<Ladder> copyHorizontalInfo, Ladder compareLadder){
		boolean contain = false;

		for(int i=0; i<copyHorizontalInfo.size(); i++){
			Ladder temp = copyHorizontalInfo.get(i);
			if(temp.left_col == compareLadder.left_col && temp.row == compareLadder.row)
				return true;
		}
		return contain;
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
		for(int i=0; i<height; i++){
			for(int j=0; j<width-1; j++){
				if(ladderMap[i][j] != 1 && ladderMap[i][j+1] != 1)
					enableAddLadder.add(new Ladder(i, j));
			}
		}
	}

}
