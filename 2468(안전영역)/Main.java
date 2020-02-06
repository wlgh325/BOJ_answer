import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	static int N;
	static int[][] map;

	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int max = 0;
		// map 만들기
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<N; j++){
				int n = Integer.parseInt(temp[j]);
				map[i][j] = n;
				if(max <= n)
					max = n;
			}			
		}
		
		int count;
		int areaMax = 1;
		for(int i=0; i<=max; i++) {
			boolean[][] visited = new boolean[N][N];
			count = 0;
			search(i);
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					if(!visited[j][k] && map[j][k] != 0){
						dfs(visited, j, k);
						count++;
					}
				}
			}
			if(areaMax <= count)
				areaMax = count;
		}
		
		bw.write("" + areaMax);
        bw.flush();
        bw.close();
        br.close();
	}

	public static void search(int height) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] <= height)
					map[i][j] = 0;
			}
		}
	}
	
	public static void dfs(boolean[][] visited, int x, int y) {
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		Stack<Node> stack = new Stack<>();
		
		stack.add(new Node(x,y));
		
		while(!stack.isEmpty()) {
			Node n = stack.pop();
			int a = n.a;
			int b = n.b;
			visited[a][b] = true;
			
			int ax, ay;
			// 4방향 보기
			for(int i=0; i<4; i++) {
				ax = a + xdir[i];
				ay = b + ydir[i];
				// 범위를 넘지 않는지 확인
				if(isCorrect(ax, ay)) {
					// 방문한 적 없고
					if(!visited[ax][ay]) {
						if(map[ax][ay] != 0) {
							stack.push(new Node(ax, ay));
							visited[ax][ay] = true;	
						}
					}
				}
			}
		}
	}
	
	public static boolean isCorrect(int x, int y) {
		boolean flag = true;
		
		if(x < 0 || x >= N || y < 0 || y >= N)
			flag = false;
		
		return flag;
	}
}