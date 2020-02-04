package exam;

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
	static int width, height;
	static int[][] map;
	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		height = Integer.parseInt(br.readLine());
		width = Integer.parseInt(br.readLine());
		map = new int[width][height];
		
		// map 만들기
		for(int i=0; i<height; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<width; j++){
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
        
		
		// 모두 녹아서 없어지는데 걸리는 시간
		// 모두 녹기 한 시간 전 치즈의 개수
		
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