import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

class Pos{
	int a;
	int b;
	
	Pos(int a, int b){
		this.a = a;
		this.b = b;
	}
}

class Main{
	static int[][] map;
	static int height, width;
	static ArrayList<Pos> emptyList;
	static ArrayList<Integer> resultList;
	static ArrayList<Pos> virusList;
	static int[][] map2;
	static int max = 0;

	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		map = new int[height][width];
		emptyList = new ArrayList<>();
		resultList = new ArrayList<>();
		virusList = new ArrayList<>();

		// map 만들기
		for(int i=0; i<height; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<width; j++){
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if(n == 2)
					virusList.add(new Pos(i,j));
			}			
		}
		
		comb(0, 3);
		
		bw.write("" + Collections.max(resultList));
        bw.flush();
        bw.close();
        br.close();
	}

	public static void comb(int start, int r){
		if(r==0){
			solve();
			return;
		}
		
		for(int i=start; i<width*height; i++){
			int x = i/width;
			int y = i%width;
			if(map[x][y] == 0) {
				map[x][y] = 1;
				comb(i+1, r-1);
				map[x][y] = 0;	
			}
		}
	}

	public static void solve(){
		map2 = deepCopy(map);

		// spread Virus
		for(Pos pos: virusList){
			int x = pos.a;
			int y = pos.b;

			dfs(x,y);
		}
		max = Math.max(max, calSafetyArea());
	}

	public static void dfs(int x, int y){
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		Stack<Pos> stack = new Stack<>();
		boolean[][] visited = new boolean[height][width];

		stack.push(new Pos(x,y));
		while(!stack.isEmpty()){
			Pos pos = stack.pop();
			int a = pos.a;
			int b = pos.b;
			visited[a][b] = true;
			
			for(int i=0; i<4; i++){
				int dx = a + xdir[i];
				int dy = b + ydir[i];
				if(isValidPosition(dx, dy)){
					if(!visited[dx][dy] && map2[dx][dy] == 0){
						stack.push(new Pos(dx, dy));
						visited[dx][dy] = true;
						map2[dx][dy] = 2;
					}
				}
			}
		}
	}

	public static boolean isValidPosition(int x, int y){
		boolean result = true;

		if(x < 0 || x >= height || y < 0 || y >= width)
			result = false;

		return result;
	}

	public static int calSafetyArea(){
		int area = 0;

		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				if(map2[i][j] == 0)
					area++;
			}
		}
		
		return area;
	}

	public static int[][] deepCopy(int[][] src){
		if(src == null) return null;
		int[][] dest = new int[src.length][src[0].length];
		
		for(int i=0; i<src.length; i++){
			System.arraycopy(src[i], 0, dest[i], 0, src[0].length);
		}
		
		return dest;
	}
}