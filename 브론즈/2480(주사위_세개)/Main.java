import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] temp = br.readLine().split(" ");
    	
    	int a = Integer.parseInt(temp[0]);
    	int b = Integer.parseInt(temp[1]);
    	int c = Integer.parseInt(temp[2]);
    	
    	int cnt = 0;	// 같은 주사위 count, 1이면 같은 주사위 2개, 2이면 같은 주사위 3개
    	int same = 0;	// 같은 수
    	if(a == b) {
    		cnt++;
    		same = a;
    		if(b==c)
    			cnt++;
    	}
    	else {
    		if(a==c) {
    			cnt++;
    			same=a;
    		}
    		else if(b==c) {
    			cnt++;
    			same=b;
    		}
    	}
    	
    	switch(cnt) {
	    	case 0:
	    		// 같은 눈 없음
	    		int max = Math.max(a, Math.max(b,c));
	    		System.out.println(max*100);
	    		break;
	    	case 1:
	    		// 같은 눈 2개
	    		System.out.println(1000+100*same);
	    		break;
	    	case 2:
	    		// 같은 눈 3개
	    		System.out.println(10000+same*1000);
	    		break;
    	}
    }
}