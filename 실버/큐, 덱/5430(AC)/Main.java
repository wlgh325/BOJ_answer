import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int tc=1; tc<=T; tc++) {
        	String cmdd = br.readLine();
        	char[] cmd = cmdd.toCharArray();
        	int n = Integer.parseInt(br.readLine());
        	Deque<String> dq = new LinkedList<>();
        	
        	String input = br.readLine();
        	input = input.substring(1, input.length()-1);
        	if(n != 0)
        		st = new StringTokenizer(input, ",");
        	else {
				// 배열에 주어진 수가 없는데 빼려하면 error
				// 빼려하지 않으면 "[]" 출력
        		if(cmdd.contains("D"))
        			System.out.println("error");
        		else
        			System.out.println("[]");
        		continue;
        	}
        	
        	for(int i=0; i<n; i++)
        		dq.add(st.nextToken());        	
			
			// true면 정방향 -> 앞에서 지움
			// false: 역방향 -> 뒤에서 지움
        	boolean r = true;
        	boolean errorFlag = false;
        	for(int i=0; i<cmd.length; i++) {
				// 방향 바꾸기
        		if(cmd[i] == 'R')
        			r = !r;        		
        		else {
        			// 뺄게 없으면 오류
        			if(dq.isEmpty()) {
        				errorFlag = true;
        				break;
        			}
        			// 정방향 이면 앞에서 빼기
    				if(r) 
    					dq.pollFirst();
    				else
    					dq.pollLast();	// 역방향이면 뒤에서 빼기
        		}
        	}
			
			// 에러가 안났으면 남은 수 출력
        	if(!errorFlag) {
        		System.out.print("[");
        		if(r) {
        			while(dq.size() > 1)
        				System.out.print(dq.pollFirst() + ",");
        		}
        		else {
        			while(dq.size() > 1)
        				System.out.print(dq.pollLast() + ",");
        		}
        		if(dq.size() != 0)
        			System.out.print(dq.getFirst());
        		System.out.println("]");
        	}
        	else {
        		System.out.println("error");
        	}
        }
   }
}