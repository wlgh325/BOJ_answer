import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	String str = br.readLine();
    	String result = "";
    	
    	String[] temp = str.split("<");
    	
    	int i;
    	if(str.charAt(0) == '<') {
    		i = 1;
    		result += "<";
    	}
    	else
    		i = 0;
    	
    	for(;i<temp.length; i++) {
    		int idx = temp[i].indexOf(">");
    		result += temp[i].substring(0, idx+1);
    		
    		String[] temp2 = temp[i].substring(idx+1, temp[i].length()).split(" ");
    		for(int j=0; j<temp2.length; j++) {
    			result += (new StringBuilder(temp2[j])).reverse().toString();
    			if(j != temp2.length-1)
    				result += " ";
    		}
    		
    		if(i != temp.length - 1)
    			result += "<";
    	}
    	bw.write("" + result);
    	bw.flush();
    	br.close();
    	bw.close();
    }
}