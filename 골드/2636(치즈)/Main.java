import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

class Node{
	int a;
	int b;
	Node(int a, int b){
		this.a = a;
		this.b = b;
	}
}

class Main{
	static ArrayList<Integer> list;
	static int width, height;
	static int[][] map;
	static final int AIR = 3;
	static final int PREAIR = 2;
	static final int CHEESE = 1;
	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		String[] numb = br.readLine().split(" ");
		int remainCheese = 0;

		height = Integer.parseInt(numb[0]);
		width = Integer.parseInt(numb[1]);
		map = new int[height][width];
		list = new ArrayList<>();
		
		// map 만들기
		for(int i=0; i<height; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<width; j++){
				map[i][j] = Integer.parseInt(temp[j]);
				if(map[i][j] == 1)
					remainCheese++;
			}			
		}
        
		list.add(remainCheese);
		int time = 0;
		while(remainCheese != 0) {
			// 바깥 벽이랑 닿은 것은 공기로 판별
			// 1: 치즈
			// 2: 공기랑 접촉하기전
			// 3: 공기
			dfs();
			
			// 곧 녹을 치즈 표시
			dfs2();
			remainCheese = melting();
			list.add(remainCheese);
			time++;
		}
		
		// 모두 녹아서 없어지는데 걸리는 시간
		// 모두 녹기 한 시간 전 치즈의 개수
		bw.write(time + "");
		bw.newLine();
		
		bw.write(list.get(list.size() - 2) + "");
		bw.newLine();
		
        bw.flush();
        bw.close();
        br.close();
	}
	
	public static int melting() {
		int cheese = 0;
		for(int i=0; i< height; i++) {
			for(int j=0; j<width; j++) {
				if(map[i][j] == PREAIR)
					map[i][j] = AIR;
				else if(map[i][j] == 1)
					cheese++;
					
			}
		}
		return cheese;
	}
	
	public static void dfs() {
		boolean[][] visited = new boolean[height][width];
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		
		Stack<Node> stack = new Stack<>();
		
		stack.add(new Node(0,0));
		while(!stack.isEmpty()) {
			Node n = stack.pop();
			int a = n.a;
			int b = n.b;
			
			int ax, ay;
			
			// 4방향 보기
			for(int i=0; i<4; i++) {
				ax = a + xdir[i];
				ay = b + ydir[i];
				// 범위를 넘지 않는지 확인
				if(isCorrect(ax, ay)) {
					// 방문한 적 없고
					// 치즈가 아니거나 공기인 경우
					if(!visited[ax][ay] && (map[ax][ay] == 0 || map[ax][ay] == AIR)) {
						map[ax][ay] = AIR;
						stack.push(new Node(ax, ay));
						visited[ax][ay] = true;
					}
				}
			}
		}
	}
	
	public static void dfs2() {
		boolean[][] visited = new boolean[height][width];
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		
		Stack<Node> stack = new Stack<>();
		
		stack.add(new Node(0,0));
		while(!stack.isEmpty()) {
			Node n = stack.pop();
			int a = n.a;
			int b = n.b;
			
			// 4방향 보기
			for(int i=0; i<4; i++) {
				int ax = n.a + xdir[i];
				int ay = n.b + ydir[i];
				// 범위를 넘지 않는지 확인
				if(isCorrect(ax, ay)) {
					if(!visited[ax][ay]) {
						if(map[a][b] == AIR) {
							stack.push(new Node(ax,ay));
							visited[ax][ay] = true;
							if(map[ax][ay] == CHEESE)
								map[ax][ay] = PREAIR;
						}
					}
				}
			}
		}
	}
	
	public static boolean isCorrect(int x, int y) {
		boolean flag = true;
		
		if(x < 0 || x >= height || y < 0 || y >= width)
			flag = false;
		
		return flag;
	}
}