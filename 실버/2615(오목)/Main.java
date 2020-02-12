import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
	static int[][] map;
	static int cnt = 0, row = 1, col = 1;
	static int ul = 0, ur=0, dl=0, dr=0;

	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1: 검은 바둑알
		// 2: 흰 바둑알
		// 0: 알이 놓이지 않음
		map = new int[20][20];
		for(int i=1; i<=19; i++){
			String[] temp = br.readLine().split(" ");
			for(int j=1; j<=19; j++){
				map[i][j] = Integer.parseInt(temp[j-1]);
			}
		}
		
		// 어떤 색이 이겼는가?? 승부가 나지 않았는가?
		// 5개의 알이 연속으로 가로, 세로, 대각선으로 놓인 경우에 이김
		// 6개는 이긴게 아니다.

		// 가로로 이긴 경우 가장 왼쪽알
		// 대각선이나 세로는 가장 위 왼쪽

		for(int i=1; i<=19; i++){
			for(int j=1; j<=19; j++){
				if(map[i][j] == 1){
					search(i,j,1);
				}
				else if(map[i][j] == 2){
					search(i,j,2);
				}
			}
		}

		System.out.println("0");
		br.close();
	}

	public static boolean check(int x, int y, int num){
		if(isValidPosition(x, y)){
			if(map[x][y] != num){
				return false;
			}
		}
		else
			return false;
			

		return true;
	}
	public static boolean isValidPosition(int x, int y){
		if(x < 1 || x > 19 || y < 1 || y > 19)
			return false;
		return true;
	}

	public static void search(int i, int j, int num){
		// 가로에 대해서 확인
		for(int k=1; k<5; k++){
			if(!check(i,j+k, num))
				break;
			else
				row++;
		}

		// 세로 검사
		for(int k=1; k<5; k++){
			if(!check(i+k, j, num))
				break;
			else
				col++;
		}

		// 아래 오른쪽로 대각선 검사
		for(int k=1; k<5; k++){
			if(!check(i+k, j+k, num))
				break;
			else
				dr++;
		}

		// 위로 오른쪽 대각선
		for(int k=1; k<5; k++){
			if(!check(i-k, j+k, num))
				break;
			else
				ur++;
		}

		// 양 쪽 끝으로 더 연결된 것이 있는지 확인
		if(row == 5){
			if(isValidPosition(i, j-1) && map[i][j-1] != num){				
				if(isValidPosition(i, j+5) && map[i][j+5] != num){
					System.out.println(num);
					System.out.println(i + " " + j);
					System.exit(0);
				}
			}
		}
		else if(col == 5){
			if(isValidPosition(i-1, j) && map[i-1][j] != num){				
				if(isValidPosition(i+5, j) && map[i+5][j] != num){
					System.out.println(num);
					System.out.println(i + " " + j);
					System.exit(0);
				}
			}
		}
		else if(dr == 5){
			if(isValidPosition(i-1, j-1) && map[i-1][j-1] != num){				
				if(isValidPosition(i+5, j+5) && map[i+5][j+5] != num){
					System.out.println(num);
					System.out.println(i + " " + j);
					System.exit(0);
				}
			}
		}
		else if(ur == 5){
			if(isValidPosition(i+1, j-1) && map[i+1][j-1] != num){				
				if(isValidPosition(i-5, j+5) && map[i-5][j+5] != num){
					System.out.println(num);
					System.out.println(i + " " + j);
					System.exit(0);
				}
			}
		}

		row = 1;
		col = 1;
		ur = 1;
		dr = 1;
	}
}