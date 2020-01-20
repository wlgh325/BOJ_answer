package exam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.OutputStreamWriter;

class Info {
	int a;
	int len;
	int b;

	Info(int a, int len, int b){
		this.a = a;
		this.len = len;
		this.b = b;
	}

}
class Main {
	static ArrayList<Info> info = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] temp = br.readLine().split(" ");
		int gruNum = Integer.parseInt(temp[0]);
		int pathNum = Integer.parseInt(temp[1]);
	   
		// n개의 그루터기
		// m개의 오솔길
		// 두 그루터기 사이에 길은 하나
		// 여우와 늑대는 1번에 살고 있음
		// 여우는 일정한 속도로 달린다. but 늑대는 체력 부족. 여우의 하나는 속도 2배, 다음 하나는 여우의 절반 속도 반복
		// 여우가 늑대보다 먼저 도착할 수 있는 그루터기 몇개???

		for(int i=0; i<pathNum; i++){
			String[] temp2 = br.readLine().split(" ");
			info.add(new Info(Integer.parseInt(temp2[0]), temp2[1], temp2[2]));
		}

		
		bw.flush();

		br.close();
		bw.close();
	}

}

