import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int num;
	static int[] arr;
	static char[] t;
	static ArrayList<Integer> list;
	static char[] opList; 
	static int idx = 0;
	static boolean[] visited;
	static ArrayList<Integer> resultList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		num = Integer.parseInt(br.readLine());
		arr = new int[num];
		t = new char[num-1];
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
		perm(0);
		
		Collections.sort(resultList);
		System.out.println(resultList.get(resultList.size() - 1));
		System.out.println(resultList.get(0));
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
	
	public static void perm(int k)
	{
		if (k == num-1) {
			resultList.add(solve());	// 계산하기
		}
		else
		{
			for (int i = 0; i < num-1; i++)
			{
				if (visited[i]) continue;

				t[k] = opList[i];
				visited[i] = true;
				perm(k + 1);
				visited[i] = false;
			}
		}
	}
	
	public static int solve() {
		int result = arr[0];
		int idx = 1;
		for(int i=0; i<t.length; i++) {
			char op = t[i];
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
					result /= arr[idx];
					if(isoverFlow())
						
					break;
			}
			idx++;
		}
		return result;
	}
	
	public static boolean isoverFlow() {
		boolean flag = false;
		
		return flag;
	}
}
