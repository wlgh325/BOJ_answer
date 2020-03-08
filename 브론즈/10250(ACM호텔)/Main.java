import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testNum = Integer.parseInt(br.readLine());
		for(int t=1; t<=testNum; t++){
			String[] temp = br.readLine().split(" ");

			int H = Integer.parseInt(temp[0]);
			int W = Integer.parseInt(temp[1]);
			int N = Integer.parseInt(temp[2]);

			int result = 0;
			if( N % H == 0){
				int x = N / H;
				result = H*100 + x;
			}
			else{
				int x = (N / H) + 1;
				int y = N % H;
				result = y*100 + x;
			}
			System.out.println(result);
		}
		br.close();
	}
}