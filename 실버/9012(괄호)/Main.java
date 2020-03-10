import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();

		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++){			
			String ps = br.readLine();
			boolean flag = false;
			for(int idx=0; idx<ps.length(); idx++){
				char p = ps.charAt(idx);
				switch(p){
					case '(':
						// '(' 문자는 stack에 push
						stack.push(p);
						break;
					case ')':
						// 갯수가 맞는 경우 stack에서 pop
						if(!stack.isEmpty())
							stack.pop();
						else
							flag = true;	// '(와 ')'의 개수가 맞지 않는 경우 탈출
						break;
				}

				if(flag)
					break;
			}

			// 짝이 모두 맞고 갯수가 맞는 경우 올바른 괄호
			if(stack.size() == 0 && !flag)
				System.out.println("YES");
			else
				System.out.println("NO");
			stack.clear();	// stack 비우기
		}
	}
}