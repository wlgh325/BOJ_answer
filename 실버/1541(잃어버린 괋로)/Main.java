import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split("\\-");	// - 를 기준으로 식 나누기
		
		int ans = 0; // 최종 답
		for(int i=0, len=temp.length; i<len; i++) {
			String str = temp[i];	// 나누어진 식
			int ans2 = 0;	// 중간 결과(식에 있는 수를 더한 값)
			if(str.contains("+")) {	// 식에 +가 포함되어 있을 경우만 덧셈을 위해 나누기, 숫자 하나만 있는 경우 에러 방진
				String[] temp2 = str.split("\\+");
				for(int j=0; j<temp2.length; j++) {
					ans2 += Integer.parseInt(temp2[j]);	// 식에 있는 숫자 모두 더하기				
				}
			}
			else
				ans2 = Integer.parseInt(str);	// 숫자 하나만 있는 경우 숫자 하나만 더하기
			
			// 맨 앞의 수의 경우 기준 수가 되므로 더한다
			// 그 뒤에 수들은 맨 앞의 수에서 뺀다.
			if(i==0)
				ans += ans2;
			else
				ans -= ans2;
		}
		System.out.println(ans);
		br.close();
	}
}