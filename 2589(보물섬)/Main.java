import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Node{
	int a;
	int b;
	
	Node(int a, int b){
		this.a = a;
		this.b = b;
	}
}

class Main{
	static int height, width;
	static String[][] map;
	static boolean[][] visited;
	
	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp = br.readLine().split(" ");
		height = Integer.parseInt(temp[0]);
		width = Integer.parseInt(temp[1]);
		map = new String[height][width];
		
		ArrayList<Integer> list = new ArrayList<>();
		
		// 갑 입력받기
		for(int i=0; i<height; i++) {
			String [] temp2 = br.readLine().split("");
			for(int j=0; j<width; j++) {
				map[i][j] = temp2[j];
			}
		}
		
		// 순회
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				if(!map[i][j].equals("W")) {
					list.add(bfs(i,j));
				}
			}
		}

		Collections.sort(list);
		bw.write(list.get(list.size()-1) + "");
        bw.flush();
        bw.close();
        br.close();
	}
	
	public static int bfs(int x, int y) {
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		int level = 0;
		Queue<Node> q = new LinkedList<>();
		visited = new boolean[height][width];
		
		visited[x][y] = true;
		q.add(new Node(x,y));
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i=1; i<=qSize; i++) {
				Node n = q.poll();
				int a = n.a;
				int b = n.b;
				int ax, ay;
				
				// 4방향 보기
				for(int j=0; j<4; j++) {
					ax = n.a + xdir[j];
					ay = n.b + ydir[j];
					// 범위를 넘지 않는지 확인
					if(isCorrect(ax, ay)) {
						// 빙산은 stack에 넣고 탐색
						if(!map[ax][ay].equals("W")){
							if(!visited[ax][ay]){
								visited[ax][ay] = true;
								q.offer(new Node(ax, ay));
							}
						}
					}
				}
				
			}
			level++;
		}
		return level-1;
	}

	public static boolean isCorrect(int x, int y) {
		boolean flag = true;
		
		if(x < 0 || x >= height || y < 0 || y >= width)
			flag = false;
		
		return flag;
	}
}