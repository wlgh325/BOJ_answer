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
		
		// 연산자 우선 순위 무시하고 앞에 부터 계산
		// 주어진 숫자는 순서 안 바뀜
		// 나눗셈은 몫만 취한다.

		// 음수를 양수로 나눌때
		// 양수로 바꾼 후 몫을 취하고 그 몫을 음수로 바꾼 것과 같다.
		
		// 식의 결과의 최솟값과 최댓 값 구하기
		
		comb();
	}
	
	public static void comb() {
		
	}
}
