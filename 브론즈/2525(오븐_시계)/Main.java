import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] temp = br.readLine().split(" ");
    	
    	int nowH = Integer.parseInt(temp[0]);
    	int nowM = Integer.parseInt(temp[1]);
		int need = Integer.parseInt(br.readLine());
    	
		// 0시 0분 부터 23시 59분까지 있음
		// 최대 1000분까지 있음
		int sum = nowM + need;
		if(sum >= 60){
			nowH += sum / 60;
			sum = sum % 60;
		}

		if(nowH >= 24)
			nowH = nowH % 24;

		System.out.println(nowH + " " + sum);
    }
}