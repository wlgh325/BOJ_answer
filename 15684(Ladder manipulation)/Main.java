import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Ladder{
	int a;
	int b;

	Ladder(int a, int b){
		this.a = a;
		this.b = b;
	}
}

class Main {
	static ArrayList<Ladder> ladderMap;
	static int vertical;
	static int horizontal;
	static int manipulNum;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] temp = input.spilt(" ");

		vertical = Integer.parseInt(temp[0]);
		horizontal = Integer.parseInt(temp[1]);
		manipulNum = Integer.parseInt(temp[2]);

		ladderMap = new ArrayList<>();

		for(int i=0; i<horizontal; i++){
			String input2 = br.readLine();
			String temp2 = input2.split(" ");
			int a = Integer.parseInt(temp2[0]);
			int b = Integer.parseInt(temp2[1]);
			
			ladderMap.add(new Ladder(a,b));
		}
	}
}
