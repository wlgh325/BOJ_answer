import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	/*
	 * 길이 N인 컨베이어 벨트
	 * 로봇이 어떠 칸에 올라가면 그 칸의 내구도 1 감소
	 * 내구도가 0인 칸에는 로봇 올라서기 불가능
	 * 
	 * 1. 벨트가 회전
	 * 먼저 올라간 로봇 부터 벨트가 회전하는 방향으로 한ㄴ 칸 이동 가능
	 * 앞 칸에 로봇이 없으며 내구도가 남아있어야 함
	 * 
	 * 
	 * 2. 올라가는 위치에 로봇이 없다면 올린다
	 * 내구도가 0인 칸의 개수가 K개 이상이면 종료
	 * 몇 단계가 진행중인지 구해라
	 */
	static int[] belt;	// 내구도
	static int[] robot;	// 로봇 있는지 여부 체크
	static int N, K;
	static int zeroCheck;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String[] temp = br.readLine().split(" ");
    	N = Integer.parseInt(temp[0]);
    	K = Integer.parseInt(temp[1]);
    	
    	belt = new int[2*N+1];
    	robot = new int[N+1];
    	temp = br.readLine().split(" ");
    	for(int i=1; i<2*N+1; i++) {
    		belt[i] = Integer.parseInt(temp[i-1]);
    	}
    	
    	zeroCheck = 0;
    	int stage = 0;
    	while(zeroCheck < K) {
    		rotate();
    		moveRobot();
    		load();
    		
    		stage++;
    	}
    	
    	System.out.println(stage);
    }
    
    public static void rotate() {
    	// 벨트 이동
    	int temp = belt[2*N];
    	for(int i=2*N; i>1; i--) {
    		belt[i] = belt[i-1];
    	}
    	belt[1] = temp;
    	
    	// 로봇 이동
    	temp = robot[N-1];
    	for(int i=N-1; i>1; i--) {
    		robot[i] = robot[i-1]; 
    	}
    	robot[1] = 0;
    	robot[N] = 0;
    }
    
    public static void moveRobot() {
    	// N전에 로봇이 있다면 이동시키고 내림
    	if(robot[N-1] == 1 && belt[N] >= 1) {
    		robot[N-1] = 0;
    		belt[N]--;
    		
    		if(belt[N] == 0) zeroCheck++;
    	}
    	
    	// 먼저 올라간 로봇 부터 오른쪽으로 이동
    	for(int i=N-2; i>0; i--) {
    		// 로봇이 있고, 앞에 로봇이 없고, 내구도가 있는 경우에 이동
    		if(robot[i] == 1 && robot[i+1] == 0 && belt[i+1] >= 1) {
    			robot[i] = 0 ;
    			robot[i+1] = 1;
    			belt[i+1]--;
    			
    			if(belt[i+1] == 0) zeroCheck++;
    		}
    	}
    }
    
    public static void load() {
    	// 올라가는 위치에 로봇이 없고, 내구도 있는 경우 올림ㄴ
    	if(robot[1] == 0 && belt[1] >= 1) {
    		robot[1] = 1;
    		belt[1]--;
    		
    		if(belt[1] == 0) zeroCheck++;
    	}
    }
}