import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int N,C;
	static int[] homepos;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine();
		N = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);

		homepos = new int[N];
		for(int i=0; i<N; i++)
			homepos[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(homepos);
		binSearch();
	}

	public static int binSearch(){
		int left = 1;
		int right = homepos[homepos.length-1];

		while(left < right -1){
			int mid = (left + right) / 2;

			// 오른쪽에 집들이 더 많으면
			if(check()){
				mid = left;
			}
			else{
				mid = right;
			}
		}
		return left;
	}

	public static boolean check(){
		
	}
}