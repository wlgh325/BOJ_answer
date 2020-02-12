import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testNum = Integer.parseInt(br.readLine());

		for(int i=0; i<testNum; i++){
			String[] temp2 = br.readLine().split(" ");
			int n = Integer.parseInt(temp2[0]);
			int m = Integer.parseInt(temp2[1]);

			int last1 = 1;
			int last2 = 1;
			int j=0;
			do {
				int temp = last1;
				last1 = last2;
				last2 = (temp + last1) % m;
				j++;
			} while(last1 !=1 || last2 !=1);
			
			
			bw.write(String.valueOf(n) + " " + String.valueOf(j));
			bw.newLine();
		}
		
		
		bw.flush();

		br.close();
		bw.close();
	}
}

