import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


class Point{
	int x;
	int y;

	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Dragon{
	ArrayList<Point> pnts;
	int dir;
	int gen;

	Dragon(int dir, int gen){
		pnts = new ArrayList<>();
		this.dir = dir;
		this.gen = gen;
	}
}

class Main {
	static ArrayList<Dragon> dragons;
	static int[][] map;
	static int height = 101;
	static int width = 101;
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		int dragonNum = Integer.parseInt(input);
		
		dragons = new ArrayList<>();
		map = new int[height][width];

		for(int i=0; i<dragonNum; i++){
			String input2 = br.readLine();
			String[] temp2 = input2.split(" ");
			
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			int dir = Integer.parseInt(temp2[2]);
			int gen = Integer.parseInt(temp2[3]);

			dragons.add(new Dragon(dir, gen));
			dragons.get(i).pnts.add(new Point(x,y));
			// 2,4 -> 4,2
			map[y][x] = 1;
		}

		
		for(int i=0; i<dragonNum; i++){
			initDragon(dragons.get(i));
			MakeDragonCurve(dragons.get(i));
		}

		System.out.println(getBoxNum());
	}

	// 00 01 10 11
	// 01 02 11 12
	static int getBoxNum(){
		int count = 0;
		for(int i=0; i<height-1; i++){
			for(int j=0; j<width-1; j++){
				if(map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1){
					count++;
				}
			}
		}

		return count;
	}

	static void initDragon(Dragon dragon){
		Point pnt = dragon.pnts.get(0);

		switch(dragon.dir){
			case 0:
				dragon.pnts.add(new Point(pnt.x+1, pnt.y));
				map[pnt.y][pnt.x + 1] = 1;
				break;
			case 1:
				dragon.pnts.add(new Point(pnt.x, pnt.y-1));
				map[pnt.y - 1][pnt.x] = 1;
				break;
			case 2:
				dragon.pnts.add(new Point(pnt.x-1, pnt.y));
				map[pnt.y][pnt.x - 1] = 1;
				break;
			case 3:
				dragon.pnts.add(new Point(pnt.x, pnt.y+1));
				map[pnt.y + 1][pnt.x] = 1;
		}
	}

	static void MakeDragonCurve(Dragon dragon){
		// gen번 회전 -> gen세대
		for(int i=0; i<dragon.gen; i++){
			rotate(dragon);
		}
	}

	static void rotate(Dragon dragon){
		ArrayList<Point> temp = dragon.pnts;

		Point endPoint = temp.get(temp.size() - 1);
		ArrayList<Point> tempList = new ArrayList<>();
		
		// 끝점을 기준으로 정렬
		for(int i=0; i<temp.size(); i++){
			int x = temp.get(i).x;
			int y = temp.get(i).y;

			x -= endPoint.x;
			y -= endPoint.y;

			tempList.add(new Point(x,y));
		}		
		dragon.pnts = tempList;
		rotate90(dragon);

		// 원래대로 돌려놓기
		for(int i=0; i<dragon.pnts.size(); i++){
			dragon.pnts.get(i).x += endPoint.x;
			dragon.pnts.get(i).y += endPoint.y;
		}
		paint(dragon);
	}

	static void rotate90(Dragon dragon){
		ArrayList<Point> temp = dragon.pnts;
		int size = temp.size();
		for(int i=size-2; i>=0; i--){
			int x = 0 * temp.get(i).x + -1 * temp.get(i).y;
			int y = 1 * temp.get(i).x + 0 * temp.get(i).y;
			temp.add(new Point(x,y));
		}
		
	}

	static void paint(Dragon dragon){
		ArrayList<Point> arrList = dragon.pnts;

		for(int i=0; i<arrList.size(); i++){
			map[arrList.get(i).y][arrList.get(i).x] = 1;
		}
	}
}
