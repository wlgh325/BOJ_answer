import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	static int num;
	static int[] arr;
	static int[] op;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		num = Integer.parseInt(br.readLine());
		arr = new int[num];
		op = new int[4];
		list = new ArrayList<>();
		
		String[] temp = br.readLine().split(" ");
		
		for(int i=0; i<temp.length; i++) {
			arr[i] = Integer.parseInt(temp[i]);
		}
		
		String[] temp2 = br.readLine().split(" ");
		for(int i=0; i<4; i++) {
			op[i] = Integer.parseInt(temp2[i]);
		}
		
		// 가능한 조합 구하기
		comb();
	}
	
	public static void comb() {
		
	}
}
