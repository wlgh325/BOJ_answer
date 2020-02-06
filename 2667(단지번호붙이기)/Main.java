import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
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
	static int N;
	static int[][] map;
	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		
		// 1: 집이 있는 곳
		// 0: 집이 없는 곳
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split("");
			for(int j=0; j<N; j++){
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
        
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					list.add(dfs(i,j));
				}
			}
		}
		
		Collections.sort(list);
		bw.write("" + list.size());
		bw.newLine();
		for(int i=0; i<list.size(); i++) {
			bw.write("" + list.get(i));
			bw.newLine();
		}
		
        bw.flush();
        bw.close();
        br.close();
	}
	
	public static int dfs(int x, int y) {
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		int count = 0;
		Stack<Node> stack = new Stack<>();
		
		stack.add(new Node(x,y));
		while(!stack.isEmpty()) {
			Node n = stack.pop();
			
			// 방문했는지 확인
			if(map[n.a][n.b] == 1) {
				int ax, ay;
				
				// 방문 처리
				map[n.a][n.b] = 0;
				count++;
				// 4방향 보기
				for(int i=0; i<4; i++) {
					ax = n.a + xdir[i];
					ay = n.b + ydir[i];
					// 범위를 넘지 않는지 확인
					if(isCorrect(ax, ay)) {
						if(map[ax][ay] == 1)
							stack.add(new Node(ax, ay));
					}
				}
			}
		}
		
		return count;
	}
	
	public static boolean isCorrect(int x, int y) {
		boolean flag = true;
		
		if(x < 0 || x >= N || y < 0 || y >= N)
			flag = false;
		
		return flag;
	}
}