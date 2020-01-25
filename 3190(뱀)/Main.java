import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Command {
	int sec;
	String dir;
	
	// L : Left 90
	// D : Right 90
	Command(int sec, String dir){
		this.sec = sec;
		this.dir = dir;
	}
}

class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int n;
	static int apple;
	static int change;
	
	static int[][] map;
	
	static Queue<Command> queue;
	static String dir;
	static Queue<Point> snake;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		apple = Integer.parseInt(br.readLine());
		dir = "R";
		snake = new LinkedList<>();
		queue = new LinkedList<>();

		// 뱀 초기 위치 set
		snake.offer(new Point(0,0));
		map[0][0] = 1;
		// 사과 위치 정보
		for(int i=0; i<apple; i++){
			String[] temp = br.readLine().split(" ");
			int row = Integer.parseInt(temp[0]) - 1;
			int col = Integer.parseInt(temp[1]) - 1;

			map[row][col] = -1;
		}
		
		change = Integer.parseInt(br.readLine());

		// 방향 변환 정보
		for(int i=0; i<change; i++){
			String[] temp = br.readLine().split(" ");
			queue.offer(new Command(Integer.parseInt(temp[0]), temp[1] ) );
		}

		int row = 0;
		int col = 0;
		int time = 0;

		// 꼬리 위치 정보
		int rearX = 0;
		int rearY = 0;
		while(true){

			// 방향에 따른 진진
			switch(dir){
				case "U":
					row--;
					break;
				case "D":
					row++;
					break;
				case "L":
					col--;
					break;
				case "R":
					col++;
			}
			time++;

			if((row < n && row >= 0 ) && (col < n && col >= 0)){
				// 자기 몸과 부딪히지 않았는지 검사
				if(map[row][col] == 1)
					break;

				// 사과가 있으면
				if(map[row][col] == -1){
					map[row][col] = 1;	// 전진
					snake.add(new Point(row, col));
				}
				else{
					map[row][col] = 1;	// 전진
					map[rearX][rearY] = 0;	// 꼬리 없애기

					snake.poll();
					snake.offer(new Point(row, col));

					// 1보다 크면 머리 != 꼬리
					if(snake.size() > 1){
						rearX = snake.peek().x;
						rearY = snake.peek().y;
					}
					else{
						rearX = row;
						rearY = col;
					}
				
				}

				if(!queue.isEmpty()){
					if(queue.peek().sec == time){
						String move = queue.poll().dir;
	
						// 현재 방향에 따른 방향전환
						switch(dir){
							case "U":
								if(move.equals("L"))
									dir = "L";
								else
									dir = "R";
								break;
							case "D":
								if(move.equals("L"))
									dir = "R";
								else
									dir = "L";
								break;
							case "L":
								if(move.equals("L"))
									dir = "D";
								else
									dir = "U";
								break;
							case "R":
								if(move.equals("L"))
									dir = "U";
								else
									dir = "D";
							
						}
					}
				}
				
			}
			else
				break;
		}

		bw.write(time + "");
		bw.flush();
		bw.close();
		br.close();
	}
}

