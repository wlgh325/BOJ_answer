import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Consult{
	int i;
	int day;
	int money;

	Consult(int i, int day, int money){
		this.i = i;
		this.day = day;
		this.money = money;
	}
}

class Main {
	static int maxMoney = 0;
	static int N;
	static ArrayList<Consult> schedule;
	static int current=0;
	static int rr;

	// N+1일째 되는날 퇴사
	// 하루에 하나씩 상담을 잡음
	// 상담 하나를 하고 있으면 잡힌 다른 상담은 못함

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] visited;
		String input = br.readLine();
		N = Integer.parseInt(input);

		schedule = new ArrayList<>();

		for(int i=1; i<=N; i++){
			String input2 = br.readLine();
			String[] temp = input2.split(" ");
			
			int day = Integer.parseInt(temp[0]);
			int money = Integer.parseInt(temp[1]);

			// 퇴사 직전에 있는 상담 업무중 아얘 할 수 없는 것 제외
			if(N-i+1>=day)
				schedule.add(new Consult(i, day, money));			
		}

		visited = new boolean[schedule.size()];
		
		int scheduleSize = schedule.size();
		for(int i=1; i<=scheduleSize; i++){
			rr = i;
			comb(visited, 0, scheduleSize, i);
		}
		
		System.out.println(maxMoney);
	}

	static void comb(boolean[] visited, int start, int n, int r){
		if(r==0){
			solve(visited, n);
		}
		else{
			for(int i=start; i<n; i++){
				visited[i] = true;
				comb(visited, i+1, n, r-1);
				visited[i] = false;
			}
		}
	}

	static void solve(boolean[] visited, int n){
		int money = 0;
		int temp = 0;

		for(int i=0; i<n; i++){
			if(visited[i]){
				Consult consult = schedule.get(i);
				if(current <= consult.i){
					current = consult.i + consult.day;
					money += consult.money;
					temp++;
				}
				else
					break;
			}
			if(temp == rr)
				break;
		}

		if(temp == rr){
			if(maxMoney < money){
				maxMoney = money;
			}
		}
		current = 0;
	}

}
