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
	int seaNum;
	
	Node(int a, int b){
		this.a = a;
		this.b = b;
		this.seaNum = 0;
	}
	
	Node(int a, int b, int seaNum){
		this.a = a;
		this.b = b;
		this.seaNum = seaNum;
	}
}

class Main{
	static int height, width;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp = br.readLine().split(" ");
		height = Integer.parseInt(temp[0]);
		width = Integer.parseInt(temp[1]);
		map = new int[height][width];
		
		for(int i=0; i<height; i++) {
			String [] temp2 = br.readLine().split(" ");
			for(int j=0; j<width; j++) {
				map[i][j] = Integer.parseInt(temp2[j]);
			}
		}
		
		int area;
		int time = 0;
			
		while(true){
			area = 0;
			visited = new boolean[height][width];
			
			// 녹이기
			for(int i=0; i<height; i++){
				for(int j=0; j<width; j++){
					if(map[i][j] != 0 && !visited[i][j]) {
						dfs(i,j);
						area++;
					}
				}
			}
							
			if(area == 0) {
				bw.write("" + 0);	
				break;
			}
			if(area >= 2) {
				bw.write("" + time);
				break;
			}
			time++;
		}
		
        bw.flush();
        bw.close();
        br.close();
	}
	
	public static void dfs(int x, int y) {
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		Stack<Node> stack = new Stack<>();
		ArrayList<Node> list = new ArrayList<>();
		
		stack.add(new Node(x,y));
		while(!stack.isEmpty()) {
			Node n = stack.pop();
			int a = n.a;
			int b = n.b;
			int ax, ay;
			int seaNum = 0;
			
			visited[a][b] = true;
			
			// 4방향 보기
			for(int i=0; i<4; i++) {
				ax = n.a + xdir[i];
				ay = n.b + ydir[i];
				// 범위를 넘지 않는지 확인
				if(isCorrect(ax, ay)) {
					// 빙산은 stack에 넣고 탐색
					if(map[ax][ay] != 0){
						if(!visited[ax][ay]){
							stack.add(new Node(ax, ay));
						}
					}
					else {
						// 빙산에 연결된 바다 수 check
						seaNum++;
					}
				}
			}
			
			int k = map[a][b] - seaNum > 0 ? map[a][b] - seaNum : 0;
			list.add(new Node(a, b, k));
			
		}
		
		for(Node n: list) {
			map[n.a][n.b] = n.seaNum;
		}
	}

	public static boolean isCorrect(int x, int y) {
		boolean flag = true;
		
		if(x < 0 || x >= height || y < 0 || y >= width)
			flag = false;
		
		return flag;
	}
}