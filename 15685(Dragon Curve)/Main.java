import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Dragon{
	int x;
	int y;
	int dir;
	int gen;

	Dragon(int x, int y, int dir, int gen){
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.gen = gen;
	}
}

class Main {
	static ArrayList<Dragon> dragons;
	static int[]][] map;

	Main(){
		dragons = new ArrayList<>();
		map = new int[100][100];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		int dragonNum = Integer.parseInt(input);


		for(int i=0; i<height; i++){
			String input2 = br.readLine();
			String[] temp2 = input2.split(" ");
			
			dragons.add(new Dragon(temp2[0], tenp2[1], temp2[2], temp2[3]));

		}

		for(int i=0; i<dragonNum; i++){
			MakeDragonCurve(dragons.get(i));
		}
	}

	static void MakeDragonCurve(Dragon dragon){

	}

	static void rotate(){
		
	}
}
