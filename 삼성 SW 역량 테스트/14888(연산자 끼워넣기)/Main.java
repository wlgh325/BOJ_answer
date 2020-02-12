import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int num;
	static int[] arr;
	static char[] output;
	static ArrayList<Integer> list;
	static char[] opList; 
	static int idx = 0;
	static boolean[] visited;
	static ArrayList<Integer> resultList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		num = Integer.parseInt(br.readLine());
		arr = new int[num];
		output = new char[num-1];
		list = new ArrayList<>();
		opList = new char[num-1];
		resultList = new ArrayList<>();
		
		String[] temp = br.readLine().split(" ");
		
		for(int i=0; i<temp.length; i++) {
			arr[i] = Integer.parseInt(temp[i]);
		}
		
		String[] temp2 = br.readLine().split(" ");
		for(int i=0; i<4; i++) {
			int num = Integer.parseInt(temp2[i]);
			initOperator(i, num);
		}
		
		visited = new boolean[num-1];
		perm(0, num-1, num-1);
		
		System.out.println(Collections.max(resultList));
		System.out.println(Collections.min(resultList));
	}
	
	public static void initOperator(int k, int num) {
		switch(k) {
			// '+'
			case 0:
				for(int j=0; j<num; j++,idx++)
					opList[idx] = '+';
				break;
			// '-'
			case 1:
				for(int j=0; j<num; j++,idx++)
					opList[idx] = '-';
				break;
			// '*'
			case 2:
				for(int j=0; j<num; j++,idx++)
					opList[idx] = '*';
				break;
			// '/'
			case 3:
				for(int j=0; j<num; j++,idx++)
					opList[idx] = '/';
		}
	}
	
	public static void perm(int start, int N, int r){
		if(start==r){
			resultList.add(solve());
		}
		else{
			for(int i=0; i<N; i++){
				if(!visited[i]) {
					visited[i] = true;
					output[start] = opList[i];
					perm(start+1, N, r);
					visited[i] = false;	
				}
				
			} 
		}
	}
	
	public static int solve() {
		int result = arr[0];
		int idx = 1;
		for(int i=0; i<num-1; i++) {
			char op = output[i];
			switch(op) {
				case '+':
					result += arr[idx];
					break;
				case '-':
					result -= arr[idx];
					break;
				case '*':
					result *= arr[idx];
					break;
				case '/':
					if(arr[idx] < 0)
						result = divideNegative(result, arr[idx]);
					else
						result /= arr[idx];
					break;
			}
			idx++;
		}
		return result;
	}
	
	public static int divideNegative(int result, int num) {
		int temp = 0;
		
		num = Math.abs(num);
		
		temp = result / num;
		temp -= temp*2;

		return temp;
	}
}