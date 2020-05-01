import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static int[][] cube;
	static final int WHITE = 0;
	static final int YELLOW = 1;
	static final int RED = 2;
	static final int ORANGE = 3;
	static final int GREEN = 4;
	static final int BLUE = 5;
	static final int EMPTY = 6;
	static final char[] ch = {'w','y','r','o','g','b'};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++){
			int n = Integer.parseInt(br.readLine());	// 돌린 횟수
			String[] temp = br.readLine().split(" ");	// 돌린 방법
			cube = init();
			for(int i=0; i<n; i++){
				rotate(temp[i]);
				print();
			}
		}
	}

	public static int[][] init(){
		int[][] cube = { {EMPTY, EMPTY, EMPTY, ORANGE, ORANGE, ORANGE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
						{EMPTY, EMPTY, EMPTY, ORANGE, ORANGE, ORANGE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
						{EMPTY, EMPTY, EMPTY, ORANGE, ORANGE, ORANGE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
						{GREEN, GREEN, GREEN, WHITE, WHITE, WHITE, BLUE, BLUE, BLUE, YELLOW, YELLOW, YELLOW},
						{GREEN, GREEN, GREEN, WHITE, WHITE, WHITE, BLUE, BLUE, BLUE, YELLOW, YELLOW, YELLOW},
						{GREEN, GREEN, GREEN, WHITE, WHITE, WHITE, BLUE, BLUE, BLUE, YELLOW, YELLOW, YELLOW},
						{EMPTY, EMPTY, EMPTY, RED, RED, RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
						{EMPTY, EMPTY, EMPTY, RED, RED, RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
						{EMPTY, EMPTY, EMPTY, RED, RED, RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY}};
		return cube;
	}

	public static void rotate(String cmd){
		switch(cmd){
			case "U+":
				rotate_uc();
				break;
			case "U-":
				rotate_uc(); rotate_uc(); rotate_uc();
				break;
			case "D+":
				rotateClockwise(3, 6, 9, 12);
				rotate_dc(); rotate_dc(); rotate_dc();
				break;
			case "D-":
				rotateClockwise(3, 6, 9, 12); rotateClockwise(3, 6, 9, 12); rotateClockwise(3, 6, 9, 12);
				rotate_dc();
				break;
			case "F+":
				rotateClockwise(6, 9, 3, 6);
				rshift(5);
				break;
			case "F-":
				rotateClockwise(6, 9, 3, 6); rotateClockwise(6, 9, 3, 6); rotateClockwise(6, 9, 3, 6);
				rshift(5); rshift(5); rshift(5);
				break;
			case "B+":
				rotateClockwise(0, 3, 3, 6);
				rshift(3); rshift(3); rshift(3);
				break;
			case "B-":
				rotateClockwise(0, 3, 3, 6); rotateClockwise(0, 3, 3, 6); rotateClockwise(0, 3, 3, 6);			
				rshift(3);
				break;
			case "L+":
				rotateClockwise(3, 6, 0, 3);
				cshift(3);
				break;
			case "L-":
				rotateClockwise(3, 6, 0, 3); rotateClockwise(3, 6, 0, 3); rotateClockwise(3, 6, 0, 3);
				cshift(3); cshift(3); cshift(3);
				break;
			case "R+":
				rotateClockwise(3, 6, 6, 9);
				cshift2(5); cshift2(5); cshift2(5);
				break;
			case "R-":
				rotateClockwise(3, 6, 6, 9); rotateClockwise(3, 6, 6, 9); rotateClockwise(3, 6, 6, 9);
				cshift2(5);
				break;
		}
	}

	public static void rotate_dc(){
		int temp1 = cube[0][3]; int temp2 = cube[0][4];	int temp3 = cube[0][5];
		cube[0][3] = cube[5][0]; cube[0][4] = cube[4][0]; cube[0][5] = cube[3][0];
		
		int temp4 = cube[3][8]; int temp5 = cube[4][8]; int temp6 = cube[5][8];
		cube[3][8] = temp1; cube[4][8] = temp2; cube[5][8] = temp3;

		int temp7 = cube[8][3]; int temp8 = cube[8][4]; int temp9 = cube[8][5];
		cube[8][3] = temp6; cube[8][4] = temp5; cube[8][5] = temp4;

		cube[3][0] = temp7;
		cube[4][0] = temp8;
		cube[5][0] = temp9;
	}

	public static void rotate_uc(){
		// copy
		int[][] temp = new int[5][5];
		for(int i=2; i<7; i++){
			for(int j=2; j<7; j++){
				temp[i-2][j-2] = cube[i][j];
			}
		}

		// rotate
		for(int i=2; i<7; i++){
			for(int j=2; j<7; j++){
				cube[i][j] = temp[6-j][i-2];
			}
		}
	}
	
	public static void cshift(int col){
		// 아래로 하나씩 밀기
		for(int j=0; j<3; j++){
			Queue<Integer> q = new LinkedList<>();

			for(int i=0; i<9; i++)
				q.offer(cube[i][col]);
			q.offer(cube[5][11]);
			q.offer(cube[4][11]);
			q.offer(cube[3][11]);

			
			for(int i=1; i<9; i++)
				cube[i][col] = q.poll();
			
			cube[5][11] = q.poll();
			cube[4][11] = q.poll();
			cube[3][11] = q.poll();
			cube[0][col] = q.poll();
		}
	}

	public static void cshift2(int col){
		// 아래로 하나씩 밀기
		for(int j=0; j<3; j++){
			Queue<Integer> q = new LinkedList<>();

			for(int i=0; i<9; i++)
				q.offer(cube[i][col]);
			q.offer(cube[5][9]);
			q.offer(cube[4][9]);
			q.offer(cube[3][9]);

			
			for(int i=1; i<9; i++)
				cube[i][col] = q.poll();
			
			cube[5][9] = q.poll();
			cube[4][9] = q.poll();
			cube[3][9] = q.poll();
			cube[0][col] = q.poll();
		}
	}

	public static void rshift(int row){
		// 아래로 하나씩 밀기
		for(int j=0; j<3; j++){
			Queue<Integer> q = new LinkedList<>();
			for(int i=0; i<12; i++)
				q.offer(cube[row][i]);

			for(int i=1; i<12; i++)
				cube[row][i] = q.poll();
			cube[row][0] = q.poll();
		}
	}

	public static void rotateClockwise(int rstart, int rend, int cstart, int cend){
		// copy
		int[][] temp = new int[3][3];
		for(int i=rstart; i<rend; i++){
			for(int j=cstart; j<cend; j++){
				temp[i-rstart][j-cstart] = cube[i][j];
			}
		}

		// rotate
		for(int i=rstart; i<rend; i++){
			for(int j=cstart; j<cend; j++){
				cube[i][j] = temp[cend-1-j][i-rstart];
			}
		}
	}

	public static void print(){
		for(int i=3; i<6; i++){
			for(int j=3; j<6; j++){
				System.out.print(ch[cube[i][j]]);
			}
			System.out.println();
		}
	}
}