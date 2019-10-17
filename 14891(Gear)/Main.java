import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main {
	static int[][] map;	// 톱니바퀴
	static int area=0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] temp = input.split(" ");
		int height = Integer.parseInt(temp[0]);
		int width = Integer.parseInt(temp[1]);

		map = new int[height][width];

		for(int i=0; i<height; i++){
			String input2 = br.readLine();
			String[] temp2 = input2.split(" ");
			for(int j=0; j<width; j++){
				map[i][j] = Integer.parseInt(temp2[j]);
			}
		}

	}
}
