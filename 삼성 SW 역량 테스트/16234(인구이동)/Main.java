import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static boolean isMove;
	
	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		L = Integer.parseInt(temp[1]);
		R = Integer.parseInt(temp[2]);
		map = new int[N][N];		
		
		
		// 값 입력받기
		for(int i=0; i<N; i++) {
			String [] temp2 = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(temp2[j]);
			}
		}
		
		int move=0;
		while(true) {
			visited = new boolean[N][N];
			isMove = false;
			// 순회
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++){
					if(!visited[i][j]) {
						bfs(i,j);
					}
				}
			}
			// 인구이동이 일어난 경우
			if(isMove)
				move++;
			else
				break;
		}
		
		bw.write("" + move);
        bw.flush();
        bw.close();
        br.close();
	}
	
	public static void bfs(int x, int y) {
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		
		Queue<Node> q = new LinkedList<>();
		Queue<Node> tempQ = new LinkedList<>();
		
		visited[x][y] = true;
		q.offer(new Node(x,y));
		tempQ.offer(new Node(x,y));
		
		while(!q.isEmpty()) {
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
					if(!visited[ax][ay]){
						int diff = Math.abs(map[a][b] - map[ax][ay]);
						if(diff >= L && diff <= R) {
							visited[ax][ay] = true;
							q.offer(new Node(ax, ay));
							tempQ.offer(new Node(ax, ay));
						}
					}
				}
			}
		}
		
		int sum = 0;
		int qSize = tempQ.size();
		Node[] nodes = new Node[qSize];
		nodes = tempQ.toArray(nodes);
		
		if(tempQ.size() > 1) {
			while(!tempQ.isEmpty()) {
				Node n = tempQ.poll();
				sum += map[n.a][n.b];
			}
			sum = sum / qSize;
			
			for(int i=0; i<qSize; i++) {
				map[nodes[i].a][nodes[i].b] = sum;
			}
			// 인구 이동이 일어났음
			isMove = true;
		}
	}

	public static boolean isCorrect(int x, int y) {
		boolean flag = true;
		
		if(x < 0 || x >= N || y < 0 || y >= N)
			flag = false;
		
		return flag;
	}
}