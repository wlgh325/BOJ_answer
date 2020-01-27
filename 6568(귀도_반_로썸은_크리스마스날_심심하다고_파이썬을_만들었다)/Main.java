import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

class Main{	
	static int pc;
	static int ac;
	static final int TEST_NUM = 32;
	static final int PC_SIZE = 32;
	static final int AC_SIZE = 256;
	static int[] mem;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		mem = new int[32];
		pc = 0;
		ac = 0;
		
		boolean run = true;

		String input = "";
		int i=0;

		// EOF 처리
		// 테스트 케이스 여러개 들어올 수 있음
		while((input = br.readLine()) != null) {
			mem[i++] = toInt(input);
			if(i < TEST_NUM)
				continue;

			while(run) {
				int cmd = mem[pc] >> 5;
				int addr = mem[pc] & 31;
				pc = (pc + 1) % PC_SIZE;
				switch(cmd){
					case 0:
						mem[addr] = ac;
						break;
					case 1:
						ac = mem[addr];
						break;
					case 2:
						if(ac == 0)
							pc = addr;
						break;
					case 3:
						break;
					case 4:
						ac--;
						if(ac<0)
							ac = toPositive(ac);
						ac %= AC_SIZE;
						break;
					case 5:
						ac = (ac + 1 ) % AC_SIZE;
						break;
					case 6:
						pc = addr;
						break;
					case 7:
						run = false;
						break;
				}
			}
			String result = toByte(ac);
			bw.write(result);
			bw.newLine();
			i=0; ac=0; pc=0;
			
			run = true;
			mem = new int[32];
			
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	
	static int toInt(String input) {
		int result = 0;
		
		for(int i=0; i<input.length(); i++) {
			result += Integer.parseInt(input.substring(i, i+1)) * Math.pow(2, 7-i) ;
		}
		return result;
	}
	
	static String toByte(int result) {
		String val = "";
		
		while(true) {
			String temp = String.valueOf(result % 2);
			val = temp + val;
			result = (int)(result / 2);
			if(result == 1) {
				val = "1" + val;
				break;
			}
			else if(result == 0) {
				val = "0" + val;
				break;
			}
				
		}
		
		// 0으로 채우기
		while(true) {
			if(val.length() == 8)
				break;
			val = "0" + val;
		}
		
		return val;
	}
	
	static int toPositive(int ac) {
		ac = -ac;
		char[] tmp = new char[8];
		int div = 128;
		String temp = "";
		
		for(int j=0; j<8; j++) {
			
			if((ac & div) == 1)
				tmp[j] = '1';
			else
				tmp[j] = '0';
			div = div >> 1;
		}
		
		for(int j=0; j<8; j++) {
			if(tmp[j] == '1') tmp[j] = '0';
			else {
				tmp[j] = '1';
			}
		}
		for(int j=7; j>=0; j--){
		    if(tmp[j]=='1') tmp[j]='0';
		    else {
		        tmp[j]='1';
		        break;
		    }
		}
		
		for(int j=0; j<8; j++) {
			if(tmp[j] == '0')
				continue;
			else
				temp = temp + "1";
		}
		
		return toInt(temp);
	}
}