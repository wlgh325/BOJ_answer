import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int m =0;
		int n =0;

		// 현 위치의 y좌표가 높이의 절반 보다 작은경우
		if(h/2 < y)
			m = h-y;
		else
			m = y;

		// 현 위치의 x좌표가 너비의 절반 보다 작은 경우
		if(w/2 < x)
			n = w-x;
		else
			n = x;

		// 높이아 너비 중 어느쪽이 더 가까운지 check
		if(m >= n)
			System.out.println(n);
		else
			System.out.println(m);
		
		br.close();
	}
}