import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringBuilder sb = new StringBuilder();
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
		int M = Integer.parseInt(br.readLine());
		long set = 0;

		int num = 0;
		int check = 0;
		for(int i=0; i<M; i++){
			String[] temp = br.readLine().split(" ");
			if(!temp[0].equals("empty") && !temp[0].equals("all"))
				num = Integer.parseInt(temp[1]);
			check = 1 << (num-1);
			switch(temp[0]){
				case "add":
					set |= check;
					break;
				case "remove":
					set &= ~check;
					break;
				case "check":
					if((set & check) == check)
						bw.write(1 + "\n");
					else
						bw.write(0 + "\n");
					break;
				case "toggle":
					if((set & check) == check)
						set &= ~check;
					else
						set |= check;
					break;
				case "all":
					set = (1 << 20) - 1;
					break;
				case "empty":
					set = 0;
					break;
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}