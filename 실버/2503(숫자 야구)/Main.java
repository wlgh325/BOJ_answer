import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] baseball = new int[n][3];
		
		for(int i=0; i<n; i++){
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<3; j++)
				baseball[i][j] = Integer.parseInt(temp[j]);
		}

		int cnt = 0;
		for(int i=123; i<=987; i++){
			char[] ans = String.valueOf(i).toCharArray();
			
			// 숫자가 모두 달라야 함
			if(ans[0] == ans[1] || ans[0] == ans[2] || ans[1] == ans[2])
				continue;
			// 0이 포함되면 안됨
			if(ans[0] == '0' || ans[1] == '0' || ans[2] == '0')
				continue;

			boolean flag = true;
			for(int j=0; j<n; j++){
				char[] num = String.valueOf(baseball[j][0]).toCharArray();
				int strike = baseball[j][1];
				int ball = baseball[j][2];
	
				int estimate_strike = 0;
				int estimate_ball = 0;

				for(int k=0; k<3; k++){
					for(int t=0; t<3; t++){
						if(k == t && num[k] == ans[t]){
							estimate_strike++;
						}
						else if(num[k] == ans[t])
							estimate_ball++;
					}
				}

				if(estimate_strike != strike || estimate_ball != ball){
					flag = false;
					break;
				}
			}
			if(flag)
				cnt++;
		}
		System.out.println(cnt);
	}
}