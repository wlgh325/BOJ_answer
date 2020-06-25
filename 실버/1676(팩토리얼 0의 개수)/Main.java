import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Pair{
	int five;
	int two;

	Pair(int five, int two){
		this.five = five;
		this.two = two;
	}
}

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] cnt = new int[N+1];
		Pair[] fact = new Pair[N+1];
		for(int i=0; i<=N; i++)
			fact[i] = new Pair(0,0);

		for(int i=2; i<=N; i++){
			int temp = i;
			int five = 0;
			int two = 0;
			
			// 새로 곱하는 수의 인수 2의 개수 구하기
			while(true){
				if(temp % 2 == 0){
					two++;
					temp /= 2;
				}
				else
					break;
			}
			temp = i;

			// 새로 곱하는 수의 인수 5의 개수 구하기
			while(true){
				if(temp % 5 == 0){
					five++;
					temp /= 5;
				}
				else
					break;
			}
			fact[i].two = fact[i-1].two + two;
			fact[i].five = fact[i-1].five + five;

			cnt[i] = Math.min(fact[i].two, fact[i].five);
		}
		System.out.println(cnt[N]);
		br.close();
	}
}