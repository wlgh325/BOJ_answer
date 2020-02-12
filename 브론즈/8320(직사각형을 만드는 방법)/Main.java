import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int n = Integer.parseInt(br.readLine());
    	
    	int sum = 1;
    	HashSet<Integer> set = new HashSet<>();
    	for(int j=2; j<=n; j++) {
    		for(int i=1; i<j; i++) {
    			if(j % i == 0) {
    				set.add(i);
    				set.add(j/i);
    			}
    		}
    		if(set.size() % 2 == 0)
    			sum += set.size() / 2;
    		else
    			sum += set.size()/2 + 1;
    		set.clear();
    	}
    	
    	bw.write("" + sum);
    	bw.flush();
    	br.close();
    	bw.close();
    }
}