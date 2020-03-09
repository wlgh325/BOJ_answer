import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.println(fact(n) / (fact(k) * fact(n-k)));
		br.close();
    }
    
    public static int fact(int k) {
    	if(k==1)
    		return 1;
    	if(k==0)
    		return 1;
    	return k*fact(k-1);
    }
}