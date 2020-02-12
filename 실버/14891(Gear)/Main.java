import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Command{
	int dir;
	int num;

	Command(int dir, int num){
		this.dir = dir;
		this.num = num;
	}
}
class Main {
	static int[][] gears;	// 톱니바퀴
	static int[][] command;

	/*
	1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
	2번 톱니바퀴의 12시방향이 N극이면 0점,S극이면 2점
	3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
	4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
	*/

	// 12시 방향부터 시계방향

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int rotateNum;
		gears = new int[4][8];

		for(int i=0; i<4; i++){
			String input = br.readLine();
			String[] temp = input.split("");
			for(int j=0; j<8; j++){
				gears[i][j] = Integer.parseInt(temp[j]);
			}
		}

		rotateNum = Integer.parseInt(br.readLine());
		command = new int[rotateNum][2];
		for(int i=0; i<rotateNum; i++){
			String input2 = br.readLine();
			String[] temp2 = input2.split(" ");
			command[i][0] = Integer.parseInt(temp2[0]);
			command[i][1] = Integer.parseInt(temp2[1]);
		}

		for(int i=0; i<rotateNum; i++){
			rotate(command[i][0], command[i][1]);
		}

		int score = calScore();

		System.out.println(score);
	}
	
	static int calScore(){
		int score =1;
		int sum = 0;
		for(int i=0; i<4; i++){
			if(gears[i][0] == 1){
				sum += score;
			}
			score *= 2;
		}

		return sum;
	}

	static void rotate(int i, int dir){
		i -= 1;
		ArrayList<Command> arrList = new ArrayList<>();

		// 명령에 해당하는 톱니 바퀴 회전
		insertCommand(dir, arrList, i);

		// 해당 톱니바퀴 회전으로 인해 회전해야할 톱니바퀴 골라서 회전시키기
		if(i == 0){
			if(gears[i][2] != gears[i+1][6]){
				insertCommand(dir*-1, arrList, i+1);
				if(gears[i+1][2] != gears[i+2][6]){
					insertCommand(dir, arrList, i+2);
					if(gears[i+2][2] != gears[i+3][6]){
						insertCommand(dir*-1, arrList, i+3);
					}
				}
			}
		}
		else if(i==1){
			if(gears[i][6] != gears[i-1][2]){
				insertCommand(dir*-1, arrList, i-1);
			}
			
			if(gears[i][2] != gears[i+1][6]){
				insertCommand(dir*-1, arrList, i+1);
				if(gears[i+1][2] != gears[i+2][6]){
					insertCommand(dir, arrList, i+2);
				}
			}
		}
		else if(i==2){
			if(gears[i][2] != gears[i+1][6]){
				insertCommand(dir*-1, arrList, i+1);
			}
			if(gears[i][6] != gears[i-1][2]){
				insertCommand(dir*-1, arrList, i-1);
				if(gears[i-1][6] != gears[i-2][2]){					
					insertCommand(dir, arrList, i-2);
				}
			}
		}
		else if(i==3){
			if(gears[i][6] != gears[i-1][2]){
				insertCommand(dir*-1, arrList, i-1);
				if(gears[i-1][6] != gears[i-2][2]){
					insertCommand(dir, arrList, i-2);
					if(gears[i-2][6] != gears[i-3][2]){
						insertCommand(dir*-1, arrList, i-3);
					}
				}
			}
		}

		for(int j=0; j<arrList.size(); j++){
			if(arrList.get(j).dir == 1)
				rotateClockwise(arrList.get(j).num);
			else
				rotateCounterClockwise(arrList.get(j).num);
		}
	}

	static void insertCommand(int dir, ArrayList<Command> arrList, int i){
		Command command = new Command(dir, i);
		arrList.add(command);
	}

	// 시계방향 : 1
	// 반시계방향 : 0

	// 3번째 극이 물림
	// 10101111 시계회전 -> 11010111
	static void rotateClockwise(int i){
		int[] temp = new int[8];
		System.arraycopy(gears[i], 0, temp, 0, gears[i].length);
		
		gears[i][0] = temp[7];
		for(int j=0; j<7; j++){
			gears[i][j+1] = temp[j];
		}
		
	}

	//	01011111
	static void rotateCounterClockwise(int i){
		int[] temp = new int[8];
		System.arraycopy(gears[i], 0, temp, 0, gears[i].length);

		gears[i][7] = temp[0];
		for(int j=0; j<7; j++)
			gears[i][j] = temp[j+1];
	}
}
