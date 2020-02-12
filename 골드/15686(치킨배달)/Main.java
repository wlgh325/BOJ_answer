import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

class Pos{
	int x;
	int y;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Main{	
	static int N, M;
	static int[][] map;
	static int rr;
	static ArrayList<Integer> resultList;
	static boolean[] visited;
	static ArrayList<Pos> chickenList;
	static ArrayList<Pos> houseList;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		map = new int[N][N];
		
		chickenList = new ArrayList<>();
		houseList = new ArrayList<>();
		resultList = new ArrayList<>();

		int chickenHouseNum = 0;
		for(int i=0; i<N; i++){
			String[] temp2 = br.readLine().split(" ");
			for(int j=0; j<N; j++){
				int n = Integer.parseInt(temp2[j]);
				map[i][j] = n;
				if(n == 2){
					chickenHouseNum++;
					chickenList.add(new Pos(i,j));
				}
				else if(n == 1)
					houseList.add(new Pos(i,j));
			}
		}
		visited = new boolean[chickenHouseNum];
		// 전체 치킨집 중 M개를 뽑는 조합을 구함
		comb(0,chickenHouseNum, M);
		
		bw.write(Collections.min(resultList) + "");
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void comb(int start, int n, int r) {
		if(r==0) {
			addChickenHouse();
			return;
		}
		for(int i=start; i<n; i++) {
			visited[i] = true;
			comb(i+1, n, r-1);
			visited[i] = false;
		}
	}

	public static void addChickenHouse() {
		//
		ArrayList<Pos> list = new ArrayList<>();
		int check = 0;
		for(int i=0; i<chickenList.size(); i++){
			if(visited[i]){
				list.add(new Pos(chickenList.get(i).x, chickenList.get(i).y));
				check++;
			}
			
			if(check == M)
				break;
		}

		solve(list);
	}

	public static void solve(ArrayList<Pos> list){
		int cityChickenDist = 0;
		for(int i=0; i<houseList.size(); i++){
			Pos house = houseList.get(i);
			int chickenDist = 99999;
			for(int j=0; j<list.size(); j++){
				int dist = calDistance(house, list.get(j));
				if(dist <= chickenDist)
					chickenDist = dist;
			}
			cityChickenDist += chickenDist;
		}
		resultList.add(cityChickenDist);
	}

	public static int calDistance(Pos a, Pos b){
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}